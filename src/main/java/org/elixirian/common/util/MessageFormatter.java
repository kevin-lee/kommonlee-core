package org.elixirian.common.util;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-03-02)
 * @version 0.0.2 (2010-05-04) the {@link #format(String, Object...)} method is recreated (moved from {@link Strings}.
 * @version 0.0.3 (2010-05-05) it does not throw the {@link NullPointerException} when the given message String has null
 *          reference. It uses the String {@code "null"} instead.
 */
public final class MessageFormatter
{
	/**
	 * '%'
	 */
	public static final char ESCAPE_CHAR = '%';

	/**
	 * "%s"
	 */
	public static final String PLACE_HOLDER_SYMBOL = ESCAPE_CHAR + "s";

	/**
	 * 2
	 */
	public static final int JUMP_SIZE = 2;

	private MessageFormatter()
	{
	}

	/**
	 * Format the message with the given argument objects. The place holder is
	 * <code>%s<code>. all the "%s"s are replaced by the given argument objects.
	 * <ul>
	 * <li>If there are more argument objects passed, the rest are added the end of the message in the square brackets.</li> 
	 * <li>If there are fewer "%s"s entered, the rest are removed from the message.</li>
	 * <li>To include <code>%s</code> in the message, put <code>%</code> just before <code>%s</code> (e.g. %%s / the
	 * first % is escape character).</li> </ul> <div> e.g.) </div>
	 * 
	 * <pre>
	 * Message:   "Hello, %s. How are you %s?"
	 * Arguments: "world", "today"
	 * Result:    "Hello, world. How are you today?"
	 * </pre>
	 * 
	 * <pre>
	 * Message:   "Hello, %s. How are you %s?"
	 * Arguments: "world", "today", "Kevin", "Lee"
	 * Result:    "Hello, world. How are you today? [Kevin, Lee]"
	 * </pre>
	 * 
	 * <pre>
	 * Message:   "Hello, %s. How are you %s? So you have more %s. Oh! another %s?"
	 * Arguments: "world", "today"
	 * Result:    "Hello, world. How are you today? So you have more . Oh! another ?"
	 * </pre>
	 * 
	 * <pre>
	 * Message:   "Hello, %s. How are you %s? %%s is escaped, but this %s is not."
	 * Arguments: "world", "today", "place holder"
	 * Result:    "Hello, world. How are you today? %s is escaped, but this place holder is not."
	 * </pre>
	 * 
	 * This is correct.
	 * 
	 * <pre>
	 * format(&quot;some message&quot;, (Object) null);
	 * </pre>
	 * 
	 * but the following code is not. This results in {@link NullPointerException}.
	 * 
	 * <pre>
	 * format(&quot;some message&quot;, null);
	 * </pre>
	 * 
	 * If there is no argument object then just do not pass anything.
	 * 
	 * <pre>
	 * format(&quot;some message&quot;);
	 * </pre>
	 * 
	 * To sum up,
	 * 
	 * <pre>
	 * format(&quot;some message&quot;, null); // WRONG!
	 * </pre>
	 * 
	 * <pre>
	 * format(&quot;some message&quot;); // FINE
	 * </pre>
	 * 
	 * <pre>
	 * format(&quot;some message&quot;, (Object) null); // FINE 
	 * // but no point to do this unless the given object variable contains null reference like the following code.
	 * 
	 * // somehow object contains null.
	 * format(&quot;some %s message&quot;, object);
	 * 
	 * // result is
	 * &quot;some null message&quot;
	 * </pre>
	 * 
	 * @param message
	 *            the given message.
	 * @param args
	 *            the given argument objects.
	 * @return formatted message.
	 */
	public static String format(final String message, final Object... args)
	{
		/* if the given String has null reference, make it String "null". */
		final String localMessage = String.valueOf(message);
		int i = 0;
		int fromIndex = 0;
		final StringBuilder formattedMessage = new StringBuilder();
		while (args.length > i)
		{
			int position = localMessage.indexOf(PLACE_HOLDER_SYMBOL, fromIndex);

			if (0 > position)
			{
				break;
			}
			if (0 < position && localMessage.charAt(position - 1) == ESCAPE_CHAR)
			{
				/* "%%s" is found so escape. */
				formattedMessage.append(localMessage.substring(fromIndex, position))
						.append(localMessage.charAt(position + 1));
			}
			else
			{
				/* "%s" is found so replace it with one of the arguments. */
				formattedMessage.append(localMessage.substring(fromIndex, position))
						.append(args[i++]);
			}
			fromIndex = position + JUMP_SIZE;
		}

		int position = localMessage.indexOf(PLACE_HOLDER_SYMBOL, fromIndex);
		if (0 == position)
		{
			/*
			 * 0 == position means it can never be "%%s" so treat it separately then testing the position against 0 is
			 * not required anymore.
			 */
			fromIndex = position + JUMP_SIZE;
			position = localMessage.indexOf(PLACE_HOLDER_SYMBOL, fromIndex);
		}
		while (0 < position)
		{
			/* remove all the extra "%s"s and escape the rest of the "%%s"s */
			if (localMessage.charAt(position - 1) == ESCAPE_CHAR)
			{
				/* "%%s" is found so escape then jump to the next position. */
				formattedMessage.append(localMessage.substring(fromIndex, position))
						.append(localMessage.charAt(position + 1));
			}
			else
			{
				/* "%s" is found so take all the Strings before it then jump to the next postion. */
				formattedMessage.append(localMessage.substring(fromIndex, position));
			}
			fromIndex = position + JUMP_SIZE;
			position = localMessage.indexOf(PLACE_HOLDER_SYMBOL, fromIndex);
		}
		formattedMessage.append(localMessage.substring(fromIndex));

		if (args.length > i)
		{
			/* put all the extra arguments to the end of the message. */
			formattedMessage.append(0 < formattedMessage.length() ? " [" : "[")
					.append(args[i++]);
			while (args.length > i)
			{
				formattedMessage.append(", ")
						.append(args[i++]);
			}
			formattedMessage.append("]");
		}
		return formattedMessage.toString();
	}
}

/**
 * 
 */
package com.elixirian.common.string;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-23)
 * @version 0.0.2 (2010-03-16) bug fixed: It does not remove the extra "%s"s nor does it escape the rest of the "%%s"s after all the
 *          arguments replace the "%s"s but there are still "%s"s or "%%s"s left =fixed=> It removes all the extra "%s"s and escapes the
 *          rest of the "%%s"s.
 * @version 0.0.3 (2010-04-01) null check and empty array check are removed for better performance (removing optimisation has led to even
 *          more optimised code).
 * @version 0.0.4 (2010-04-20) refactoring: more optimisation.
 */
public final class MessageFormatter
{
	public static final char ESCAPE_CHAR = '%';

	public static final String STRING_ARGUMANT_SYMBOL = ESCAPE_CHAR + "s";

	public static final int JUMP_SIZE = 2;

	private MessageFormatter()
	{
		throw new IllegalStateException(getClass().getName() + " cannot be instantiated.");
	}

	/**
	 * Format the message with the given argument objects. The place holder is
	 * <code>%s<code>. all the "%s"s are replaced by the given argument objects.
	 * <ul>
	 * <li>If there are more argument objects passed, the rest are added the end of the message in the square brackets.</li> 
	 * <li>If there are fewer "%s"s entered, the rest are removed from the message.</li>
	 * <li>To include <code>%s</code> in the message, put <code>%</code> just before <code>%s</code> (e.g. %%s / the first % is escape
	 * character).</li> </ul> <div> e.g.) </div>
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
	 * formatMessage(&quot;some message&quot;, (Object) null);
	 * </pre>
	 * 
	 * but the following code is not. This results in {@link NullPointerException}.
	 * 
	 * <pre>
	 * formatMessage(&quot;some message&quot;, null);
	 * </pre>
	 * 
	 * If there is no argument object then just do not pass anything.
	 * 
	 * <pre>
	 * formatMessage(&quot;some message&quot;);
	 * </pre>
	 * 
	 * To sum up,
	 * 
	 * <pre>
	 * formatMessage(&quot;some message&quot;, null); // WRONG!
	 * </pre>
	 * 
	 * <pre>
	 * formatMessage(&quot;some message&quot;); // FINE
	 * </pre>
	 * 
	 * <pre>
	 * formatMessage(&quot;some message&quot;, (Object) null); // FINE 
	 * // but no point to do this unless the given object variable contains null reference like the following code.
	 * 
	 * // somehow object contains null.
	 * formatMessage(&quot;some %s message&quot;, object);
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
	public static String formatMessage(final String message, final Object... args)
	{
		int i = 0;
		int fromIndex = 0;
		final StringBuilder formattedMessage = new StringBuilder();
		while (args.length > i)
		{
			int position = message.indexOf(STRING_ARGUMANT_SYMBOL, fromIndex);

			if (0 > position)
			{
				break;
			}
			if (0 < position && message.charAt(position - 1) == ESCAPE_CHAR)
			{
				/* "%%s" is found so escape. */
				formattedMessage.append(message.substring(fromIndex, position))
						.append(message.charAt(position + 1));
			}
			else
			{
				/* "%s" is found so replace it with one of the arguments. */
				formattedMessage.append(message.substring(fromIndex, position))
						.append(args[i++]);
			}
			fromIndex = position + JUMP_SIZE;
		}

		int position = message.indexOf(STRING_ARGUMANT_SYMBOL, fromIndex);
		if (0 == position)
		{
			/* 0 == position means it can never be "%%s" so treat it separately then testing the position against 0 is not required anymore. */
			fromIndex = position + JUMP_SIZE;
			position = message.indexOf(STRING_ARGUMANT_SYMBOL, fromIndex);
		}
		while (0 < position)
		{
			/* remove all the extra "%s"s and escape the rest of the "%%s"s */
			if (message.charAt(position - 1) == ESCAPE_CHAR)
			{
				/* "%%s" is found so escape then jump to the next position. */
				formattedMessage.append(message.substring(fromIndex, position))
						.append(message.charAt(position + 1));
			}
			else
			{
				/* "%s" is found so take all the Strings before it then jump to the next postion. */
				formattedMessage.append(message.substring(fromIndex, position));
			}
			fromIndex = position + JUMP_SIZE;
			position = message.indexOf(STRING_ARGUMANT_SYMBOL, fromIndex);
		}
		formattedMessage.append(message.substring(fromIndex));

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

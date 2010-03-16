/**
 * 
 */
package com.elixirian.common.string;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-23)
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

	public static String formatMessage(final String message, final Object... args)
	{
		if (null == args || 0 == args.length)
		{
			return message;
		}

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
		while (0 <= position)
		{
			/* remove all the extra "%s"s and escape the rest of the "%%s"s */
			if (0 < position)
			{
				// /* "%s" is found in the index number 0 so just jump */
				// fromIndex = position += JUMP_SIZE;
				// }
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

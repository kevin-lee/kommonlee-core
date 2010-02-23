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
				formattedMessage.append(message.substring(fromIndex, position))
						.append(message.charAt(position + 1));
				fromIndex = position += JUMP_SIZE;
				continue;
			}

			formattedMessage.append(message.substring(fromIndex, position))
					.append(args[i++]);
			fromIndex = position += JUMP_SIZE;
		}
		formattedMessage.append(message.substring(fromIndex));

		if (args.length > i)
		{
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

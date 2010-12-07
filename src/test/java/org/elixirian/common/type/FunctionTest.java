package org.elixirian.common.type;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * This test is to demonstrate how to use the {@link Function}.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class FunctionTest
{
	private static class OrderedItem
	{
		private final int id;
		private final String name;
		private final String status;

		public OrderedItem(int id, String name, String status)
		{
			this.id = id;
			this.name = name;
			this.status = status;
		}

		public int getId()
		{
			return id;
		}

		public String getName()
		{
			return name;
		}

		public String getStatus()
		{
			return status;
		}

		@SuppressWarnings("boxing")
		@Override
		public int hashCode()
		{
			return Arrays.hashCode(new Object[] { id, name, status });
		}

		@Override
		public boolean equals(Object orderedItem)
		{
			/* @formatter:off */
			if (this == orderedItem) return true;
			if (!(orderedItem instanceof OrderedItem)) return false;
			final OrderedItem that = (OrderedItem) orderedItem;
			return this.id == that.getId() && 
					(this.name == that.getName() || 
						(null != this.name && this.name.equals(that.getName()))) && 
					(this.status == that.getStatus() || 
						(null != this.status && this.status.equals(that.getStatus())));
			/* @formatter:on */
		}

		@Override
		public String toString()
		{
			return OrderedItem.class.getSimpleName() + "{id=" + id + ", name=" + name + ", status=" + status + "}";
		}
	}

	private enum Status
	{
		ORDERED("ordered"), PAID("paid"), SENT("sent"), CANCELLED("cancelled");
		private final String newName;

		private Status(String newName)
		{
			this.newName = newName;
		}

		public String newName()
		{
			return newName;
		}
	}

	private static class NewOrderedItem
	{
		private final int id;
		private final String name;
		private final Status status;

		public NewOrderedItem(int id, String name, Status status)
		{
			this.id = id;
			this.name = name;
			this.status = status;
		}

		public int getId()
		{
			return id;
		}

		public String getName()
		{
			return name;
		}

		public Status getStatus()
		{
			return status;
		}

		@SuppressWarnings("boxing")
		@Override
		public int hashCode()
		{
			return Arrays.hashCode(new Object[] { id, name, status });
		}

		@Override
		public boolean equals(Object newOrderedItem)
		{
			/* @formatter:off */
			if (this == newOrderedItem) return true;
			if (!(newOrderedItem instanceof NewOrderedItem)) return false;
			final NewOrderedItem that = (NewOrderedItem) newOrderedItem;
			return this.id == that.getId() && 
					(this.name == that.getName() || 
						(null != this.name && this.name.equals(that.getName()))) && 
					(this.status == that.getStatus() || 
						(null != this.status && this.status.equals(that.getStatus())));
			/* @formatter:on */
		}

		@Override
		public String toString()
		{
			return NewOrderedItem.class.getSimpleName() + "{id=" + id + ", name=" + name + ", status="
					+ status.newName() + "}";
		}
	}

	private static class NewOrderedItemCreaterFunction implements Function<OrderedItem, NewOrderedItem>
	{
		@Override
		public NewOrderedItem perform(OrderedItem input)
		{
			return new NewOrderedItem(input.id, input.name, Status.valueOf(input.status));
		}
	}

	private static class OrderedItemToNewOrderedItemConverter
	{
		public List<NewOrderedItem> convert(final List<OrderedItem> orderedItems,
				Function<OrderedItem, NewOrderedItem> function)
		{
			final List<NewOrderedItem> resultList = new ArrayList<NewOrderedItem>();
			for (final OrderedItem item : orderedItems)
			{
				resultList.add(function.perform(item));
			}
			return resultList;
		}
	}

	@Test
	public final void testPerform()
	{
		/* @formatter:off */
		final List<OrderedItem> orderedItems =
			Collections.unmodifiableList(Arrays.asList(
				new OrderedItem(1,  "Product A", "ORDERED"), 
				new OrderedItem(2,  "Product B", "ORDERED"),
				new OrderedItem(3,  "Product C", "SENT"), 
				new OrderedItem(4,  "Product D", "PAID"), 
				new OrderedItem(5,  "Product E", "PAID"), 
				new OrderedItem(6,  "Product F", "SENT"), 
				new OrderedItem(7,  "Product G", "CANCELLED"), 
				new OrderedItem(8,  "Product H", "ORDERED"), 
				new OrderedItem(9,  "Product I", "CANCELLED"), 
				new OrderedItem(10, "Product J", "PAID"), 
				new OrderedItem(11, "Product K", "ORDERED"), 
				new OrderedItem(12, "Product L", "ORDERED"), 
				new OrderedItem(13, "Product M", "ORDERED")));

		final List<NewOrderedItem> newOrderedItems =
			Collections.unmodifiableList(Arrays.asList(
					new NewOrderedItem(1,  "Product A", Status.ORDERED), 
					new NewOrderedItem(2,  "Product B", Status.ORDERED),
					new NewOrderedItem(3,  "Product C", Status.SENT), 
					new NewOrderedItem(4,  "Product D", Status.PAID), 
					new NewOrderedItem(5,  "Product E", Status.PAID), 
					new NewOrderedItem(6,  "Product F", Status.SENT), 
					new NewOrderedItem(7,  "Product G", Status.CANCELLED), 
					new NewOrderedItem(8,  "Product H", Status.ORDERED), 
					new NewOrderedItem(9,  "Product I", Status.CANCELLED), 
					new NewOrderedItem(10, "Product J", Status.PAID), 
					new NewOrderedItem(11, "Product K", Status.ORDERED), 
					new NewOrderedItem(12, "Product L", Status.ORDERED), 
					new NewOrderedItem(13, "Product M", Status.ORDERED)));
		/* @formatter:on */

		System.out.println("orderedItems: " + orderedItems);
		System.out.println("## expected: ");
		System.out.println("newOrderedItems: " + newOrderedItems);
		/* @formatter:off */
		System.out.println("## result: ");
		System.out.println("newOrderedItems: " + 
		new OrderedItemToNewOrderedItemConverter().convert(orderedItems,
			new Function<OrderedItem, NewOrderedItem>() {
				@Override
				public NewOrderedItem perform(final OrderedItem input)
				{
					return new NewOrderedItem(input.getId(), 
											   input.getName(), 
											   Status.valueOf(input.getStatus()));
				}
			}));
		/* @formatter:on */

		final OrderedItemToNewOrderedItemConverter orderedItemToNewOrderedItemConverter =
			new OrderedItemToNewOrderedItemConverter();
		assertThat(orderedItemToNewOrderedItemConverter.convert(orderedItems, new NewOrderedItemCreaterFunction()),
				is(equalTo(newOrderedItems)));
	}
}

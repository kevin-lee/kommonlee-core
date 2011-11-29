package org.elixirian.kommonlee.type;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.elixirian.kommonlee.type.Condition1;
import org.elixirian.kommonlee.type.Function2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2011-05-22)
 */
public class Function2Test
{
	private static class Product
	{
		private final Long number;
		private final String name;

		private Product(final Long number, final String name)
		{
			this.number = number;
			this.name = name;
		}

		public Long getNumber()
		{
			return number;
		}

		public String getName()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return "Product{number=" + number + ",name=" + name + "}";
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object product)
		{
			if (this == product)
				return true;

			if (!(product instanceof Product))
				return false;

			final Product that = (Product) product;
			return this.name == that.getName() || (null != this.name && this.name.equals(that.getName()));
		}

	}

	private static class LineItem
	{
		private Product product;

		private int quantity;

		private LineItem(final Product product, final int quantity)
		{
			this.product = product;
			this.quantity = quantity;
		}

		public Product getProduct()
		{
			return product;
		}

		public void setProduct(final Product product)
		{
			this.product = product;
		}

		public int getQuantity()
		{
			return quantity;
		}

		public void setQuantity(final int quantity)
		{
			this.quantity = quantity;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((product == null) ? 0 : product.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object lineItem)
		{
			if (this == lineItem)
				return true;

			if (!(lineItem instanceof LineItem))
				return false;

			final LineItem that = (LineItem) lineItem;
			return this.product == that.getProduct()
					|| (null != this.product && this.product.equals(that.getProduct()));
		}

		@Override
		public String toString()
		{
			return "LineItem{product=" + product + ",quantity=" + quantity + "}";
		}
	}

	private static class Order
	{
		private final Long number;

		private final Set<LineItem> itemList;

		private Order(final Long number, final Set<LineItem> itemList)
		{
			this.number = number;
			this.itemList = itemList;
		}

		public Long getNumber()
		{
			return number;
		}

		public Iterable<LineItem> getItems()
		{
			return itemList;
		}

		public Order addItem(final LineItem item)
		{
			itemList.add(item);
			return this;
		}

		public int itemSize()
		{
			return itemList.size();
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((number == null) ? 0 : number.hashCode());
			result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object order)
		{
			if (this == order)
				return true;

			if (!(order instanceof Order))
				return false;

			if (!(order instanceof Order))
			{
				return false;
			}
			final Order that = (Order) order;
			return (this.number == that.getNumber() || (null != this.number && this.number.equals(that.getNumber())))
					|| (this.itemList == that.getItems() || (null != this.itemList && this.itemList.equals(that.getItems())));
		}

	}

	private static class Customer
	{
		private String surname;
		private String givenName;

		private Customer(final String surname, final String givenName)
		{
			this.surname = surname;
			this.givenName = givenName;
		}

		@Override
		public String toString()
		{
			return surname + ", " + givenName;
		}
	}

	private static final Product[] PRODUCTS = new Product[] { new Product(1L, "Product A"),
			new Product(2L, "Product B"), new Product(3L, "Product C"), new Product(4L, "Product D"),
			new Product(5L, "Product E"), new Product(6L, "Product F"), new Product(7L, "Product G") };

	private static final Integer[] QUANTITIES = new Integer[] { 10, 4, 5, 50, 3, 11, 80 };

	private Order order;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@SuppressWarnings("boxing")
	@Before
	public void setUp() throws Exception
	{
		order = new Order(1L, new HashSet<LineItem>());
		order.addItem(new LineItem(PRODUCTS[0], QUANTITIES[0]))
				.addItem(new LineItem(PRODUCTS[1], QUANTITIES[1]))
				.addItem(new LineItem(PRODUCTS[2], QUANTITIES[2]))
				.addItem(new LineItem(PRODUCTS[3], QUANTITIES[3]))
				.addItem(new LineItem(PRODUCTS[4], QUANTITIES[4]))
				.addItem(new LineItem(PRODUCTS[5], QUANTITIES[5]))
				.addItem(new LineItem(PRODUCTS[6], QUANTITIES[6]));
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public final void testPerform()
	{
		final Function2<Product, Integer, LineItem> itemCreator = new Function2<Product, Integer, LineItem>() {
			@Override
			public LineItem apply(final Product product, final Integer quantity)
			{
				return new LineItem(product, quantity.intValue());
			}
		};
		@SuppressWarnings("boxing")
		final Order order2 = new Order(1L, new HashSet<LineItem>());
		for (int i = 0; i < PRODUCTS.length; i++)
		{
			order2.addItem(itemCreator.apply(PRODUCTS[i], QUANTITIES[i]));
		}
		assertEquals(order.itemSize(), order2.itemSize());
		assertThat(order2.getItems(), is(equalTo(order.getItems())));
		assertThat(order2, is(equalTo(order)));

		final Customer customer = new Customer("Lee", "Kevin");

		final Function2<Customer, Order, String> orderDetailGenerator = new Function2<Customer, Order, String>() {
			@Override
			public String apply(final Customer customer, final Order order)
			{
				final StringBuilder stringBuilder = new StringBuilder("[Order#: ").append(order.getNumber())
						.append("]\n")
						.append("[Customer: ")
						.append(customer)
						.append("]\n");
				for (final LineItem item : order.getItems())
				{
					stringBuilder.append("[Product: ")
							.append(item.getProduct())
							.append("][Quantity: ")
							.append(item.getQuantity())
							.append("]\n");
				}
				return stringBuilder.append("\n")
						.toString();
			}
		};

		final Set<LineItem> itemSet1 = new HashSet<LineItem>();
		itemSet1.add(new LineItem(PRODUCTS[0], 10));
		itemSet1.add(new LineItem(PRODUCTS[1], 5));
		itemSet1.add(new LineItem(PRODUCTS[2], 50));

		final Set<LineItem> itemSet2 = new HashSet<LineItem>();
		itemSet2.add(new LineItem(PRODUCTS[1], 1));
		itemSet2.add(new LineItem(PRODUCTS[3], 12));
		itemSet2.add(new LineItem(PRODUCTS[4], 7));
		itemSet2.add(new LineItem(PRODUCTS[5], 100));
		itemSet2.add(new LineItem(PRODUCTS[6], 30));

		final Set<LineItem> itemSet3 = new HashSet<LineItem>();
		itemSet3.add(new LineItem(PRODUCTS[2], 3));
		itemSet3.add(new LineItem(PRODUCTS[4], 1000));
		itemSet3.add(new LineItem(PRODUCTS[5], 25));

		final List<Order> orderList =
			asList(new Order(1L, unmodifiableSet(itemSet1)), new Order(2L, unmodifiableSet(itemSet2)), new Order(3L,
					unmodifiableSet(itemSet3)));
		final StringBuilder stringBuilder = new StringBuilder("## Order List\n");
		for (final Order order : orderList)
		{
			stringBuilder.append(orderDetailGenerator.apply(customer, order));
		}
		// System.out.println(stringBuilder.toString());
		assertThat(stringBuilder.toString(), is("## Order List\n" + "[Order#: 1]\n" + "[Customer: Lee, Kevin]\n"
				+ "[Product: Product{number=1,name=Product A}][Quantity: 10]\n"
				+ "[Product: Product{number=2,name=Product B}][Quantity: 5]\n"
				+ "[Product: Product{number=3,name=Product C}][Quantity: 50]\n" + "\n" + "[Order#: 2]\n"
				+ "[Customer: Lee, Kevin]\n" + "[Product: Product{number=2,name=Product B}][Quantity: 1]\n"
				+ "[Product: Product{number=4,name=Product D}][Quantity: 12]\n"
				+ "[Product: Product{number=6,name=Product F}][Quantity: 100]\n"
				+ "[Product: Product{number=5,name=Product E}][Quantity: 7]\n"
				+ "[Product: Product{number=7,name=Product G}][Quantity: 30]\n" + "\n" + "[Order#: 3]\n"
				+ "[Customer: Lee, Kevin]\n" + "[Product: Product{number=3,name=Product C}][Quantity: 3]\n"
				+ "[Product: Product{number=6,name=Product F}][Quantity: 25]\n"
				+ "[Product: Product{number=5,name=Product E}][Quantity: 1000]\n" + "\n"));

		/*
		 * Get all the items with the quantity greater than 10.
		 */
		final List<LineItem> expectedItemList = new ArrayList<LineItem>();
		for (final LineItem item : order.getItems())
		{
			if (10 < item.getQuantity())
			{
				expectedItemList.add(item);
			}
		}

		final Function2<Condition1<LineItem>, Order, List<LineItem>> itemFilter =
			new Function2<Condition1<LineItem>, Order, List<LineItem>>() {
				@Override
				public List<LineItem> apply(final Condition1<LineItem> condition, final Order order)
				{
					final List<LineItem> itemList = new ArrayList<LineItem>();
					for (final LineItem item : order.getItems())
					{
						if (condition.isMet(item))
						{
							itemList.add(item);
						}
					}
					return itemList;
				}
			};

		final List<LineItem> actualItemList = itemFilter.apply(new Condition1<Function2Test.LineItem>() {
			@Override
			public boolean isMet(final LineItem item)
			{
				return 10 < item.getQuantity();
			}
		}, order2);

		assertEquals(expectedItemList.size(), actualItemList.size());
		assertThat(actualItemList, is(equalTo(expectedItemList)));

		/*
		 * Get items the name of which is not "Product E".
		 */
		final List<LineItem> expectedItemList2 = new ArrayList<LineItem>();
		for (final LineItem item : order.getItems())
		{
			if (!"Product E".equals(item))
			{
				expectedItemList2.add(item);
			}
		}

		final List<LineItem> actualItemList2 = itemFilter.apply(new Condition1<Function2Test.LineItem>() {
			@Override
			public boolean isMet(final LineItem item)
			{
				return !"Product E".equals(item);
			}
		}, order2);

		assertEquals(expectedItemList2.size(), actualItemList2.size());
		assertThat(actualItemList2, is(equalTo(expectedItemList2)));
	}
}

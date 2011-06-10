package testdomain;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.*;

import domainmodel.Inventory;
import domainmodel.InventoryItem;
import domainmodel.Product;


public class TestInventory {
	
	private ArrayList<InventoryItem> items;
	
	@Before
	public void setUp(){
		items = new ArrayList<InventoryItem>();
		items.add(new InventoryItem(5,new Product(1, "Soap",new BigDecimal("7.99"))));
		items.add(new InventoryItem(100,new Product(2, "Chair",new BigDecimal("99.99"))));
		items.add(new InventoryItem(25,new Product(3, "Dynamite",new BigDecimal("5000.75"))));
		items.add(new InventoryItem(30,new Product(4, "Food",new BigDecimal("50"))));
	}
	
	@After
	public void clearUp(){
		items.clear();
	}
	
	@Test
	public void testSuccessfulDeclaration(){
		Inventory test = new Inventory(items);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testDeclarationWithEmptyInventoryItems(){
		items.clear();
		Inventory test = new Inventory(items);
	}
	
	@Test
	public void testDeduction(){
		Inventory test = new Inventory(items);
		InventoryItem i = items.get(3);		
		test.deduct(i, 3);
		Assert.assertEquals(27, test.getCompleteInventory().get(3).getQuantity());
		i = items.get(2);		
		test.deduct(i, 20);
		Assert.assertEquals(5, test.getCompleteInventory().get(2).getQuantity());
		test.deduct(i, 2);
		Assert.assertEquals(3, test.getCompleteInventory().get(2).getQuantity());
		i = items.get(0);		
		test.deduct(i, 4);
		Assert.assertEquals(1, test.getCompleteInventory().get(0).getQuantity());
	}
	
	@Test
	public void testCheckSizeOfAvailableItemsInInventory(){
		Inventory test = new Inventory(items);
		Assert.assertEquals(4, test.getAllItemsInStock().size());
		InventoryItem i = items.get(0);		
		test.deduct(i, 5);
		Assert.assertEquals(3, test.getAllItemsInStock().size());
		i = items.get(2);		
		test.deduct(i, 25);
		i = items.get(3);		
		test.deduct(i, 29);
		Assert.assertEquals(2, test.getAllItemsInStock().size());
		test.deduct(i, 1);
		Assert.assertEquals(1, test.getAllItemsInStock().size());
	}
	
}

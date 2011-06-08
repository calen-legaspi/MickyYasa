package testdomain;

import java.math.BigDecimal;

import org.junit.*;

import domainmodel.InventoryItem;
import domainmodel.Product;


public class TestInventoryItem {
	
	Product testProduct;
	
	@Before
	public void setUp(){
		testProduct = new Product(1, "Battery", new BigDecimal("900000"));
	}
	
	@After
	public void clear(){
	}
	
	@Test
	public void testSuccessfulDeclaration(){
		InventoryItem test = new InventoryItem(100,testProduct);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeclarationWithInvalidQuantity(){
		InventoryItem test = new InventoryItem(-1,testProduct);
	}
	
	@Test (expected = NullPointerException.class)
	public void testDeclarationWithNullProduct(){
		InventoryItem test = new InventoryItem(1,null);
	}
	
	@Test
	public void testDeduction(){
		InventoryItem test = new InventoryItem(100,testProduct);
		test.deduct(50);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDeductionWithException(){
		InventoryItem test = new InventoryItem(20,testProduct);
		test.deduct(50);
	}

}

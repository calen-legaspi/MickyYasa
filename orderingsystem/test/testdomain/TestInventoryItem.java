package testdomain;

import java.math.BigDecimal;

import org.junit.*;

import com.onb.domainmodel.InventoryItem;
import com.onb.domainmodel.Product;



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
		Assert.assertEquals(1, test.getSKUNumber());
		Assert.assertEquals(100, test.getQuantity());
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
		Assert.assertEquals(50, test.getQuantity());
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDeductionWithException(){
		InventoryItem test = new InventoryItem(20,testProduct);
		test.deduct(50);
	}

}

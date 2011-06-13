package testdomain;

import java.math.BigDecimal;

import org.junit.*;

import domainmodel.OrderItem;
import domainmodel.Product;


public class TestOrderItem {
	
	private Product testProduct; 
	
	@Before
	public void setUp(){
		testProduct  = new Product(232323, "Detergent", new BigDecimal("5.50"));
	}
	
	@Test
	public void testSuccessfulDeclaration(){
		OrderItem test = new OrderItem(5, testProduct);
		Assert.assertEquals(232323, test.getItemSKUNumber());
		Assert.assertEquals(5, test.getQuantity());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeclarationWithInvalidQuantity(){
		OrderItem test = new OrderItem(0, testProduct);
	}
	
	@Test (expected = NullPointerException.class)
	public void testDeclarationWithNoProduct(){
		OrderItem test = new OrderItem(5, null);
	}

}

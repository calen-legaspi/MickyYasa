package testdomain;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.onb.domainmodel.Product;



public class TestProduct {
	
	@Test
	public void testSuccessfulDeclaration(){
		Product test = new Product (294815, "Lotion", new BigDecimal("343.20"));
		Assert.assertEquals(294815, test.getSKUNumber());
		Assert.assertEquals("Lotion", test.getName());
		Assert.assertEquals(new BigDecimal("343.20"), test.getPrice());
	}
	
	@Test (expected = NullPointerException.class)
	public void testDeclarationWithNoProductName(){
		Product test = new Product(2424, null, new BigDecimal("33.22"));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeclarationWithInvalidSKUNumber(){
		Product test = new Product(-2424, "Hello", new BigDecimal("33.22"));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDeclarationWithEmptyProductName(){
		Product test = new Product(2424, "", new BigDecimal("33.22"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testDeclarationWithNoPrice(){
		Product test = new Product(2424, "Hello", null);
	}
	
	
}

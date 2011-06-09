package resultretrieval;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import domainmodel.Product;

public class ProductRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Product product = new Product(rs.getInt("SKU_Number"), rs.getString("Name"), new BigDecimal(rs.getDouble("PRICE")));
		return product;
	}

}

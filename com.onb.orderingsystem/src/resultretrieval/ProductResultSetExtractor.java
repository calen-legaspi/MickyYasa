package resultretrieval;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import domainmodel.Product;

public class ProductResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet arg0) throws SQLException,
			DataAccessException {		
		return new Product(arg0.getInt("SKU_Number"), arg0.getString("Name"), new BigDecimal(arg0.getDouble("PRICE")));
	}

}

package resultretrieval;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet arg0, int arg1) throws SQLException {
		ProductResultSetExtractor extractor = new ProductResultSetExtractor();
		return extractor.extractData(arg0);
	}

}

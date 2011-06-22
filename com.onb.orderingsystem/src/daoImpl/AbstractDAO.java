package daoImpl;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.CustomerDAO;

public class AbstractDAO {

	private JdbcTemplate jdbcTemplate;

	public AbstractDAO() {
		super();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
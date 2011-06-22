package com.onb.daos.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.onb.daos.CustomerDAO;


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
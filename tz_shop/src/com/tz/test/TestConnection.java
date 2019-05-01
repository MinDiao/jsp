package com.tz.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.hpe.util.JdbcUtils;

public class TestConnection {

	@Test
	public void test() throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		System.out.println(connection);
	}

}

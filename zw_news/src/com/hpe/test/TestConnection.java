package com.hpe.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.hpe.util.JdbcUtils;

public class TestConnection {

	@Test
	public void test() throws SQLException {
		// 测试数据库连接
		Connection connection = JdbcUtils.getConnection();
		System.out.println(connection);
	}

}

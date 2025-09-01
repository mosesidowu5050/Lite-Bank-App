package com.litebank;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class LiteBankApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testCanConnectToDatabase(){
		try (HikariDataSource hds = new HikariDataSource()) {
			hds.setJdbcUrl("jdbc:postgresql://localhost/lite_bank_db");
			hds.setUsername("postgres");
			hds.setPassword("0285");
			Connection connection = hds.getConnection();
			
			assertNotNull(connection);
		} catch (SQLException e) {
			assertNull(e);
		}
	}
}

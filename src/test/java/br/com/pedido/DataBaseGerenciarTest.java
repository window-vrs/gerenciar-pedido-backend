package br.com.pedido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DataBaseGerenciarTest {
	
	static Connection connection;
	

	@BeforeAll
	static void setUpDatabase() throws SQLException {
		connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(255))");
	}
	
	
	@Test
	void insertUserTest() throws SQLException {
		connection.createStatement().execute("INSERT INTO users (id, name) VALUES (1, 'John Doe')");
	}

}

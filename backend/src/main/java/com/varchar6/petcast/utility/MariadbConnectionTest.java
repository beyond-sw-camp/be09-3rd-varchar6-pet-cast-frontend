package com.varchar6.petcast.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MariadbConnectionTest {

        public static void main(String[] args) {
            String url = "jdbc:mariadb://localhost:3306/testdb";  // DB URL
            String user = "root";  // DB 사용자 이름
            String password = "your_password";  // DB 비밀번호

            try {
                Class.forName("org.mariadb.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection successful!");
            } catch (ClassNotFoundException e) {
                System.out.println("MariaDB JDBC Driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
    }

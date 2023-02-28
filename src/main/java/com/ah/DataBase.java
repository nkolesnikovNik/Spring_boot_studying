package com.ah;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {

    //URL for h2 bd
    static String dbUrl = "jdbc:h2:~/test";

    //Connection to the h2 bd
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(dbUrl);
            //Create table
            PreparedStatement table = conn.prepareStatement("CREATE TABLE IF NOT EXISTS warehouses (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	capacity real\n"
                    + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Save the URL and hash
    String sql = "INSERT INTO urls (url, hash) VALUES (?, ?)";
    PreparedStatement stmt = conn.prepareStatement(sql);

    public DataBase() throws SQLException {
    }

    //Generate hash from URL
    public static String getHash(String url) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(url.getBytes());
        byte[] digest = md.digest();
        return new String(digest);
    }

    //Save the URL and hash
    public String saveUrl(String url) throws Exception {
        String hash = getHash(url);
        stmt.setString(1, url);
        stmt.setString(2, hash);
        stmt.executeUpdate();
        return hash;
    }
}

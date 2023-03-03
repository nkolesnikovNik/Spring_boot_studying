package com.ah;

import java.sql.*;

public class DataBase {

    //URL for h2 bd
    static String dbUrl = "jdbc:h2:~/test";

    Hasher hasher = new Hasher();

    //Connection to the h2 bd
    Connection conn;

    //Save the URL and hash
    String sqlInsert = "INSERT INTO urls (url, hash) VALUES (?, ?)";

    //Save the URL and hash
    String sqlSelect = "SELECT url FROM urls where hash=?";

    public DataBase() throws SQLException {
        try {
            conn = DriverManager.getConnection(dbUrl);
            //Create table
            PreparedStatement table = conn.prepareStatement("create table if not exists urls(" +
                    " id bigint auto_increment," +
                    " url varchar(255)," +
                    " hash varchar(255))");
            table.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Save the URL and hash
    public String saveUrl(String url) throws Exception {
        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        String hash = hasher.getHash(url);
        stmt.setString(1, url);
        stmt.setString(2, hash);
        stmt.executeUpdate();
        return hash;
    }

    public String getUrl(String url) throws Exception {
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, url);
        ResultSet resultSet = stmt.executeQuery();
        resultSet.next();
        return resultSet.getString("url");
    }
}

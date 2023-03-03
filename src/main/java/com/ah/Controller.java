package com.ah;

import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Objects;

public class Controller {

    static DataBase dataBase;

    static {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Controller() throws SQLException {
    }

    public static void main(String[] args) {
    }

    @NotNull
    public String saveUrl(Context ctx) throws Exception {
        String url = ctx.queryParam("url");
        return Objects.requireNonNull(dataBase.saveUrl(url));
    }

    @NotNull
    public String getUrl(Context ctx) throws Exception {
        String url = ctx.queryParam("hash");
        return Objects.requireNonNull(dataBase.getUrl(url));
    }
}
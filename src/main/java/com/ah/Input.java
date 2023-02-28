package com.ah;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Objects;

public class Input {

    static DataBase dataBase;

    static {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Input() throws SQLException {
    }

    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .get("/hashUrl", ctx -> getUrl(ctx))
                .start(7070);
    }

    @NotNull
    private static Context getUrl(Context ctx) throws Exception {
        String url = ctx.queryParam("url");
        return ctx.result(Objects.requireNonNull(dataBase.saveUrl(url)));
    }
}
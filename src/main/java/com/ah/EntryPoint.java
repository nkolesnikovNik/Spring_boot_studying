package com.ah;

import io.javalin.Javalin;

import java.sql.SQLException;

public class EntryPoint {

    public EntryPoint() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller();
        Javalin app = Javalin.create(/*config*/)
                //First request
                .get("/hashUrl", ctx -> ctx.result(controller.saveUrl(ctx)))
                //Second request
                .get("/takeUrl", ctx -> ctx.result(controller.getUrl(ctx)))
                .start(7070);
    }
}

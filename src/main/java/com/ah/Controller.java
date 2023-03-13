package com.ah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Controller {

    @Autowired
    private Database database;

    @RequestMapping("hashUrl")
    public String saveUrl(@RequestParam String url) throws Exception {
        return Objects.requireNonNull(database.saveUrl(url));
    }

    @RequestMapping("takeUrl")
    public String getUrl(@RequestParam String hash) throws Exception {
        return database.getUrl(hash);
    }
}
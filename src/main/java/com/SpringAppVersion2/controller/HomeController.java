package com.SpringAppVersion2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final String INDEX = "index";
    private static final String POOL_PROJECT = "pool-project";
    private static final String HOME_PAGE = "home-page";
    private static final String BOOK_SPECIFIC_TABLE = "book-specific-table";
    private static final String SEE_ALL_TABLES = "see-all-tables";
    private static final String STATUS_POOL_TABLE = "status-pool-table";
    private static final String RESULT = "result";

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }


}

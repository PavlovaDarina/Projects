package com.telerikacademy.api.tests;


import static java.lang.String.format;

public class Endpoints {

    public static final String API_VERSION = "/rest/api/2";
    public static final String SEARCH_ENDPOINT = format("%s%s", API_VERSION, "/search");
    public static final String USER_SEARCH_ENDPOINT = format("%s%s", API_VERSION, "/user/search");
    public static final String ISSUE_ENDPOINT = format("%s%s", API_VERSION, "/issue");
}

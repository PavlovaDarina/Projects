package com.telerikacademy.api.tests;

public class JSONRequests {

    public static final String ISSUE = "{\n" +
            "    \"fields\": {\n" +
            "       \"project\":\n" +
            "       {\n" +
            "          \"key\": \"%s\"\n" +
            "       },\n" +
            "       \"summary\": \"%s\",\n" +
            "       \"description\": \"%s\",\n" +
            "       \"issuetype\": {\n" +
            "          \"name\": \"%s\"\n" +
            "       }\n" +
            "   }\n" +
            "}";

    public static final String ASSIGNEE_ACCOUNT_ID = "{\n" +
            "    \"fields\": {\n" +
            "        \"assignee\": {\n" +
            "            \"accountId\": \"%s\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    public static final String ASSIGNEE_ID = "{\n" +
            "    \"fields\": {\n" +
            "        \"assignee\": {\n" +
            "            \"id\": \"%s\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    public static final String PRIORITY = "{\n" +
            "    \"fields\": {\n" +
            "        \"priority\": {\n" +
            "            \"name\": \"%s\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    public static final String BLOCK_ISSUE = "{\n" +
            "    \"update\": {\n" +
            "        \"issuelinks\": [\n" +
            "            {\n" +
            "                \"add\": {\n" +
            "                    \"type\": {\n" +
            "                        \"name\": \"Blocks\",\n" +
            "                        \"inward\": \"is blocked by\",\n" +
            "                        \"outward\": \"blocks\"\n" +
            "                    },\n" +
            "                    \"outwardIssue\": {\n" +
            "                        \"key\": \"%s\"\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
}

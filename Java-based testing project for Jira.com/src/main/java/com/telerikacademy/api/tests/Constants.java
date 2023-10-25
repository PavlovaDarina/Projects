package com.telerikacademy.api.tests;


public class Constants {

    public static final String EMAIL = "darina.pavlova@telerik.academy";
    public static final String JIRA_API_TOKEN = "";
    public static final String BASE_URL = "https://xray-demo-project-a50.atlassian.net";
    public static final String PROJECT_KEY = "XD";
    public static final String STORY_SUMMARY = "Create JIRA story homework";
    public static final String BUG_SUMMARY = "Staging forum is not available";
    public static final String STORY_NAME = "Story";
    public static final String BUG_NAME = "Bug";
    public static final String BUG_PRIORITY = "Highest";
    public static final String STORY_MULTILINE_DESCRIPTION = "Use your free JIRA projects to practice testing Web Services\\n\\n"
            + "*Task 1*\\n"
            + "Create a story in JIRA via JIRA API and Postman\\n"
            + "* Use Base Auth with using the JIRA API token\\n"
            + "* Follow the best principles in creating a story\\n"
            + "* Set priority based on the severity\\n\\n"
            + "*Task 2*\\n"
            + "Create a bug in JIRA via JIRA API and Postman\\n"
            + "* Use Base Auth with using the JIRA API token\\n"
            + "* Follow the best principles in creating a bug\\n"
            + "* Set priority based on the severity\\n\\n"
            + "*Task 3*\\n"
            + "Link the bug to the story in JIRA via JIRA API and Postman\\n"
            + "* Use Base Auth with using the JIRA API token\\n"
            + "* Use relation 'is blocked by'\\n\\n"
            + "* Hints and what don't forget to have:*\\n"
            + "* Meaningful Title\\n"
            + "* Test steps/Steps to reproduce\\n"
            + "* Expected vs. Actual result\\n"
            + "* Severity/Priority\\n"
            + "* Prerequisites\\n"
            + "* Additional info\\n"
            + "* Classifications \\n"
            + "* Others as screenshots, an assignee, transitions and everything useful";
    public static final String BUG_DESCRIPTION = "*Steps to reproduce:*\\n"
            + "1. Navigate to the main page - https://stage-forum.telerikacademy.com\\n"
            + "*Expected result:* \\n The staging forum is loaded successfully \\n"
            + "*Actual result:* Staging forum is not available and return error: \\\"We can't connect to the server at \\\"stage-forum.telerikacademy.com.\\\" \\n"
            + "*Severity:* Blocking \\n"
            + "*Prerequisites:* N/A \\n !https://upload.wikimedia.org/wikipedia/commons/f/f8/Science_2.0_model.png!";
    public static String DEFAULT_ACCOUNT_ID = "";
    public static String ACCOUNT_ID = "";
    public static String STORY_KEY;
    public static String BUG_KEY;
}



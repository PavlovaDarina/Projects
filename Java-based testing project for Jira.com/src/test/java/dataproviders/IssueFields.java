package dataproviders;


import org.testng.annotations.DataProvider;

import static com.telerikacademy.api.tests.Constants.*;

public class IssueFields {

    @DataProvider
    public static Object[][] fieldsPerIssue() {
        Object[][] dataset = new Object[2][5];

        dataset[0][0] = "Create a story";
        dataset[0][1] = PROJECT_KEY;
        dataset[0][2] = STORY_SUMMARY;
        dataset[0][3] = STORY_MULTILINE_DESCRIPTION;
        dataset[0][4] = STORY_NAME;

        dataset[1][0] = "Create a bug";
        dataset[1][1] = PROJECT_KEY;
        dataset[1][2] = BUG_SUMMARY;
        dataset[1][3] = BUG_DESCRIPTION;
        dataset[1][4] = BUG_NAME;

        return dataset;
    }
}

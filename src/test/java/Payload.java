public class Payload {


    public  static String getSessionPayload() {
        return "{\n" +
                "\t\"username\":\"rsb3101\",\n" +
                "\t\"password\": \"Mann$1234\"\n" +
                "\t\n" +
                "}";

    }
        public static String getCreateIssuePayload(){
            return "{\n" +
                    "    \"fields\": {\n" +
                    "       \"project\":\n" +
                    "       {\n" +
                    "          \"key\": \"OZ\"\n" +
                    "       },\n" +
                    "       \"summary\": \"Creating the payload using REST API\",\n" +
                    "       \"description\": \"Description is not necessary\",\n" +
                    "       \"issuetype\": {\n" +
                    "          \"name\": \"Story\"\n" +
                    "       }\n" +
                    "   }\n" +
                    "}";
        }

    public static String getAddCommentPayload(){
        return "{\"body\": \"This is a comment regarding the quality of the response. From Raghvendra\"}";
    }



}

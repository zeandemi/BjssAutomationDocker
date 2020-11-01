package ApiUtility;

import io.restassured.path.json.JsonPath;

public class Payloads {
    private static JsonPath jsonPath;

    public static String createPayload = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";
    public static String updatedPayload = "{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"zion residents\"\n" +
            "}";

    public static void jsonResponse(String response) {
        jsonPath = new JsonPath(response);
        PayLoadDoc.responseName = jsonPath.getString("name");
        PayLoadDoc.responseJob = jsonPath.getString("job");
        PayLoadDoc.responseId = jsonPath.getString("id");
        PayLoadDoc.responseCreatedAt = jsonPath.getString("createdAt");

    }

    public static String jsonPatchResponse(String rawToString, String parameter) {
        jsonPath = new JsonPath(rawToString);
        String result = jsonPath.getString(parameter);
        System.out.println(result);
        return result;
    }

    public static String stringToJsonResponse(String response) {
        jsonPath = new JsonPath(response);
        String id = jsonPath.getString("data.id");
        return id;
    }
}

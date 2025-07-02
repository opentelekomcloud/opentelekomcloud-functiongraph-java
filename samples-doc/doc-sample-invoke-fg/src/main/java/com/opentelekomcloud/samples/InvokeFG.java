package com.opentelekomcloud.samples;

import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InvokeFG {

  private static final Gson gsonPrettyPrint = new GsonBuilder().setPrettyPrinting().create();

  /**
   * Get user token through username/password-based authentication
   * 
   * @see https://docs.otc.t-systems.com/function-graph/api-ref/calling_apis/making_an_api_request.html
   * @return token
   * @throws Exception
   */
  private static String getAuthToken() throws Exception {

    HttpClient client = HttpClient.newBuilder()
        .proxy(ProxySelector.getDefault()) // use system default proxy
        .build();

    // prettier-ignore
    String authBody = ""
        + "{"
        + "   \"auth\": {"
        + "    \"identity\": {"
        + "       \"methods\": [ \"password\" ],"
        + "       \"password\": {"
        + "         \"user\": {"
        + "           \"name\": \"" + Constants.getInstance().getUserName() + "\","
        + "           \"password\": \"" + Constants.getInstance().getUserPassword() + "\","
        + "           \"domain\": { \"name\": \"" + Constants.getInstance().getAccountName() + "\"}"
        + "         }"
        + "       }"
        + "    },"
        + "    \"scope\": { \"domain\": { \"name\": \"" + Constants.getInstance().getAccountName() + "\" },"
        + "                 \"project\": { \"id\": \"" + Constants.getInstance().getProjectId() + "\" }"
        + "    }"
        + "  }"
        + "}";

    System.out.println(authBody);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(Constants.getInstance().getTokenUri()))
        .header("Content-Type", "application/json;charset=utf8")
        .POST(BodyPublishers.ofString(authBody))
        .build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    if (response.statusCode() != 201) {

      switch (response.statusCode()) {
        case 401:
          throw new Exception(
              String.format("Get Token failed with: %s", "Unauthorized"));
        default:
          JsonElement jsonElement = JsonParser.parseString(response.body());

          JsonElement error_code = jsonElement.getAsJsonObject().get("error_code");
          JsonElement error_msg = jsonElement.getAsJsonObject().get("error_msg");

          throw new Exception(
              String.format("Get Token failed with: %s, %s, %s", response.statusCode(), error_code, error_msg));
      }
    }

    System.out.println(response.body());

    Optional<String> token = response.headers().firstValue("x-subject-token");

    return token.get();

  }

  public static void main(String[] args) {

    try {
      String token = getAuthToken();

      HttpClient client = HttpClient.newBuilder()
          .proxy(ProxySelector.getDefault())
          .build();

      String invokeUri = Constants.getInstance().getInvokeUri("InvokeSamplePython");

      String requestBody = "{ \"key\": \"Hello world of OpenTelekomCloud\"}";

      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(invokeUri))
          .header("Content-Type", "application/json;charset=utf8")
          .header("X-AUTH-Token", token)
          // X-Cff-Log-Type:
          // "tail": 4KB logs will be returned
          // "": no logs will be returned
          .header("X-Cff-Log-Type", "tail")
          // X-CFF-Request-Version:
          // "v0" response body in text format
          // "v1" response body in json format
          .header("X-CFF-Request-Version", "v1")
          .POST(BodyPublishers.ofString(requestBody))
          .build();

      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

      System.out.println("Status code of invoke request: " + response.statusCode());

      JsonElement jsonElement = JsonParser.parseString(response.body());

      System.out.println("Complete response:");
      System.out.println("==================");
      System.out.println(gsonPrettyPrint.toJson(jsonElement));

      JsonElement result = jsonElement.getAsJsonObject().get("result");

      JsonObject o = JsonParser.parseString(result.getAsString()).getAsJsonObject();
      System.out.println("Response body:");
      System.out.println("==============");
      System.out.println(o.get("body").getAsString());

      JsonElement log = jsonElement.getAsJsonObject().get("log");
      System.out.println("Log:");
      System.out.println("==============");
      System.out.println(log.getAsString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

package com.spribe.clients;

import com.spribe.ConfigManager;
import com.spribe.filters.LoggingFilter;
import com.spribe.models.ResponseData;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public abstract class BaseClient {

    private static final String BASE_URL = ConfigManager.getBaseUrl();
    private static final int CONNECTION_TIMEOUT = ConfigManager.getConnectionTimeout();
    private static final int SOCKET_TIMEOUT = ConfigManager.getSocketTimeout();

    static {
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", CONNECTION_TIMEOUT)
                        .setParam("http.socket.timeout", SOCKET_TIMEOUT));
        RestAssured.filters(new LoggingFilter());
    }

    private ValidatableResponse sendRequest(
            Method method, String endpoint, Object requestBody,
            Map<String, String> queryParams, int expectedStatusCode
    ) {
        RequestSpecification requestSpecification = given().baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        if (method == Method.GET) {
            return requestSpecification
                    .queryParams(queryParams)
                    .when()
                    .get(endpoint)
                    .then()
                    .statusCode(expectedStatusCode);
        } else {
            return requestSpecification
                    .body(requestBody)
                    .when()
                    .request(method, endpoint)
                    .then()
                    .statusCode(expectedStatusCode);
        }
    }

    protected ResponseData get(String endpoint, HashMap<String, String> queryParams, int statusCode) {
        return new ResponseData(sendRequest(Method.GET, endpoint, null, queryParams, statusCode));
    }

    protected <T> ResponseData post(String endpoint, T requestBody, int expectedStatusCode) {
        return new ResponseData(sendRequest(Method.POST, endpoint, requestBody, null, expectedStatusCode));
    }

    protected <T> ResponseData patch(String endpoint, T requestBody, int expectedStatusCode) {
        return new ResponseData(sendRequest(Method.PATCH, endpoint, requestBody, null, expectedStatusCode));
    }

    protected <T> ResponseData delete(String endpoint, T requestBody, int statusCode) {
        return new ResponseData(sendRequest(Method.DELETE, endpoint, requestBody, null, statusCode));
    }

}

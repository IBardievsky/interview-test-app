package com.spribe.clients;

import com.spribe.ConfigManager;
import com.spribe.filters.LoggingFilter;
import com.spribe.models.ResponseData;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public abstract class BaseClient {

    private static final String BASE_URL = ConfigManager.getBaseUrl();

    static {
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 12000)
                        .setParam("http.socket.timeout", 12000));
        RestAssured.filters(new LoggingFilter());
        RestAssured.defaultParser = Parser.JSON;
    }

    private RequestSpecification request() {
        return given().baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    protected ResponseData get(String endpoint, HashMap<String, String> queryParams, int statusCode) {
        return new ResponseData(
                request()
                        .queryParams(queryParams)
                        .when()
                        .get(endpoint)
                        .then()
                        .statusCode(statusCode)
        );
    }

    protected <T> ResponseData post(String endpoint, T requestBody, int expectedStatusCode) {
        return new ResponseData(
                request()
                        .body(requestBody)
                        .when()
                        .post(endpoint)
                        .then()
                        .statusCode(expectedStatusCode)
        );
    }

    protected <T> ResponseData patch(String endpoint, T requestBody, int expectedStatusCode) {
        return new ResponseData(
                request()
                        .body(requestBody)
                        .when()
                        .patch(endpoint)
                        .then()
                        .statusCode(expectedStatusCode)
        );
    }

    protected <T> ResponseData delete(String endpoint, T requestBody, int statusCode) {
        return new ResponseData(
                request()
                        .body(requestBody)
                        .when()
                        .delete(endpoint)
                        .then()
                        .statusCode(statusCode)
        );
    }


}

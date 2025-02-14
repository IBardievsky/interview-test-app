package com.spribe.clients;

import com.spribe.Config;
import com.spribe.filters.LoggingFilter;
import com.spribe.models.RequestModel;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public abstract class BaseClient {

    private static final String BASE_URL = Config.getBaseUrl();
    private static final String CONNECTION_TIMEOUT = Config.getConnectionTimeout();

    static {
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", CONNECTION_TIMEOUT)
                        .setParam("http.socket.timeout", CONNECTION_TIMEOUT));
        RestAssured.filters(new LoggingFilter());
    }

    private RequestSpecification request() {
        return given().baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    protected Response get(String endpoint, HashMap<String, Object> queryParams) {
        return request()
                .queryParams(queryParams)
                .when()
                .get(endpoint);
    }

    protected <T extends RequestModel> Response post(String endpoint, T requestBody) {
        return request()
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    protected <T extends RequestModel> Response put(String endpoint, T requestBody) {
        return request()
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    protected <T extends RequestModel> Response patch(String endpoint, T requestBody) {
        return request()
                .body(requestBody)
                .when()
                .patch(endpoint);
    }

    protected <T extends RequestModel> Response delete(String endpoint, T requestBody) {
        return request()
                .body(requestBody)
                .when()
                .delete(endpoint);
    }
}

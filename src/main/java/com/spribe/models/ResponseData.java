package com.spribe.models;

import io.restassured.response.ValidatableResponse;

public class ResponseData {

    private final ValidatableResponse response;

    public ResponseData(ValidatableResponse response) {
        this.response = response;
    }

    public <T> T as(Class<T> clazz) {
        return response.extract().as(clazz);
    }

    public ValidatableResponse asResponse() {
        return response;
    }
}

package com.spribe.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        logRequest(requestSpec);
        Response response = ctx.next(requestSpec, responseSpec);
        logResponse(response);
        return response;
    }

    private void logRequest(FilterableRequestSpecification requestSpec) {
        logger.info("Request: {} {} \n Headers: {} \n Cookies: {}",
                requestSpec.getMethod(), requestSpec.getURI(), requestSpec.getHeaders(), requestSpec.getCookies());
        if (requestSpec.getBody() != null) {
            logger.info("Request Body: {}", requestSpec.getBody().toString());
        }
    }

    private void logResponse(Response response) {
        logger.info("Response Status: {} \n Response Body: {}",
                response.getStatusCode(), response.getBody().asString());
    }
}


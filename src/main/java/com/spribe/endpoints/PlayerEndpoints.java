package com.spribe.endpoints;

public interface PlayerEndpoints {

    String BASE_PATH = "/player";

    String CREATE_PLAYER_URL = BASE_PATH + "/create/%s";

    String DELETE_PLAYER_URL = BASE_PATH + "/delete/%s";

    String GET_PLAYER_URL = BASE_PATH + "/get";

    String GET_ALL_PLAYERS_URL = GET_PLAYER_URL + "/all";

    String UPDATE_PLAYER_URL = BASE_PATH + "/update/%s/%s";

}

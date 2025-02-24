package com.spribe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayerEndpoints {

    CREATE_PLAYER_URL("/player/create/%s"),

    DELETE_PLAYER_URL("/player/delete/%s"),

    GET_PLAYER_URL("/player/get"),

    GET_ALL_PLAYERS_URL("/player/get/all"),

    UPDATE_PLAYER_URL("/player/update/%s/%s");

    private final String url;

}

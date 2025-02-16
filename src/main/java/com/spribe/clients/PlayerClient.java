package com.spribe.clients;

import com.spribe.enums.Role;
import com.spribe.models.ResponseData;
import com.spribe.models.request.PlayerDeleteRequestDto;
import com.spribe.models.request.PlayerGetByPlayerIdRequestDto;
import com.spribe.models.request.PlayerCreateDto;
import com.spribe.models.response.ErrorResponseDto;
import com.spribe.models.response.PlayerGetAllResponseDto;

import java.util.HashMap;
import java.util.Map;

import static com.spribe.endpoints.PlayerEndpoints.*;
import static java.lang.String.*;
import static java.util.Objects.nonNull;

public class PlayerClient extends BaseClient {

    public PlayerCreateDto getPlayerById(PlayerGetByPlayerIdRequestDto dto, int expectedStatusCode) {
        return post(GET_PLAYER_URL, dto, expectedStatusCode).as(PlayerCreateDto.class);
    }

    public PlayerCreateDto createPlayer(Role editor, PlayerCreateDto dto, int expectedStatusCode) {
        HashMap<String, String> queryParams = new HashMap<>();
        addToMapIfNotNull(queryParams, "age", dto.getAge().toString());
        addToMapIfNotNull(queryParams, "gender", dto.getGender());
        addToMapIfNotNull(queryParams, "login", dto.getLogin());
        addToMapIfNotNull(queryParams, "password", dto.getPassword());
        addToMapIfNotNull(queryParams, "role", dto.getRole());
        addToMapIfNotNull(queryParams, "screenName", dto.getScreenName());

        String createPathUrl = format(CREATE_PLAYER_URL, editor.name().toLowerCase());
        ResponseData response = get(createPathUrl, queryParams, expectedStatusCode);

        if (response.asResponse().extract().statusCode() >= 400) {
            return response.as(PlayerCreateDto.class);
        } else {
            throw new AssertionError("");
        }
    }

    private void addToMapIfNotNull(Map<String, String> map, String key, String value) {
        if (nonNull(value)) {
            map.put(key, value);
        }
    }

    public PlayerGetAllResponseDto getAllPlayers(int expectedStatusCode) {
        return get(GET_ALL_PLAYERS_URL, new HashMap<>(), expectedStatusCode).as(PlayerGetAllResponseDto.class);
    }

    public ErrorResponseDto getAllPlayersWithPostMethod(int expectedStatusCode) {
        return post(GET_ALL_PLAYERS_URL, PlayerCreateDto.builder().build(), expectedStatusCode).as(ErrorResponseDto.class);
    }

    public ResponseData deletePlayerById(Role editor, PlayerDeleteRequestDto dto, int expectedStatusCode) {
        String deletePathUrl = format(DELETE_PLAYER_URL, editor.name().toLowerCase());
        return delete(deletePathUrl, dto, expectedStatusCode);
    }

    public PlayerCreateDto updatePlayer(Role editor, long playerId, PlayerCreateDto dto, int expectedStatusCode) {
        String updatePathUrl = format(UPDATE_PLAYER_URL, editor.name().toLowerCase(), playerId);
        return patch(updatePathUrl, dto, expectedStatusCode).as(PlayerCreateDto.class);
    }
}

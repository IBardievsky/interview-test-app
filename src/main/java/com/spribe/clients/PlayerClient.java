package com.spribe.clients;

import com.spribe.enums.Role;
import com.spribe.models.request.PlayerDeleteRequestDto;
import com.spribe.models.request.PlayerGetByPlayerIdRequestDto;
import com.spribe.models.request.PlayerCreateDto;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.spribe.endpoints.PlayerEndpoints.*;
import static java.lang.String.*;
import static java.util.Objects.nonNull;

public class PlayerClient extends BaseClient {

    public Response getPlayerById(PlayerGetByPlayerIdRequestDto dto) {
        return post(GET_PLAYER_URL, dto);
    }

    public Response createPlayer(Role editor, PlayerCreateDto dto) {
        HashMap<String, Object> queryParams = new HashMap<>();
        addToMapIfNotNull(queryParams, "age", dto.getAge().toString());
        addToMapIfNotNull(queryParams, "gender", dto.getGender());
        addToMapIfNotNull(queryParams, "login", dto.getLogin());
        addToMapIfNotNull(queryParams, "password", dto.getPassword());
        addToMapIfNotNull(queryParams, "role", dto.getRole());
        addToMapIfNotNull(queryParams, "screenName", dto.getScreenName());

        String createPathUrl = format(CREATE_PLAYER_URL, editor.name().toLowerCase());
        return get(createPathUrl, queryParams);
    }

    private void addToMapIfNotNull(Map<String, Object> map, String key, Object value) {
        if (nonNull(value)) {
            map.put(key, value);
        }
    }

    public Response getAllPlayers() {
        return get(GET_ALL_PLAYERS_URL, new HashMap<>());
    }

    public Response getAllPlayersWithPostMethod() {
        return post(GET_ALL_PLAYERS_URL, PlayerCreateDto.builder().build());
    }

    public Response deletePlayerById(Role editor, PlayerDeleteRequestDto dto) {
        String deletePathUrl = format(DELETE_PLAYER_URL, editor.name().toLowerCase());
        return delete(deletePathUrl, dto);
    }
}

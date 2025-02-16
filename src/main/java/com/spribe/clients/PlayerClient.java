package com.spribe.clients;

import com.spribe.enums.Role;
import com.spribe.models.ResponseData;
import com.spribe.models.request.PlayerDeleteRequestDto;
import com.spribe.models.request.PlayerGetByPlayerIdRequestDto;
import com.spribe.models.request.PlayerItemDto;
import com.spribe.models.response.ErrorResponseDto;
import com.spribe.models.response.PlayerGetAllResponseDto;

import java.util.HashMap;
import java.util.Map;

import static com.spribe.endpoints.PlayerEndpoints.*;
import static java.lang.String.*;
import static java.util.Objects.nonNull;

public class PlayerClient extends BaseClient {

    public PlayerItemDto getPlayerById(PlayerGetByPlayerIdRequestDto dto, int expectedStatusCode) {
        return post(GET_PLAYER_URL, dto, expectedStatusCode).as(PlayerItemDto.class);
    }

    public ResponseData createPlayer(Role editor, PlayerItemDto dto, int expectedStatusCode) {
        HashMap<String, String> queryParams = new HashMap<>();
        addToMapIfNotNull(queryParams, "age", dto.getAge().toString());
        addToMapIfNotNull(queryParams, "gender", dto.getGender());
        addToMapIfNotNull(queryParams, "login", dto.getLogin());
        addToMapIfNotNull(queryParams, "password", dto.getPassword());
        addToMapIfNotNull(queryParams, "role", dto.getRole());
        addToMapIfNotNull(queryParams, "screenName", dto.getScreenName());

        String createPathUrl = format(CREATE_PLAYER_URL, editor.name().toLowerCase());
        return get(createPathUrl, queryParams, expectedStatusCode);
    }

    public PlayerItemDto createPlayer(Role editor, PlayerItemDto dto) {
        return createPlayer(editor, dto, 200).as(PlayerItemDto.class);
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
        return post(GET_ALL_PLAYERS_URL, PlayerItemDto.builder().build(), expectedStatusCode).as(ErrorResponseDto.class);
    }

    public ResponseData deletePlayerById(Role editor, PlayerDeleteRequestDto dto, int expectedStatusCode) {
        String deletePathUrl = format(DELETE_PLAYER_URL, editor.name().toLowerCase());
        return delete(deletePathUrl, dto, expectedStatusCode);
    }

    public PlayerItemDto updatePlayer(Role editor, long playerId, PlayerItemDto dto, int expectedStatusCode) {
        String updatePathUrl = format(UPDATE_PLAYER_URL, editor.name().toLowerCase(), playerId);
        return patch(updatePathUrl, dto, expectedStatusCode).as(PlayerItemDto.class);
    }
}

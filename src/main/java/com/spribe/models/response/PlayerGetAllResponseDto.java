package com.spribe.models.response;

import lombok.Data;

import java.util.List;

@Data
public class PlayerGetAllResponseDto {
    private List<PlayerItemResponseDto> players;
}

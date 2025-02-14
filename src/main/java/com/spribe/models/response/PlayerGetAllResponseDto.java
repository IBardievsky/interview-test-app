package com.spribe.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Data
public class PlayerGetAllResponseDto {
    private List<PlayerItemResponseDto> players;
}

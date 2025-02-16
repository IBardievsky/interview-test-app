package com.spribe.models.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlayerGetByPlayerIdRequestDto {
     private Long playerId;
}

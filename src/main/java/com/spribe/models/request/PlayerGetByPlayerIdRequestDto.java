package com.spribe.models.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerGetByPlayerIdRequestDto {
     private Long playerId;
}

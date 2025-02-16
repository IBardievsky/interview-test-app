package com.spribe.models.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDeleteRequestDto {
    private Long playerId;
}

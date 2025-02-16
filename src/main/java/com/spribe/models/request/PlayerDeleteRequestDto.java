package com.spribe.models.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDeleteRequestDto {
    private Long playerId;
}

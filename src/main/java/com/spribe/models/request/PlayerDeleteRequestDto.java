package com.spribe.models.request;

import com.spribe.models.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PlayerDeleteRequestDto implements RequestModel {
    Long playerId;
}

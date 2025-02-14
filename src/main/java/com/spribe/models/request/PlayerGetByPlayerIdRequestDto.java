package com.spribe.models.request;

import com.spribe.models.RequestModel;
import lombok.*;

@Value
@Builder
@With
@AllArgsConstructor
public class PlayerGetByPlayerIdRequestDto implements RequestModel {
     Long playerId;
}

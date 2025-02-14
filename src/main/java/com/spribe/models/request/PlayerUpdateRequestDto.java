package com.spribe.models.request;

import com.spribe.enums.Gender;
import com.spribe.enums.Role;
import com.spribe.models.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PlayerUpdateRequestDto implements RequestModel {
    Long age;
    String gender;
    String login;
    String password;
    String role;
    String screenName;
}

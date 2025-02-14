package com.spribe.models.response;

import com.spribe.enums.Gender;
import com.spribe.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
public class PlayerUpdateResponseDto {
    private Integer age;
    private String gender;
    private Long id;
    private String login;
    private String role;
    private String screenName;
}

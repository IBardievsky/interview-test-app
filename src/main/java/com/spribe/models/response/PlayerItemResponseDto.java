package com.spribe.models.response;

import com.spribe.enums.Gender;
import com.spribe.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
public class PlayerItemResponseDto {
    private Long id;
    private String screenName;
    private String gender;
    private Integer age;
    private String role;
}

package com.spribe.models.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlayerUpdateRequestDto {
    private Long age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;
}

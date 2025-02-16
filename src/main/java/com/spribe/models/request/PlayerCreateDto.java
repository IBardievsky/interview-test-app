package com.spribe.models.request;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreateDto implements RequestModel {
    private Long id;
    private String gender;
    private Integer age;
    private String login;
    private String password;
    private String role;
    private String screenName;
}

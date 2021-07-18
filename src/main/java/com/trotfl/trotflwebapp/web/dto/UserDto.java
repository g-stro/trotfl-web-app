package com.trotfl.trotflwebapp.web.dto;

import lombok.*;
/**
 * @author Greg Stroud
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String newPassword;
}

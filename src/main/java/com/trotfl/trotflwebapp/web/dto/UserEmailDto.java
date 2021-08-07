package com.trotfl.trotflwebapp.web.dto;

import com.trotfl.trotflwebapp.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Greg Stroud
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEmailDto {

    @Email(message = "Must be a valid email address.")
    @NotBlank
    private String email;

    @ValidPassword
    private String password;
}

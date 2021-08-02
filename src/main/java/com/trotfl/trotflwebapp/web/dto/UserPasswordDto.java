package com.trotfl.trotflwebapp.web.dto;

import com.trotfl.trotflwebapp.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author Greg Stroud
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPasswordDto {

    @ValidPassword
    private String password;

    @NotBlank
    private String newPassword;
}

package com.trotfl.trotflwebapp.domain.security;

import com.trotfl.trotflwebapp.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Greg Stroud
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

}

package com.trotfl.trotflwebapp.domain.security;

import com.trotfl.trotflwebapp.domain.security.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Greg Stroud
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}

package com.trotfl.trotflwebapp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Greg Stroud
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

}

package com.customer.service.simple.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public static Role valueOf(RoleType roleType) {
        return new Role(roleType);
    }

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}

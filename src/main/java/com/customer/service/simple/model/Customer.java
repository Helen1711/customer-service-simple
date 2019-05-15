package com.customer.service.simple.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {
    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private Address address;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Role> roles = new HashSet<>();

    public void addAddress(Address address) {
        address.setCustomer(this);
        this.address = address;
    }

    public void removeAddress() {
        if (address != null) {
            address.setCustomer(null);
            this.address = null;
        }
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.setCustomer(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.setCustomer(null);
    }

    public Customer(String firstName, String lastName, @Email String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

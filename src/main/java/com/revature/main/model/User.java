package com.revature.main.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "nps_users")
@NoArgsConstructor @Getter @Setter @ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nps_users_id")
    private Long id;

    @Column(name="nps_users_username", unique=true)
    private String username;

    @Column(name="nps_users_password")
    private String password;

    @Column(name="nps_users_eth_address")
    private String ethAddress;

}

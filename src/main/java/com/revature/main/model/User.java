package com.revature.main.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "nps_users")
@NoArgsConstructor @Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="nps_users_id")
    private Long id;

    @Column(name="nps_users_username")
    private String username;

    @Column(name="nps_users_password")
    private String password;

    @Column(name="nps_users_eth_address")
    private String ethAddress;

    public User() {}

    public User(Long id, String username, String password, String ethAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ethAddress = ethAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }


}

package com.revature.main.model;

import javax.persistence.*;

@Entity
@Table(name = "nps_users")
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

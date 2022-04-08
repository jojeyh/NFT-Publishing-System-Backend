package com.revature.main.repository;

import com.revature.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public abstract User findByUsernameAndPassword(String username, String password);
}

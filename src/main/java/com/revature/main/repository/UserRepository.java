package com.revature.main.repository;

import com.revature.main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public abstract User findByUsernameAndPassword(String username, String password);
}

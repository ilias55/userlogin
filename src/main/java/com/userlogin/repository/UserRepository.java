package com.userlogin.repository;

import com.userlogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    User findByUserName(String userName);

}

package com.dinsaren.hrmanagementsystemapplication.repositories;

import com.dinsaren.hrmanagementsystemapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhone(String phone);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

    Optional<User> findByPhoneAndPassword(String username, String password);

    Optional<User> findByPassword(String password);

}

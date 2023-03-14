package com.example.tinywiny.repository;

import com.example.tinywiny.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findUserByUserName(@Param("userName") String userName);

  User getUserByUserName(String userName);

  Optional<User> findUserByUserNameAndPassword(String userName, String password);

  Optional<User> findUserByUserId(Long userId);

  Page<User> findAllBy(Pageable page);


}

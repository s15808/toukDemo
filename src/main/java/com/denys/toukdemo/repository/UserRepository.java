package com.denys.toukdemo.repository;

import com.denys.toukdemo.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findUsersEntityByNameAndSurname(String name, String surname);
}

package org.example.notetaker.dao;

import org.example.notetaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, String> {
    UserEntity getUserEntitiesByUserId(String userId);
}

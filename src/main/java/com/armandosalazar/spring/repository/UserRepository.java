package com.armandosalazar.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.armandosalazar.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Transactional(readOnly = true)
    Optional<User> findByUsername(String username);
}
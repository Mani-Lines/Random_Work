package com.challenge.test.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.test.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}

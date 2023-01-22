package com.example.projektJAVA.repository;

import com.example.projektJAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}

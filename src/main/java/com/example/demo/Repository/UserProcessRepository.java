package com.example.demo.Repository;

import com.example.demo.Model.User;
import com.example.demo.Model.UserProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProcessRepository extends JpaRepository<UserProcess,Integer> {
    Optional<UserProcess> findByUserId(Integer id);
    List<UserProcess> findByUser(User userID);
}

package com.example.demo.Repository;

import com.example.demo.Model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {
    Optional<News>findById(Integer id);
}

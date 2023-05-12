package com.example.data.repository.extended;


import com.example.data.model.extended.CulinaryNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulinaryNewsRepository extends JpaRepository<CulinaryNews, Long> {

}

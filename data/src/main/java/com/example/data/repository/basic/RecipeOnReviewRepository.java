package com.example.data.repository.basic;


import com.example.data.model.basic.RecipeOnReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeOnReviewRepository extends JpaRepository<RecipeOnReview, Long> {

}

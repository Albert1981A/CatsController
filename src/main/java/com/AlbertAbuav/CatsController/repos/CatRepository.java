package com.AlbertAbuav.CatsController.repos;

import com.AlbertAbuav.CatsController.beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

    List<Cat> findAllByNameAndWeight(String name, float weight);
    List<Cat> findAllByNameOrWeight(String name, float weight);
    List<Cat> findAllByOrderByWeightAsc();
    List<Cat> findAllByOrderByWeightDesc();
    List<Cat> findAllByNameStartingWith(String name);
    List<Cat> findByWeight(float weight);

}

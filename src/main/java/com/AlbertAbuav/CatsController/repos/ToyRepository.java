package com.AlbertAbuav.CatsController.repos;

import com.AlbertAbuav.CatsController.beans.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Integer> {

}

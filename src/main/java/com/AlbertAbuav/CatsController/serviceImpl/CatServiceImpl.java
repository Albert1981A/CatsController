package com.AlbertAbuav.CatsController.serviceImpl;

import com.AlbertAbuav.CatsController.beans.Cat;
import com.AlbertAbuav.CatsController.exception.CatsException;
import com.AlbertAbuav.CatsController.repos.CatRepository;
import com.AlbertAbuav.CatsController.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    /**
     * Add a Cat
     */
    public void addCat(Cat cat) {
        catRepository.save(cat);
    }

    /**
     * Update a Cat
     * if the id doesn't exist in the DB throw Exception
     */
    public void updateCat(Cat cat) throws CatsException {
        if (!catRepository.existsById(cat.getId())) {
            throw new CatsException("The Cat id doesn't exist in the data base!");
        }
        catRepository.saveAndFlush(cat);
    }

    /**
     * Delete a Cat by ID
     * if the id doesn't exist in the DB throw Exception
     */
    public void deleteCatById(int id) throws CatsException {
        if (!catRepository.existsById(id)) {
            throw new CatsException("The Cat id doesn't exist in the data base!");
        }
        catRepository.deleteById(id);
    }

    /**
     * Get all cats
     */
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    /**
     * Get a single Cat by ID
     * if the id doesn't exist in the DB throw Exception
     */
    public Cat getSingleCatById(int id) throws CatsException {
        if (!catRepository.existsById(id)) {
            throw new CatsException("The Cat id doesn't exist in the data base!");
        }
        return catRepository.getOne(id);
    }

    /**
     * Get all Cat by name and weight
     * if the weight is les then 0 throw Exception
     */
    public List<Cat> getAllCatsByNameAndWeight(String name, float weight) throws CatsException {
        if (weight <= 0) {
            throw new CatsException("The weight can't be a negative number!");
        }
        return catRepository.findAllByNameAndWeight(name, weight);
    }

    /**
     * Get all Cats by weight sorted in ascending order
     */
    public List<Cat> findAllCatsByWeightAscendingOrder() {
        return catRepository.findAllByOrderByWeightAsc();
    }

    /**
     * Get all Cats by name Like...
     */
    public List<Cat> findAllCatsByNameStartingWith(String name) {
        return catRepository.findAllByNameStartingWith(name);
    }


}

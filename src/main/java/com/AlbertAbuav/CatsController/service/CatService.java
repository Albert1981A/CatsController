package com.AlbertAbuav.CatsController.service;

import com.AlbertAbuav.CatsController.beans.Cat;
import com.AlbertAbuav.CatsController.exception.CatsException;

import java.util.List;

public interface CatService {

    void addCat(Cat cat);

    void updateCat(Cat cat) throws CatsException;

    void deleteCatById(int id) throws CatsException;

    List<Cat> getAllCats();

    Cat getSingleCatById(int id) throws CatsException;

    List<Cat> getAllCatsByNameAndWeight(String name, float weight) throws CatsException;

    List<Cat> findAllCatsByWeightAscendingOrder();

    List<Cat> findAllCatsByNameStartingWith(String name);

}

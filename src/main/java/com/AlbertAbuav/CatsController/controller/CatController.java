package com.AlbertAbuav.CatsController.controller;

import com.AlbertAbuav.CatsController.beans.Cat;
import com.AlbertAbuav.CatsController.exception.CatsException;
import com.AlbertAbuav.CatsController.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cats_service")  //==>  http://localhost:8080/cats_service
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    /**
     * Add a Cat
     */
    @PostMapping("cats")  //==>  http://localhost:8080/cats_service/cats
    public ResponseEntity<?> addCat(@RequestBody Cat cat) {
        catService.addCat(cat);
        return new ResponseEntity<>(HttpStatus.CREATED); //==> Return 201 (created)
    }

    /**
     * Update a Cat
     * if the id doesn't exist in the DB throw Exception
     */
    @PutMapping("cats")  //==>  http://localhost:8080/cats_service/cats
    public ResponseEntity<?> updateCat(@RequestBody Cat cat) throws CatsException {
        catService.updateCat(cat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //==> Return 204 (ok and no content)
    }

    /**
     * Delete a Cat by ID
     * if the id doesn't exist in the DB throw Exception
     */
    @DeleteMapping("cats/{id}")  //==>  http://localhost:8080/cats_service/cats/1
    public ResponseEntity<?> deleteCatById(@PathVariable int id) throws CatsException {
        catService.deleteCatById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //==> Return 204 (ok and no content)
    }

    /**
     * Get all cats
     */
    @GetMapping("cats")  //==>  http://localhost:8080/cats_service/cats
    public ResponseEntity<?> getAllCats() {
        return new ResponseEntity<>(catService.getAllCats(), HttpStatus.OK); //==> Return body + 200
    }

    /**
     * Get a single Cat by ID
     * if the id doesn't exist in the DB throw Exception
     */
    @GetMapping("cats/{id}") //==>  http://localhost:8080/cats_service/cats/3
    public ResponseEntity<?> getSingleCatById(@PathVariable int id) throws CatsException {
         return new ResponseEntity<>(catService.getSingleCatById(id), HttpStatus.OK); //==> Return body + 200
    }

    /**
     * Get all Cat by name and weight
     * if the weight is les then 0 throw Exception
     */
    @GetMapping("cats/name_and_weight")  //==>  http://localhost:8080/cats_service/cats/name_and_weight
    public ResponseEntity<?> getAllCatsByNameAndWeight(@RequestParam String name, @RequestParam float weight) throws CatsException {
        return new ResponseEntity<>(catService.getAllCatsByNameAndWeight(name, weight), HttpStatus.OK); //==> Return body + 200
    }

    /**
     * Get all Cats by weight sorted in ascending order
     */
    @GetMapping("cats/ascending")  //==>  http://localhost:8080/cats_service/cats/ascending
    public ResponseEntity<?> findAllCatsByWeightAscendingOrder() {
        return new ResponseEntity<>(catService.findAllCatsByWeightAscendingOrder(), HttpStatus.OK); //==> Return body + 200
    }

    /**
     * Get all Cats by name Like...
     */
    @GetMapping("cats/starting_with")  //==>  http://localhost:8080/cats_service/cats/starting_with
    public ResponseEntity<?> findAllCatsByNameStartingWith(@RequestParam String name) {
        return new ResponseEntity<>(catService.findAllCatsByNameStartingWith(name), HttpStatus.OK); //==> Return body + 200
    }

}

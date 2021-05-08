package com.AlbertAbuav.CatsController.clr;

import com.AlbertAbuav.CatsController.beans.Cat;
import com.AlbertAbuav.CatsController.beans.Toy;
import com.AlbertAbuav.CatsController.exception.CatsException;
import com.AlbertAbuav.CatsController.service.CatService;
import com.AlbertAbuav.CatsController.utils.ArtUtils;
import com.AlbertAbuav.CatsController.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class InitData3 implements CommandLineRunner {

    private final CatService catService;

    @Override
    public void run(String... args) {

        System.out.println(ArtUtils.INIT_DATA_3);

        TestUtils.testInfo("Add a Cat");

        Toy t1 = Toy.builder()
                .name("Duck doll")
                .build();

        Toy t2 = Toy.builder()
                .name("plastic chicken")
                .build();

        Cat c1 = Cat.builder()
                .name("Brown")
                .weight(3.7f)
                .toy(t1)
                .toy(t2)
                .build();

        System.out.println("The cat to add:");
        System.out.println(c1);
        catService.addCat(c1);
        System.out.println("The cats after adding the new cat:");
        catService.getAllCats().forEach(System.out::println);

        TestUtils.testInfo("Update a Cat name");

        Cat catToUpdate = null;
        try {
            catToUpdate = catService.getSingleCatById(4);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }
        catToUpdate.setName("New Cat Name");

        try {
            catService.updateCat(catToUpdate);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("The cat after the Update:");

        try {
            System.out.println(catService.getSingleCatById(4));
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Update a Cat with an id that doesn't exist in the DB");

        catToUpdate.setId(9);
        try {
            catService.updateCat(catToUpdate);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Delete a Cat by id");

        catToUpdate.setId(4);
        System.out.println("This is the cat to delete:");
        System.out.println(catToUpdate);

        try {
            catService.deleteCatById(4);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("All cats after deleting cat id: " + catToUpdate.getId());
        catService.getAllCats().forEach(System.out::println);

        TestUtils.testInfo("Delete a Cat with an id that doesn't exist in the DB");

        try {
            catService.deleteCatById(9);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Get all cats");

        catService.getAllCats().forEach(System.out::println);

        TestUtils.testInfo("Get a single Cat by id");

        System.out.println("Attempting to Get Cat id: 7");
        try {
            System.out.println(catService.getSingleCatById(7));
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Get a single Cat with an id that doesn't exist in the DB");

        System.out.println("Attempting to Get Cat id: 9 (doesn't exist in the DB)");
        try {
            System.out.println(catService.getSingleCatById(9));
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Get all Cats by name and weight");

        Cat catToGet5 = null;
        try {
            catToGet5 = catService.getSingleCatById(5);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("This is the cat to get:");
        System.out.println(catToGet5);
        System.out.println("Getting the cat:");
        try {
            catService.getAllCatsByNameAndWeight(catToGet5.getName(), catToGet5.getWeight()).forEach(System.out::println);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Get all Cats by name and weight but the weight is a negative number");

        try {
            catService.getAllCatsByNameAndWeight(catToGet5.getName(), -2.2f);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Get all Cats by weight sorted in ascending order");

        catService.findAllCatsByWeightAscendingOrder().forEach(System.out::println);

        TestUtils.testInfo("Getting all the cats that start at a certain letter");

        catService.findAllCatsByNameStartingWith("N").forEach(System.out::println);

    }
}

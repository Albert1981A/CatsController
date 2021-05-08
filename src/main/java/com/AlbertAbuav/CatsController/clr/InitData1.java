package com.AlbertAbuav.CatsController.clr;

import com.AlbertAbuav.CatsController.beans.Cat;
import com.AlbertAbuav.CatsController.beans.Toy;
import com.AlbertAbuav.CatsController.repos.CatRepository;
import com.AlbertAbuav.CatsController.utils.ArtUtils;
import com.AlbertAbuav.CatsController.utils.TestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitData1 implements CommandLineRunner {

    private final CatRepository catRepository;

    @Override
    public void run(String... args) {

        System.out.println(ArtUtils.INIT_DATA_1);

        Toy t1 = Toy.builder()
                .name("ball")
                .build();

        Toy t2 = Toy.builder()
                .name("Mouse")
                .build();

        Toy t3 = Toy.builder()
                .name("Toy Car")
                .build();

        Toy t4 = Toy.builder()
                .name("Doll")
                .build();

        Cat c1 = Cat.builder()
                .name("Mitzi")
                .weight(4.5f)
                .toy(t1)
                .toy(t2)
                .build();

        Cat c2 = Cat.builder()
                .name("Pitzi")
                .weight(3.3f)
                .toy(t3)
                .toy(t4)
                .build();

        TestUtils.testInfo("Adding a Cat");

        catRepository.saveAll(Arrays.asList(c1, c2));

        TestUtils.testInfo("Get all Cats");

        catRepository.findAll().forEach(System.out::println);

        TestUtils.testInfo("Get a single Cat by ID");

        Cat catToUpdate = catRepository.getOne(2);
        System.out.println(catToUpdate);

        TestUtils.testInfo("Updating a Cat");

        System.out.println("Updating weight to 2.5:");
        catToUpdate.setWeight(2.5f);
        catRepository.saveAndFlush(catToUpdate);
        System.out.println("Cat after the update:");
        System.out.println(catRepository.getOne(2));

        TestUtils.testInfo("Deleting a Cat");

        System.out.println("Deleting cat number 1:");
        catRepository.delete(catToUpdate);
        System.out.println("Cats after deleting:");
        catRepository.findAll().forEach(System.out::println);

    }
}

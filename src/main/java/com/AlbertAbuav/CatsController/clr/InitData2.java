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
@Order(2)
public class InitData2 implements CommandLineRunner {

    private final CatRepository catRepository;

    @Override
    public void run(String... args) {

        System.out.println(ArtUtils.INIT_DATA_2);

        Toy t1 = Toy.builder()
                .name("Big ball")
                .build();

        Toy t2 = Toy.builder()
                .name("Dino")
                .build();

        Toy t3 = Toy.builder()
                .name("Bell")
                .build();

        Toy t4 = Toy.builder()
                .name("Dragon")
                .build();

        Cat c1 = Cat.builder()
                .name("Noga")
                .weight(4.2f)
                .toy(t1)
                .toy(t2)
                .build();

        Cat c2 = Cat.builder()
                .name("Kitzi")
                .weight(5.1f)
                .toy(t3)
                .build();

        Cat c3 = Cat.builder()
                .name("Nitzi")
                .weight(2.3f)
                .toy(t4)
                .build();

        Cat c4 = Cat.builder()
                .name("Nemo")
                .weight(3.2f)
                .build();

        TestUtils.testInfo("Adding a Cat");

        catRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

        TestUtils.testInfo("Get all Cats");

        catRepository.findAll().forEach(System.out::println);

        TestUtils.testInfo("Get all Cats by name and weight");

        Cat catSample1 = catRepository.getOne(3);
        Cat catSample2 = catRepository.getOne(4);
        catRepository.findAllByNameAndWeight(catSample2.getName(), catSample2.getWeight()).forEach(System.out::println);

        TestUtils.testInfo("Get all Cats by name or weight");

        catRepository.findAllByNameOrWeight("fjf", catSample1.getWeight()).forEach(System.out::println);

        TestUtils.testInfo("Getting all cats sorted by weight in ascending order");

        catRepository.findAllByOrderByWeightAsc().forEach(System.out::println);

        TestUtils.testInfo("Getting all cats sorted by weight in descending order");

        catRepository.findAllByOrderByWeightDesc().forEach(System.out::println);

        TestUtils.testInfo("Getting all the cats that start at a certain letter");

        catRepository.findAllByNameStartingWith("N").forEach(System.out::println);

        TestUtils.testInfo("Getting all the cats by weight");

        catRepository.findByWeight(2.3f).forEach(System.out::println);

    }

}

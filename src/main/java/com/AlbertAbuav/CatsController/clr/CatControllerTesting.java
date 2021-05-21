package com.AlbertAbuav.CatsController.clr;

import com.AlbertAbuav.CatsController.beans.Cat;
import com.AlbertAbuav.CatsController.beans.Toy;
import com.AlbertAbuav.CatsController.exception.CatsException;
import com.AlbertAbuav.CatsController.service.CatService;
import com.AlbertAbuav.CatsController.utils.ArtUtils;
import com.AlbertAbuav.CatsController.utils.TestUtils;
import com.AlbertAbuav.CatsController.wrappers.ListOfCats;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Component
@RequiredArgsConstructor
@Order(4)
public class CatControllerTesting implements CommandLineRunner {

    private final static String BASE_URL = "http://localhost:8080/cats_service";

    private final RestTemplate restTemplate;
    private final CatService catService;

    @Override
    public void run(String... args) {

        System.out.println(ArtUtils.CAT_CONTROLLER_TESTING);

        Toy t1 = Toy.builder()
                .name("Thomas the train")
                .build();

        Toy t2 = Toy.builder()
                .name("Teddy bear")
                .build();

        Cat c1 = Cat.builder()
                .name("Flitzi")
                .weight(3.3f)
                .toy(t1)
                .toy(t2)
                .build();

        TestUtils.testInfo("Add a Cat");

        // restTemplate.postForObject(BASE_URL + "/cats", c1, String.class);  //==> Will post but return void
        ResponseEntity<String> res = restTemplate.postForEntity(BASE_URL + "/cats", c1, String.class);
        System.out.println(res);
        System.out.println(res.getStatusCodeValue());
        System.out.println(res.getStatusCodeValue() == 201);
        System.out.println(res.getStatusCodeValue() == HttpStatus.CREATED.value());

        TestUtils.testInfo("Update a Cat");

        Cat catToUpdate = null;
        try {
            catToUpdate = catService.getSingleCatById(1);
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("This is the cat to update: \n" + catToUpdate);
        catToUpdate.setWeight(5.2f);
        restTemplate.put(BASE_URL + "/cats", catToUpdate, String.class);
        System.out.println("The cat after the update:");
        try {
            System.out.println(catService.getSingleCatById(1));
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Delete a Cat by ID");

        System.out.println("Deleting the Cat:");
        restTemplate.delete(BASE_URL + "/cats/" + catToUpdate.getId(), String.class);
        System.out.println("Attempt to get the cat that was deleted:");
        try {
            System.out.println(catService.getSingleCatById(1));
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }

        TestUtils.testInfo("Get all cats");

        ListOfCats result = restTemplate.getForObject(BASE_URL + "/cats", ListOfCats.class);
        result.getCats().forEach(System.out::println);

        TestUtils.testInfo("Get a single Cat by ID (Cat id 3)");

        Cat catSample = restTemplate.getForObject(BASE_URL + "/cats/3", Cat.class);
        System.out.println(catSample);

        TestUtils.testInfo("Get all Cat by name and weight");

        ListOfCats result2 = restTemplate.getForObject(BASE_URL + "/cats/name_and_weight?name=" + c1.getName() + "&weight=" + c1.getWeight(), ListOfCats.class);
        result2.getCats().forEach(System.out::println);

        TestUtils.testInfo("Get all Cats by weight sorted in ascending order");

        ListOfCats result3 = restTemplate.getForObject(BASE_URL + "/cats/ascending", ListOfCats.class);
        result3.getCats().forEach(System.out::println);

        TestUtils.testInfo("Get all Cats by name Like...");

        ListOfCats result4 = restTemplate.getForObject(BASE_URL + "/cats/starting_with?name=N", ListOfCats.class);
        result4.getCats().forEach(System.out::println);

    }
}

package sample.helpers;

import sample.entity.Address;
import sample.entity.City;
import sample.entity.Feedback;
import sample.entity.Restaurant;
import sample.entity.dao.AddressDAO;
import sample.entity.dao.CityDAO;
import sample.entity.dao.RestaurantDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by abrasha on 12/16/15.
 */
public class ContentFactory {

    public static void generate(){
        CityDAO cityDAO = CityDAO.getInstance();
        RestaurantDAO restaurantDAO = RestaurantDAO.getInstance();

        City city1 = new City();
        city1.setName("New York");
        cityDAO.save(city1);

        City city2 = new City();
        city2.setName("Denver");
        cityDAO.save(city2);

        Address address1 = new Address();
        address1.setBuildingNumber(2);
        address1.setPostalCode(8745);
        address1.setStreet("Shalom Aleichem Street");

        Address address2 = new Address();
        address2.setBuildingNumber(62);
        address2.setPostalCode(12412);
        address2.setStreet("Independence Street");

        Address address3 = new Address();
        address3.setBuildingNumber(16);
        address3.setPostalCode(97845);
        address3.setStreet("Main Street");

        AddressDAO addressDAO = AddressDAO.getInstance();
        addressDAO.save(address1);
        addressDAO.save(address2);
        addressDAO.save(address3);

        List<Feedback> feedbackList1 = new ArrayList<>();
        List<Feedback> feedbackList2 = new ArrayList<>();
        List<Feedback> feedbackList3 = new ArrayList<>();

        Feedback fb1 = new Feedback();
        fb1.setAuthor("Andrew");
        fb1.setBody("Nice tea");
        fb1.setDate(LocalDate.now());
        fb1.setTitle("Good");

        Feedback fb2 = new Feedback();
        fb2.setAuthor("Bob");
        fb2.setBody("Tasty coffee");
        fb2.setDate(LocalDate.now());
        fb2.setTitle("Nice");

        Feedback fb3 = new Feedback();
        fb3.setAuthor("George");
        fb3.setBody("Delicious meal");
        fb3.setDate(LocalDate.now());
        fb3.setTitle("OK");

        Feedback fb4 = new Feedback();
        fb4.setAuthor("Nicole");
        fb4.setBody("Nice tea");
        fb4.setDate(LocalDate.now());
        fb4.setTitle("Good");

        Feedback fb5 = new Feedback();
        fb5.setAuthor("Michael");
        fb5.setBody("Tasty coffee");
        fb5.setDate(LocalDate.now());
        fb5.setTitle("Nice");

        Feedback fb6 = new Feedback();
        fb6.setAuthor("Stefan");
        fb6.setBody("Delicious meal");
        fb6.setDate(LocalDate.now());
        fb6.setTitle("OK");

        Feedback fb7 = new Feedback();
        fb7.setAuthor("Steve");
        fb7.setBody("Nice tea");
        fb7.setDate(LocalDate.now());
        fb7.setTitle("Good");

        Feedback fb8 = new Feedback();
        fb8.setAuthor("Daniel");
        fb8.setBody("As for me, food in this restaurant is cheap.");
        fb8.setDate(LocalDate.now());
        fb8.setTitle("Nice");

        Feedback fb9 = new Feedback();
        fb9.setAuthor("Alex");
        fb9.setBody("The beer is too expensive for its taste.");
        fb9.setDate(LocalDate.now());
        fb9.setTitle("Too expensive!");

        Feedback fb10 = new Feedback();
        fb10.setAuthor("Dayle");
        fb10.setBody("The meal is not as traditional one.");
        fb10.setDate(LocalDate.now());
        fb10.setTitle("Strange.");

        Feedback fb11 = new Feedback();
        fb11.setAuthor("Chip");
        fb11.setBody("Too loud music in hall.");
        fb11.setDate(LocalDate.now());
        fb11.setTitle("Uncomfortable");

        Feedback fb12 = new Feedback();
        fb12.setAuthor("Chip");
        fb12.setBody("The fruits are not so fresh.");
        fb12.setDate(LocalDate.now());
        fb12.setTitle("So-so, la-la");

        Feedback fb13 = new Feedback();
        fb13.setAuthor("Sarah");
        fb13.setBody("There are a lot of bugs in the toilet.");
        fb13.setDate(LocalDate.now());
        fb13.setTitle("Scary.");

        Feedback fb14 = new Feedback();
        fb14.setAuthor("Fatima");
        fb14.setBody("The food is not free in this restaurant! Not free, Carl!");
        fb14.setDate(LocalDate.now());
        fb14.setTitle("Very bad!");

        feedbackList1.add(fb1);
        feedbackList1.add(fb2);
        feedbackList1.add(fb3);
        feedbackList1.add(fb4);

        feedbackList2.add(fb5);
        feedbackList2.add(fb6);
        feedbackList2.add(fb7);
        feedbackList2.add(fb8);
        feedbackList2.add(fb9);

        feedbackList3.add(fb10);
        feedbackList3.add(fb11);
        feedbackList3.add(fb12);
        feedbackList3.add(fb13);
        feedbackList3.add(fb14);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("A&W Restaurants");
        restaurant1.setAddress(address1);
        restaurant1.setFeedback(feedbackList1);

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("America's Incredible Pizza Company");
        restaurant2.setAddress(address2);
        restaurant2.setFeedback(feedbackList2);

        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("Applebee's");
        restaurant3.setAddress(address3);
        restaurant3.setFeedback(feedbackList3);

        restaurantDAO.save(restaurant1);
        restaurantDAO.save(restaurant2);
        restaurantDAO.save(restaurant3);

        city1.setRestaurants(new HashSet<>());
        city2.setRestaurants(new HashSet<>());

        city1.getRestaurants().add(restaurant1);
        city1.getRestaurants().add(restaurant2);

        city2.getRestaurants().add(restaurant3);


        cityDAO.update(city1);
        cityDAO.update(city2);
    }

}

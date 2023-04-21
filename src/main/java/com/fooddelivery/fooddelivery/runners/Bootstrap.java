package com.fooddelivery.fooddelivery.runners;

import com.fooddelivery.fooddelivery.entities.Customer;
import com.fooddelivery.fooddelivery.entities.MenuItem;
import com.fooddelivery.fooddelivery.entities.Restaurant;
import com.fooddelivery.fooddelivery.repositories.CustomerRepository;
import com.fooddelivery.fooddelivery.repositories.MenuItemRepository;
import com.fooddelivery.fooddelivery.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        if (restaurantRepository.count() == 0) {
            seedData();
        }
        if (customerRepository.count() == 0) {
            seedCustomer();
        }

    }

    private void seedCustomer() {
        //admin
        Customer customer = new Customer();
        customer.setName("Admin");
        customer.setDeliveryAddress("123 Main Street, Anytown, USA");
        customer.setPassword("YWRtaW4=");
        customer.setEmail("admin");
        customer.setPhoneNumber("1234567890");
        customerRepository.save(customer);
    }

    private void seedData() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("The Hungry Bear");
        restaurant.setImage("https://img.freepik.com/free-photo/happy-waiter-serving-food-group-cheerful-friends-pub_637285-12525.jpg");
        restaurant.setLocation("123 Main Street, Anytown, USA");
        restaurant.setCuisineType("BBQ");
        restaurant.setRating("4.5");

        Restaurant restaurant1 = restaurantRepository.save(restaurant);

        restaurant = new Restaurant();
        restaurant.setRestaurantName("Luigi's Pizzeria");
        restaurant.setImage("https://images.edrawmind.com/article/swot-analysis-for-restaurant/1200_630.jpg");
        restaurant.setLocation("456 Oak Avenue, Big City, USA");
        restaurant.setCuisineType("Italian");
        restaurant.setRating("4.0");
        Restaurant restaurant2 = restaurantRepository.save(restaurant);

        restaurant = new Restaurant();
        restaurant.setRestaurantName(" Tokyo Ramen House");
        restaurant.setImage("http://theaquinian.net/wp-content/uploads/2019/09/Jasmine-Gidney_4796.jpg");
        restaurant.setLocation("789 Maple Lane, Anytown, USA");
        restaurant.setCuisineType("Japanese");
        restaurant.setRating("4.3");
        Restaurant restaurant3 = restaurantRepository.save(restaurant);
        MenuItem menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant1.getId());
        menuItem.setItemName("Hamburger");
        menuItem.setDescription("Juicy burger with lettuce, tomato, and onion");
        menuItem.setPrice(5.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2018/10/4/1/FN_chain-restaurant-entrees_Carls-Jr_Famous-Star-with-Cheese_s6x4.jpg.rend.hgtvcom.966.544.suffix/1538685748808.jpeg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant2.getId());
        menuItem.setItemName("Cheese Pizza");
        menuItem.setDescription("Cheese pizza with tomato sauce");
        menuItem.setPrice(7.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2018/10/4/1/FN_chain-restaurant-entrees_Olive-Garden_Chicken-Alfredo_s6x4.jpg.rend.hgtvcom.966.644.suffix/1538685766492.jpeg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant3.getId());
        menuItem.setItemName("Chicken Ramen");
        menuItem.setDescription("Chicken ramen with vegetables");
        menuItem.setPrice(9.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://www.foxandbriar.com/wp-content/uploads/2016/02/Easy-Chicken-Ramen-9-of-10-1.jpg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant1.getId());
        menuItem.setItemName("Hot Dog");
        menuItem.setDescription("Hot dog with ketchup and mustard");
        menuItem.setPrice(3.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://www.washingtonpost.com/resizer/D6V5TO35ggS_RU7kLijiFiHLO7s=/arc-anglerfish-washpost-prod-washpost/public/IPJAY7WQUAI6ZCDL353BQPJDH4.jpg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant2.getId());
        menuItem.setItemName("Pepperoni Pizza");
        menuItem.setDescription("Pepperoni pizza with tomato sauce");
        menuItem.setPrice(8.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://cdn.ruled.me/wp-content/uploads/2014/08/pepperonipizza.jpg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant3.getId());
        menuItem.setItemName("Beef Ramen");
        menuItem.setDescription("Beef ramen with vegetables");
        menuItem.setPrice(9.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://img.taste.com.au/0Nzctw6S/taste/2017/08/ramen-129052-2.jpg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant1.getId());
        menuItem.setItemName("Chicken Sandwich");
        menuItem.setDescription("Chicken sandwich with lettuce, tomato, and onion");
        menuItem.setPrice(5.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://easychickenrecipes.com/wp-content/uploads/2019/06/featured-fried-chicken-sandwich-recipe-reshoot.jpg");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant2.getId());
        menuItem.setItemName("Veggie Pizza");
        menuItem.setDescription("Veggie pizza with tomato sauce");
        menuItem.setPrice(7.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://i0.wp.com/www.thursdaynightpizza.com/wp-content/uploads/2022/06/veggie-pizza-side-view-out-of-oven.png?resize=720%2C480&ssl=1");
        menuItemRepository.save(menuItem);

        menuItem = new MenuItem();
        menuItem.setRestaurantId(restaurant3.getId());
        menuItem.setItemName("Spicy Ramen");
        menuItem.setDescription("Spicy ramen with vegetables");
        menuItem.setPrice(9.99);
        menuItem.setAvailability(true);
        menuItem.setItemImage("https://i.ytimg.com/vi/XiQqBTQO7ow/maxresdefault.jpg");
        menuItemRepository.save(menuItem);

    }
}

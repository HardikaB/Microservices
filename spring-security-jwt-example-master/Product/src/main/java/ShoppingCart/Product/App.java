package ShoppingCart.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ShoppingCart.Product.entity.Product;
import ShoppingCart.Product.repository.ProductRepository;

import javax.annotation.PostConstruct;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class App {

    @Autowired
    private ProductRepository prepository;    
    @PostConstruct
    public void initProduct() {
        List<Product> products = Stream.of(
                new Product((long)1, "Mask", "N95 Mask", 100, new BigDecimal(20.00)),
                new Product((long)2,"Hand Sanitizer", "Kills 99% germs and virus", 100, new BigDecimal(30.00)),
                new Product((long)3, "Gloves", "Protect hands from germs and virus", 100, new BigDecimal(50.00))
        ).collect(Collectors.toList());
        prepository.saveAll(products);
    }    

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}

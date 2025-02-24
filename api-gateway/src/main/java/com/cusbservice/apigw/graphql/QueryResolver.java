package com.cusbservice.apigw.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    public User getUser(String id) {
        return new User(id, "John Doe", "john@example.com");
    }

    public Product getProduct(String id) {
        return new Product(id, "Laptop", 1200.0);
    }
}

@Getter
class User {
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
}

@Getter
class Product {
    private String id;
    private String name;
    private Double price;

    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}

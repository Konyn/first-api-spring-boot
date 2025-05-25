package com.estudos.first_api.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String category;
    private String image;

    public Product(RequestProduct resquestProduct) {
        this.id = resquestProduct.id();
        this.name = resquestProduct.name();
        this.price = resquestProduct.price();
        this.description = resquestProduct.description();
        this.category = resquestProduct.category();
        this.image = resquestProduct.image();
    }

}

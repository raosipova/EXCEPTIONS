package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    Product product1 = new Product(15, "banana", 80);
    Product product2 = new Product(25, "apple", 90);
    Product product3 = new Product(35, "orange", 100);
    Product product4 = new Product(45, "water melon", 110);
    Product product5 = new Product(45, "melon", 120);

    @Test
    public void shouldNotFindId() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(45);
        });
    }

    @Test
    public void shouldFindId() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(25);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSameId() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);

        repo.remove(25);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product5);
        });
    }

    @Test
    public void shouldAddProduct() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(25);

        repo.add(product4);

        Product[] expected = {product1, product3, product4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
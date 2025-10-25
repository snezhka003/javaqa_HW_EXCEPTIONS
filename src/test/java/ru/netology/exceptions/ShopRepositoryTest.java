package ru.netology.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product item1 = new Product(11, "хлеб", 40);
    Product item2 = new Product(222, "булка", 30);
    Product item3 = new Product(3, "картошка", 20);
    Product item4 = new Product(34, "молоко", 95);
    Product item5 = new Product(34, "кефир", 78);

    @Test
    public void shouldBeRemovedById() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        repo.removeById(11);

        Product[] expected = { item2, item3 };
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotBeRemovedById() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(54);
        });
    }

    @Test
    public void shouldBeAddedById() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product[] expected = { item1, item2, item3, item4 };
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotBeAddedByIdForDublicateProductAndId() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item2);
        });
    }

    @Test
    public void shouldNotBeAddedByIdForNotDublicateProductButDublicateId() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item5);
        });
    }
}
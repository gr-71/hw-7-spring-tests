package com.rga.springwebapp.repositories;

import com.rga.springwebapp.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:initProducts.sql")})
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void checkFindByName() {
        //have
        Product product = new Product();
        product.setId(777L);
        product.setTitle("TestProduct777");
        product.setPrice(777.77);

        entityManager.persist(product);

        //execute
        Product actualProduct = productRepository.findOneByTitle("TestProduct777");

        //check
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(product.getId(), actualProduct.getId());
        Assertions.assertEquals(product.getTitle(), actualProduct.getTitle());
        Assertions.assertEquals(product.getPrice(), actualProduct.getPrice());
    }

        @Test
        void checkFindByTitleAfterSql() {
            //execute
            Product actualProduct = productRepository.findOneByTitle("Apples");

            //check
            Assertions.assertNotNull(actualProduct);
            Assertions.assertEquals(7, actualProduct.getId());
            Assertions.assertEquals("Apples", actualProduct.getTitle());
            Assertions.assertEquals(69.70, actualProduct.getPrice());
        }
    }



package com.rga.springwebapp.services;


import com.rga.springwebapp.domain.Product;
import com.rga.springwebapp.repositories.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.util.UUID;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All tests");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each test");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All test");
    }

    @Test
    void checkFindOneByTitle() {
        //have
        String title = "Parsley";
        Product expectedProduct = Product.builder().id(1L).title(title).build();

        Mockito.when(productRepository.findOneByTitle(Mockito.anyString()))
                .thenReturn(expectedProduct);

        //execute
        Product actualProduct = productService.getProductByTitle(title);

        //check
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void checkFindOneByTitleExact() {
        //have
        String title = "Parsley";
        Product expectedProduct = Product.builder().id(1L).title(title).build();

        Mockito.when(productRepository.findOneByTitle(Mockito.eq(title))).thenReturn(expectedProduct);

        //execute
        Product actualProduct = productService.getProductByTitle(title);
        Product randomProduct = productService.getProductByTitle(UUID.randomUUID().toString());

        //check
        Assertions.assertNotNull(actualProduct);
        Assertions.assertEquals(expectedProduct, actualProduct);

        Assertions.assertNull(randomProduct);
    }
}
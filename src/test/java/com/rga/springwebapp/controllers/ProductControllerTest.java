package com.rga.springwebapp.controllers;

import com.rga.springwebapp.dao.ProductDao;
import com.rga.springwebapp.domain.Product;
import com.rga.springwebapp.services.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    private Product p1 = new Product(567L, "TestProduct567", 567.99);
    private Product p2 = new Product(568L, "TestProduct568", 568.99);
    private Product p3 = new Product(569L, "TestProduct569", 569.99);

    @BeforeEach
    void setUp() {
        given (productService.getAllProducts()).willReturn (Arrays.asList (p1, p2, p3));
    }

    @Test
    void checkList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + p1.getTitle() + "</td>")))
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + p2.getTitle() + "</td>")))
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + p3.getTitle() + "</td>")));
    }

    @Test
    void checkPrice() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("products/" + p1.getId () + "/price")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString(String.valueOf (p1.getPrice()))));

    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/" + p2.getId ())
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string(Matchers.containsString("<td>" + p2.getTitle() + "</td>")));

    }

}

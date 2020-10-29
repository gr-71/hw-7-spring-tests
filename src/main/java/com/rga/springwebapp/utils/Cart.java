package com.rga.springwebapp.utils;

import com.rga.springwebapp.domain.OrderPosition;
import com.rga.springwebapp.domain.Product;
import com.rga.springwebapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<OrderPosition> positions;

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public List<OrderPosition> getPositions() {
        return positions;
    }

    @PostConstruct
    public void init() {
        positions = new ArrayList<>();
    }

    public void addProductById(Long id) {
        Product product = productService.getProductById(id);
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setProduct(product);
        positions.add(orderPosition);
    }
}

package org.sahith.products.controllers;

import org.junit.jupiter.api.Test;
import org.sahith.products.exceptions.ProductNotFoundException;
import org.sahith.products.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    @MockBean
    private ProductsService productsService;

    @Test
    public void getProductTest_passIdWhichIsNotPresentInDB_throwsProductNotFoundException(){



        Exception exception = assertThrows(ProductNotFoundException.class, ()->productsController.getProduct(12L));

    }
}

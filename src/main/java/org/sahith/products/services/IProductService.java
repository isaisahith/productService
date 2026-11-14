package org.sahith.products.services;

import org.sahith.products.clients.FakeStoreApiClient;
import org.sahith.products.dtos.FakeStoreProductDto;
import org.sahith.products.models.Category;
import org.sahith.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IProductService implements ProductsService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProduct(long id) {

        FakeStoreProductDto fakeStoreProductDto=  fakeStoreApiClient.getByProductId(id);
        if(fakeStoreProductDto!=null){
            return from(fakeStoreProductDto);
        }
        return null;

    }

    public Product replaceProduct(long id, Product product){
        FakeStoreProductDto fakeStoreProductDto = from(product);
        FakeStoreProductDto responseFakeStoreProductDto = fakeStoreApiClient.replaceProduct(id, fakeStoreProductDto);
        if(fakeStoreProductDto!=null){
            return from(fakeStoreProductDto);
        }
        return null;

    }

    public List<Product> getAllProducts(){

       FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();

       List<Product> products = new ArrayList<>();
       for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
           Product product = from(fakeStoreProductDto);
           products.add(product);
       }

       return products;

    }



    public Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getTitle());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        if(fakeStoreProductDto.getCategory()!=null){
            Category category = new Category();
            category.setDescription(fakeStoreProductDto.getCategory());
            product.setCategory(category);
        }
        return product;
    }

    public FakeStoreProductDto from(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null){
            fakeStoreProductDto.setCategory(product.getCategory().getDescription());
        }

        return fakeStoreProductDto;
    }
}

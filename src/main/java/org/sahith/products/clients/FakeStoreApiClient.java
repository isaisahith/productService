package org.sahith.products.clients;

import org.sahith.products.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getByProductId(long id){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/"+id, null, FakeStoreProductDto.class, id);
        if(validateResponseEntity(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }

    public FakeStoreProductDto replaceProduct(long id, FakeStoreProductDto fakeStoreProductDto){
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/"+id, fakeStoreProductDto, FakeStoreProductDto.class, id);
        if(validateResponseEntity(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoResponseEntity.getBody();
        }

        return null;
    }

    public FakeStoreProductDto[] getAllProducts(){
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductsResponseEntity = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/", null, FakeStoreProductDto[].class);
        if(validateResponseEntity(fakeStoreProductsResponseEntity)){
            return fakeStoreProductsResponseEntity.getBody();
        }
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate fakeStoreApiRestTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = fakeStoreApiRestTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = fakeStoreApiRestTemplate.responseEntityExtractor(responseType);
        return fakeStoreApiRestTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private <T> boolean validateResponseEntity(ResponseEntity<T> fakeStoreProductDtoResponseEntity){
        if(fakeStoreProductDtoResponseEntity.hasBody() && fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()){
            return true;
        }
        return false;
    }
}

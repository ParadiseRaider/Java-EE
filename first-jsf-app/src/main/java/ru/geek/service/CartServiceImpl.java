package ru.geek.service;

import ru.geek.service.dto.ProductDto;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartServiceImpl implements CartService {

    private final List<ProductDto> productList = new ArrayList<>();

    @Override
    public void add(ProductDto productDto) {
        productList.add(productDto);
    }

    @Override
    public void remove(ProductDto productDto) {
        productList.remove(productDto);
    }

    @Override
    public List<ProductDto> findAll() {
        return productList;
    }

    @Override
    public String count() {
        return productList.size()==0 ? "Cart" : "Cart (" + productList.size() + ")";
    }
}

package ru.geek.controller;

import lombok.Getter;
import lombok.Setter;
import ru.geek.persist.Category;
import ru.geek.persist.CategoryRepository;
import ru.geek.service.ProductService;
import ru.geek.service.dto.ProductDto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryRepository categoryRepository;

    @Getter
    @Setter
    private ProductDto product;

    private List<ProductDto> productList;

    @Getter
    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.productList = productService.findAllWithCategoryFetch();
        this.categoryList = categoryRepository.findAll();
    }

    public List<ProductDto> findAll() {
        return productList;
    }

    public String editProduct(ProductDto product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto product) {
        productService.delete(product.getId());
    }

    public String saveProduct() {
        productService.save(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public String addProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }
}

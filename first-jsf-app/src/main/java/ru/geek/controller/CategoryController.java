package ru.geek.controller;

import lombok.Getter;
import lombok.Setter;
import ru.geek.persist.Category;
import ru.geek.persist.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    @Getter
    @Setter
    private Category category;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category.getId());
    }

    public String saveCategory() {
        categoryRepository.save(category);
        return "/category.xhtml?faces-redirect=true";
    }

    public String addCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect=true";
    }
}

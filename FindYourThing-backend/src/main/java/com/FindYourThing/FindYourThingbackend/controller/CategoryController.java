package com.FindYourThing.FindYourThingbackend.controller;

import com.FindYourThing.FindYourThingbackend.dto.CategoryDTO;
import com.FindYourThing.FindYourThingbackend.model.Category;
import com.FindYourThing.FindYourThingbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/savecategory")
    public CategoryDTO saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/getallcategories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/getcategory/{id}")
    public CategoryDTO getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/updatecategory/{id}")
    public CategoryDTO updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.updateCategory(category, id);
    }

    @DeleteMapping("/deletecategory/{id}")
    public CategoryDTO deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

}

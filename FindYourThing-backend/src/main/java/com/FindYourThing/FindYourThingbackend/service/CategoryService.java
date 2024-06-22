package com.FindYourThing.FindYourThingbackend.service;

import com.FindYourThing.FindYourThingbackend.dto.CategoryDTO;
import com.FindYourThing.FindYourThingbackend.exception.CategoryNotFoundException;
import com.FindYourThing.FindYourThingbackend.model.Category;
import com.FindYourThing.FindYourThingbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryDTO saveCategory(Category category) {

        categoryRepository.save(category);
        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return categoryDTO;

    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<CategoryDTO> categoryDTOList = Arrays.asList(modelMapper.map(categoryList, CategoryDTO[].class));

        return categoryDTOList;
    }

    public CategoryDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return categoryDTO;
    }

    public CategoryDTO updateCategory(Category newCategory, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));

        if (newCategory.getTitle() != null) {
            category.setTitle(newCategory.getTitle());
        }
        if (newCategory.getCategory() != null) {
            category.setCategory(newCategory.getCategory());
        }

        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        categoryRepository.save(category);

        return categoryDTO;
    }

    public CategoryDTO deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));

        categoryRepository.delete(category);

        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        return categoryDTO;
    }

}

package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.EasyshopApplication;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.List;
//to make this a REST controller
@RestController
//base url:
@RequestMapping ("/categories")
    // http://localhost:8080/categories
@CrossOrigin
//^annotation to allow requests(insomnia and frontend)
public class CategoriesController {
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao)
    {

        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    //^^^create an Autowired controller to inject the categoryDao and ProductDao

    //GET category, users are able to access (:
    @GetMapping("")
    @PreAuthorize("permitAll()")
    // ^^add the appropriate annotation for a get action
    public List<Category> getAll()
    {
        try
        {
            return categoryDao.getAll();
        }
        catch (Exception ex)
        {
            //runtime exception added to signal an http error response
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving categories"
            );
        }

    }

    //annotation for get action
    // get the category by id
    @GetMapping("/{id}")
    public Category getById(@PathVariable int id)
    {
        try
        {
            Category category = categoryDao.getById(id);

            if (category == null)
            {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                );
            }
            return category;
        }
        catch (ResponseStatusException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving category"
            );
        }
    }



    // the url to return all products in category 1 would look like this **I took off an "s"
    // get a list of product by categoryId
    // http://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        try
        {
            return productDao.getByCategoryId(categoryId);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error retrieving products by category"
            );
        }
    }

    // added annotation to call this method for a POST action
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // ^^added annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.CREATED)
    //returns 201 if successful
    public Category addCategory(@RequestBody Category category)
    {
        try
        {
            return categoryDao.create(category);
            // ^inserts the category
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error creating category"
            );
        }
    }

    @PutMapping("/{id")
    // ^added annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // ^added annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        try
        {
            Category existing = categoryDao.getById(id);
            if (existing == null)
            {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                );
            }
            // update the category by id
            category.setCategoryId(id);
            categoryDao.update(category);
        }
        catch (ResponseStatusException ex)
        {
            throw ex;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error updating category"

            );
        }

    }

    @DeleteMapping("/{id}")
    // ^^added annotation to call this method for a DELETE action - the url path must include the categoryId
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //^^added annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id)
    {
        try
        {
            Category existing = categoryDao.getById(id);
            if (exisiting == null)
            {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category was not found"
                );
            }
        }
        // delete the category by id
    }
}

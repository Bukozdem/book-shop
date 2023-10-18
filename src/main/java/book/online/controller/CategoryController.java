package book.online.controller;

import book.online.dto.book.BookDtoWithoutCategoryIds;
import book.online.dto.category.CategoryRequestDto;
import book.online.dto.category.CategoryResponseDto;
import book.online.service.BookService;
import book.online.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponse(responseCode = "200", description = "Category was created successfully")
    @Operation(summary = "Create a new category", description = "Save a new category to database")
    public CategoryResponseDto createCategory(@RequestBody @Valid CategoryRequestDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all categories",
            description = "Get a list of categories from database")
    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        return categoryService.findAllCategories(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get category by id", description = "Get a category from database by id")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponse(responseCode = "201", description = "Category was updated successfully")
    @Operation(summary = "Update a category", description = "Update fields in category")
    public CategoryResponseDto updateCategory(@PathVariable Long id,
                                              @RequestBody @Valid CategoryRequestDto categoryDto) {
        return categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a category",
            description = "Delete a category by id")
    @ApiResponse(responseCode = "204",
            description = "Category was deleted successfully")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all books by category",
            description = "Get a list of books by a certain category")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.getBooksByCategoryId(id);
    }
}

package book.online.service;

import book.online.dto.category.CategoryRequestDto;
import book.online.dto.category.CategoryResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponseDto saveCategory(CategoryRequestDto book);

    List<CategoryResponseDto> findAllCategories(Pageable pageable);

    CategoryResponseDto findCategoryById(Long id);

    CategoryResponseDto updateCategory(CategoryRequestDto requestDto, Long id);

    void deleteCategory(Long id);
}

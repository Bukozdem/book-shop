package book.online.service.impl;

import book.online.dto.CategoryRequestDto;
import book.online.dto.CategoryResponseDto;
import book.online.mapper.CategoryMapper;
import book.online.model.Category;
import book.online.repository.category.CategoryRepository;
import book.online.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponseDto saveCategory(CategoryRequestDto requestDto) {
        return mapper.toDto(repository.save(mapper.toModel(requestDto)));
    }

    @Override
    public List<CategoryResponseDto> findAllCategories(Pageable pageable) {
        return repository.findAll(pageable).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto findCategoryById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                .format("Can`t find category by id - %d", id))));
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto requestDto, Long id) {
        findCategoryById(id);
        Category category = mapper.toModel(requestDto);
        category.setId(id);
        return mapper.toDto(repository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        findCategoryById(id);
        repository.deleteById(id);
    }
}

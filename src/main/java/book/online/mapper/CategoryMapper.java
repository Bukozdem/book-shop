package book.online.mapper;

import book.online.config.MapperConfig;
import book.online.dto.category.CategoryRequestDto;
import book.online.dto.category.CategoryResponseDto;
import book.online.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toModel(CategoryRequestDto requestDto);
}

package book.online.mapper;

import book.online.config.MapperConfig;
import book.online.dto.BookDto;
import book.online.dto.CreateBookRequestDto;
import book.online.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}

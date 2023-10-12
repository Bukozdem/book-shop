package book.online.service;

import book.online.dto.BookDto;
import book.online.dto.BookDtoWithoutCategoryIds;
import book.online.dto.BookSearchParameters;
import book.online.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto saveBook(CreateBookRequestDto book);

    List<BookDto> findAllBooks(Pageable pageable);

    BookDto findBookById(Long id);

    BookDto updateBook(CreateBookRequestDto requestDto, Long id);

    void deleteBook(Long id);

    List<BookDto> searchBooks(BookSearchParameters parameters);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}

package book.online.service;

import book.online.dto.book.BookDto;
import book.online.dto.book.BookDtoWithoutCategoryIds;
import book.online.dto.book.BookSearchParameters;
import book.online.dto.book.CreateBookRequestDto;
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

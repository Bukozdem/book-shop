package book.online.service;

import book.online.dto.BookDto;
import book.online.dto.BookSearchParameters;
import book.online.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto saveBook(CreateBookRequestDto book);

    List<BookDto> findAllBooks();

    BookDto findBookById(Long id);

    BookDto updateBook(CreateBookRequestDto requestDto, Long id);

    String deleteBook(Long id);

    List<BookDto> searchBooks(BookSearchParameters parameters);
}

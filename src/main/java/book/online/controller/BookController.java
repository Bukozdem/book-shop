package book.online.controller;

import book.online.dto.BookDto;
import book.online.dto.BookSearchParameters;
import book.online.dto.CreateBookRequestDto;
import book.online.service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @PutMapping(value = "/{id}")
    public BookDto updateBook(@RequestBody CreateBookRequestDto requestDto, @PathVariable Long id) {
        return bookService.updateBook(requestDto, id);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping(value = "/search")
    public List<BookDto> searchBooks(BookSearchParameters parameters) {
        return bookService.searchBooks(parameters);
    }
}

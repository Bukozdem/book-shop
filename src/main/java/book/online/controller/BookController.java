package book.online.controller;

import book.online.dto.BookDto;
import book.online.dto.BookSearchParameters;
import book.online.dto.CreateBookRequestDto;
import book.online.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/api/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of books from database")
    public List<BookDto> getAllBooks(Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get book by id", description = "Get a book from database by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Save a new book to database")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a book", description = "Update fields in book")
    public BookDto updateBook(@RequestBody @Valid CreateBookRequestDto requestDto,
                              @PathVariable Long id) {
        return bookService.updateBook(requestDto, id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a book", description = "Delete a book by id")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping(value = "/search")
    @Operation(summary = "Search a book by parameters",
            description = "Search a book by author or title")
    public List<BookDto> searchBooks(BookSearchParameters parameters) {
        return bookService.searchBooks(parameters);
    }
}

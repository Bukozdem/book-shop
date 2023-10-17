package book.online.controller;

import book.online.dto.book.BookDto;
import book.online.dto.book.BookSearchParameters;
import book.online.dto.book.CreateBookRequestDto;
import book.online.service.BookService;
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
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all books", description = "Get a list of books from database")
    public List<BookDto> getAllBooks(Pageable pageable) {
        return bookService.findAllBooks(pageable);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get book by id", description = "Get a book from database by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponse(responseCode = "201",
            description = "Book was created successfully")
    @Operation(summary = "Create a new book", description = "Save a new book to database")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponse(responseCode = "200",
            description = "Book was updated successfully")
    @Operation(summary = "Update a book", description = "Update fields in book")
    public BookDto updateBook(@RequestBody @Valid CreateBookRequestDto requestDto,
                              @PathVariable Long id) {
        return bookService.updateBook(requestDto, id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a book", description = "Delete a book by id")
    @ApiResponse(responseCode = "204",
            description = "Book was deleted successfully")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping(value = "/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Search a book by parameters",
            description = "Search a book by author or title")
    public List<BookDto> searchBooks(BookSearchParameters parameters) {
        return bookService.searchBooks(parameters);
    }
}

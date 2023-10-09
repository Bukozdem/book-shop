package book.online.service;

import book.online.dto.BookDto;
import book.online.dto.BookSearchParameters;
import book.online.dto.CreateBookRequestDto;
import book.online.mapper.BookMapper;
import book.online.model.Book;
import book.online.repository.book.BookRepository;
import book.online.repository.book.BookSpecificationBuilder;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto saveBook(CreateBookRequestDto requestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(requestDto)));
    }

    @Override
    public List<BookDto> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Can`t find book by id - %d", id))));
    }

    @Override
    public BookDto updateBook(CreateBookRequestDto requestDto, Long id) {
        findBookById(id);
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public String deleteBook(Long id) {
        findBookById(id);
        bookRepository.deleteById(id);
        return String.format("Book by id %d was deleted successfully", id);
    }

    @Override
    public List<BookDto> searchBooks(BookSearchParameters parameters) {
        return bookRepository.findAll(bookSpecificationBuilder.build(parameters)).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}

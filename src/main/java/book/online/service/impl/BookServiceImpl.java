package book.online.service.impl;

import book.online.dto.book.BookDto;
import book.online.dto.book.BookDtoWithoutCategoryIds;
import book.online.dto.book.BookSearchParameters;
import book.online.dto.book.CreateBookRequestDto;
import book.online.mapper.BookMapper;
import book.online.model.Book;
import book.online.model.Category;
import book.online.repository.book.BookRepository;
import book.online.repository.book.BookSpecificationBuilder;
import book.online.repository.category.CategoryRepository;
import book.online.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto saveBook(CreateBookRequestDto requestDto) {
        Set<Category> categories = requestDto.getCategoryIds().stream()
                .map(categoryRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        Book book = bookMapper.toModel(requestDto);
        book.setCategories(categories);
        return bookMapper.toDto(bookRepository.save(book));
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
    public void deleteBook(Long id) {
        findBookById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> searchBooks(BookSearchParameters parameters) {
        return bookRepository.findAll(bookSpecificationBuilder.build(parameters)).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id) {
        return bookRepository.findAllByCategoryId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .collect(Collectors.toList());
    }
}

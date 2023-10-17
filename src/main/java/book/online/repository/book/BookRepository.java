package book.online.repository.book;

import book.online.model.Book;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    @EntityGraph(attributePaths = "categories")
    List<Book> findAllByCategoryId(Long categoryId);

    @EntityGraph(attributePaths = "categories")
    @NonNull
    Optional<Book> findById(@NonNull Long id);

    @EntityGraph(attributePaths = "categories")
    @NonNull
    Page<Book> findAll(@NonNull Pageable pageable);

    @EntityGraph(attributePaths = "categories")
    @NonNull
    Book save(@NonNull Book book);
}

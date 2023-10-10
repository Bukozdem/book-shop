package book.online.repository.book;

import book.online.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query(value = "SELECT * FROM books b LEFT JOIN books_categories bc ON b.id = bc.book_id "
            + "WHERE bc.category_id = ?1", nativeQuery = true)
    List<Book> findAllByCategoryId(Long categoryId);
}

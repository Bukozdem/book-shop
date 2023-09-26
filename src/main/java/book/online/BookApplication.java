package book.online;

import static java.math.BigDecimal.valueOf;

import book.online.model.Book;
import book.online.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book testBook = new Book("Title", "Author", "0000",
                    valueOf(100), "Description", "cover image");
            Book anotherBook = new Book("Book", "Writer", "1111",
                    valueOf(300), "Some text", "photo");
            bookService.save(testBook);
            bookService.save(anotherBook);
            bookService.findAll();
        };
    }
}

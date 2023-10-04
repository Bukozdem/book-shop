package book.online.repository.book;

import book.online.model.Book;
import book.online.repository.SpecificationProvider;
import book.online.repository.SpecificationProviderManager;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private List<SpecificationProvider<Book>> bookSpecificationProvider;

    public BookSpecificationProviderManager(List<SpecificationProvider<Book>>
                                                    bookSpecificationProvider) {
        this.bookSpecificationProvider = bookSpecificationProvider;
    }

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProvider.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String
                        .join("Couldn't find correct specification provider or key", key)));
    }
}

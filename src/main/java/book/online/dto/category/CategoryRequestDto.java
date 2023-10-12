package book.online.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull(message = "Field name should not be empty")
    private String name;
    private String description;
}

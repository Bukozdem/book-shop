package book.online.dto.order;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StatusRequestDto {
    @Pattern(regexp = "PENDING|DELIVERED|COMPLETED",
            message = "Status should be only PENDING, DELIVERED or COMPLETED")
    private String status;
}

package main.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoesNotHaveDto {
    private List<String> items;
}

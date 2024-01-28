package main.dto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HasDto {
    private List<String> country;
    private List<String> items;
}

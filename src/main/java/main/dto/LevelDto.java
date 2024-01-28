package main.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LevelDto {
    private int min;
    private int max;
}

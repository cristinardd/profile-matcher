package main.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class MatchersDto {
    private LevelDto level;
    private HasDto has;
    private DoesNotHaveDto doesNotHave;
}
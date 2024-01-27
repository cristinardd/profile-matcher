package main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DeviceDto {
    private Integer id;
    private String model;
    private String carrier;
    private String firmware;
}
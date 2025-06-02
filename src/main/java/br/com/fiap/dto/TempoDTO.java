package br.com.fiap.dto;

import lombok.Data;
import java.util.List;

@Data
public class TempoDTO {
    private Main main;
    private List<Weather> weather;

    @Data
    public static class Main {
        private double temp;
    }

    @Data
    public static class Weather {
        private String description;
    }
}

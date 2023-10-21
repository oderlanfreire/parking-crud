package com.api.crudestacionamento.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EstacionamentoDto {


    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(max = 7)
    private String licensePlatCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;
    

}

package com.mycompany.calculatorapi.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OperationDto {

    @NotNull
    private Type operation;

    @NotNull
    private BigDecimal a;

    @NotNull
    private BigDecimal b;

    public enum Type {

        ADD, SUBTRACT, DIVIDE, MULTIPLY

    }

}

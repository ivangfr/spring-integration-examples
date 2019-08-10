package com.mycompany.springintegrationshell.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CalculatorApiDto {

    private Type operation;
    private BigDecimal a;
    private BigDecimal b;

    public enum Type {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }

}

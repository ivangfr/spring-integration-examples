package com.mycompany.springintegrationshell.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CalculatorApiRequest {

    Type operation;
    BigDecimal a;
    BigDecimal b;

    public enum Type {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }
}

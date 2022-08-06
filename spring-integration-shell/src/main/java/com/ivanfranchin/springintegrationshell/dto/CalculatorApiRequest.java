package com.ivanfranchin.springintegrationshell.dto;

import java.math.BigDecimal;

public record CalculatorApiRequest(CalculatorApiRequest.Type operation, BigDecimal a, BigDecimal b) {

    public enum Type {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }
}

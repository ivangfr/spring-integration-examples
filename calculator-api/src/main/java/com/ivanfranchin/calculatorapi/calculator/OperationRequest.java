package com.ivanfranchin.calculatorapi.calculator;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OperationRequest(@NotNull OperationRequest.Type operation, @NotNull BigDecimal a, @NotNull BigDecimal b) {

    public enum Type {
        ADD, SUBTRACT, DIVIDE, MULTIPLY
    }
}

package com.financeflow.dto;
import java.math.BigDecimal;
public record EvolucaoMensal(Integer ano, Integer mes, BigDecimal receitas, BigDecimal despesas, BigDecimal resultado) {}


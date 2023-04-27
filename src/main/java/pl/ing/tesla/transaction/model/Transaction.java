package pl.ing.tesla.transaction.model;

import java.math.BigDecimal;

public record Transaction(String debitAccount, String creditAccount, BigDecimal amount) {
}

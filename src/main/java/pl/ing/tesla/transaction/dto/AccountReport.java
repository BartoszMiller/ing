package pl.ing.tesla.transaction.dto;

import java.math.BigDecimal;

public class AccountReport {

    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance = BigDecimal.ZERO;

    public AccountReport(String account) {
        this.account = account;
    }

    public void debit(BigDecimal amount) {
        debitCount++;
        balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        creditCount++;
        balance = balance.add(amount);
    }

    public String getAccount() {
        return account;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

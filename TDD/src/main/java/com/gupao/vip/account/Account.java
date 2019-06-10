package com.gupao.vip.account;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = BigDecimal.ZERO;
    private int transationCount;
    public void credit(BigDecimal credit) {
        transationCount++;
        balance = balance.add(credit);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal transactionsAvg() {
        return balance.divide(new BigDecimal(transationCount),BigDecimal.ROUND_HALF_UP);
    }
}

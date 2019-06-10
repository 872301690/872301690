package com.gupao.vip.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTest {

    static final String ABA = "102000012";
    static final String ACCOUNT_NUMBER = "194431518811";
    private Account account;

    @Before
    public void init(){
        account = new Account();
        account.setBankAba(ABA);
        account.setBankAccountNo(ACCOUNT_NUMBER);
        account.setBankAccountType(ACCOUNT.BankAccountType.CHECKING);

    }

    @Test
    public void transferFormBank(){
        account.setAch(new JimbobAch());

        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFormBank(amount);
        Assert.assertEquals(amount,account.getBalance());
    }

    @Test
    public void transactions(){
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.0"));
        Assert.assertEquals(new BigDecimal("11.10"),account.getBalance());
    }

    @Test
    public void transactionsAvg(){
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.0"));
        account.credit(new BigDecimal("2.99"));
        Assert.assertEquals(new BigDecimal("4.70"),account.transactionsAvg());
    }
}

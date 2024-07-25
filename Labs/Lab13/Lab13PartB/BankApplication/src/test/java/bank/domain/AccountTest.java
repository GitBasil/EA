package bank.domain;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*; 

public class AccountTest {
    @Test
    public void testDeposit(){
        Account account = new Account();
        account.deposit(100);
        assertThat(account.getBalance(), closeTo (100.0, 0.01));
    }

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.deposit(100);
        account.withdraw(100);
        assertThat(account.getBalance(), closeTo(0, 0.01));
    }
}

package accounts.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*; 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class testAccountRepository {
    @Autowired
    private TestEntityManager entityManager; 
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByAccountHolder(){
        Account account = new Account("123", 100, "Frank");
        entityManager.persist(account);
        entityManager.flush();

        Account frank = accountRepository.findByAccountHolder("Frank");

        assertThat(frank.getAccountHolder(), equalTo(account.getAccountHolder()));
    }
}

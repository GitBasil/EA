package bank;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import bank.DTO.AccountDTO;
import bank.DTO.AccountEntryDTO;
import bank.DTO.CustomerDTO;
import bank.service.IAccountService;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class Application  implements CommandLineRunner{
	@Autowired
	IAccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// create 2 accounts;
		accountService.createAccount(1263862, "Frank Brown");
		accountService.createAccount(4253892, "John Doe");
		//use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);
		//use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
		// show balances
		
		Collection<AccountDTO> accountlist = accountService.getAllAccounts();
		CustomerDTO customer = null;
		for (AccountDTO account : accountlist) {
			customer = account.customer();
			System.out.println("Statement for Account: " + account.accountnumber());
			System.out.println("Account Holder: " + customer.name());
			System.out.println("-Date-------------------------"
							+ "-Description------------------"
							+ "-Amount-------------");
			for (AccountEntryDTO entry : account.entryList()) {
				System.out.printf("%30s%30s%20.2f\n", entry.date()
						.toString(), entry.description(), entry.amount());
			}
			System.out.println("----------------------------------------"
					+ "----------------------------------------");
			// System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
			// 		account.getBalance());
		}
	}

}



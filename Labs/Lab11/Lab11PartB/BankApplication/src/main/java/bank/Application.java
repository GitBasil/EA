package bank;

import java.util.Collection;

import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;

import bank.DTO.AccountDTO;
import bank.DTO.AccountEntryDTO;
import bank.DTO.AccountParam;
import bank.DTO.AccountsDTO;
import bank.DTO.CustomerDTO;
import bank.service.IAccountService;

@SpringBootApplication
public class Application  implements CommandLineRunner{
	@Autowired
	IAccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();

		// create 2 accounts;
		AccountParam accountParam = new AccountParam();
		accountParam.setAccountNumber(1263862);
		accountParam.setCustomerName("Frank Brown");
		restClient.post()
            .uri("/Bank/Account")
            .contentType(MediaType.APPLICATION_JSON)
            .body(accountParam)
            .retrieve()
            .body(AccountParam.class);

		accountParam = new AccountParam();
		accountParam.setAccountNumber(4253892);
		accountParam.setCustomerName("John Doe");
		restClient.post()
				.uri("/Bank/Account")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		//use account 1;
		accountParam = new AccountParam();
		accountParam.setAccountNumber(1263862);
		accountParam.setAmount(240);
		accountParam.setOperation("deposit");
		restClient.post()
				.uri("/Bank/Account/Funds")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		accountParam = new AccountParam();
		accountParam.setAccountNumber(1263862);
		accountParam.setAmount(529);
		accountParam.setOperation("deposit");
		restClient.post()
				.uri("/Bank/Account/Funds")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		accountParam = new AccountParam();
		accountParam.setAccountNumber(1263862);
		accountParam.setAmount(230);
		accountParam.setOperation("withdrawEuros");
		restClient.post()
				.uri("/Bank/Account/Funds")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		//use account 2;
		accountParam = new AccountParam();
		accountParam.setAccountNumber(4253892);
		accountParam.setAmount(12450);
		accountParam.setOperation("deposit");
		restClient.post()
				.uri("/Bank/Account/Funds")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		accountParam = new AccountParam();
		accountParam.setAccountNumber(4253892);
		accountParam.setAmount(200);
		accountParam.setOperation("depositEuros");
		restClient.post()
				.uri("/Bank/Account/Funds")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		accountParam = new AccountParam();
		accountParam.setFromAccountNumber(4253892);
		accountParam.setToAccountNumber(1263862);
		accountParam.setAmount(100);
		accountParam.setDescription("payment of invoice 10232");
		accountParam.setOperation("transferFunds");
		restClient.post()
				.uri("/Bank/Account/Funds")
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountParam)
				.retrieve()
				.body(AccountParam.class);

		//Accounts
		AccountsDTO accs = restClient.get()
				.uri("/Bank")
				.retrieve()
				.body(AccountsDTO.class);
		if(accs == null) return;
		
		Collection<AccountDTO> accountlist = accs.getAccounts();
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
		}
	}

}



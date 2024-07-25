package bank;

import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;

import bank.DTO.AccountParam;
import bank.config.ApplicationProperties;
import bank.service.IAccountService;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)  // if you add @service in the class you dont need this
public class Application  implements CommandLineRunner{
	@Autowired
	IAccountService accountService;

	@Autowired
	ApplicationProperties applicationProperties;

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


		System.out.println(applicationProperties);
	}

}



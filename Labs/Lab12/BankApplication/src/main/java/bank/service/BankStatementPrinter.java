package bank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import bank.DTO.AccountDTO;
import bank.DTO.AccountEntryDTO;
import bank.DTO.CustomerDTO;

@Service
@EnableScheduling
public class BankStatementPrinter {
    @Autowired
    IAccountService accountService;

    @Scheduled(fixedRate = 20000)
    public void printStatement(){
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
		}
    }
}

package bank.service;
import bank.integration.EmailSender;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.TraceRecord;
import bank.repositories.AccountRepository;
import bank.repositories.CustomerRepository;
import bank.repositories.TraceRecordRepository;
import jakarta.transaction.Transactional;

@Service
public class BankService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private TraceRecordRepository traceRecordRepository;

	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){
		try {
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome "+customerName);

			TraceRecord traceRecord = new TraceRecord("Customer " + customerName + " created with \n" +
								"account " + AccountNumber, LocalDate.now());
			traceRecordRepository.save(traceRecord);
		} catch (Exception e) {
			emailSender.sendEmail(emailAddress, "We could not create your account "+AccountNumber);
			TraceRecord traceRecord = new TraceRecord("Could not create Customer " + customerName + " with \n" +
								"account " + AccountNumber, LocalDate.now());
			traceRecordRepository.save(traceRecord);
			throw e;
		}
		
	}

}

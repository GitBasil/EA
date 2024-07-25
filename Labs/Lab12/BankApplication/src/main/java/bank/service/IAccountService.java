package bank.service;

import java.util.List;

import bank.DTO.AccountDTO;



public interface IAccountService {
    public AccountDTO createAccount(long accountNumber, String customerName);
    public AccountDTO getAccount(long accountNumber);
    public List<AccountDTO> getAllAccounts();
    public void deposit (long accountNumber, double amount);
    public void withdraw (long accountNumber, double amount);
    public void depositEuros (long accountNumber, double amount);
    public void withdrawEuros (long accountNumber, double amount);
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
}

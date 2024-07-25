package bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.DTO.AccountDTO;
import bank.DTO.AccountParam;
import bank.DTO.AccountsDTO;
import bank.service.IAccountService;

@RestController
@RequestMapping("/Bank")
public class BankController {
    @Autowired
    IAccountService accountService;

    @PostMapping("/Account")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountParam accountParam) {
        AccountDTO account = accountService.createAccount(accountParam.getAccountNumber(), accountParam.getCustomerName());

        return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
    }

    @PostMapping("/Account/Funds")
    public ResponseEntity<AccountParam> deposit(@RequestBody AccountParam accountParam) {
        switch (accountParam.getOperation()) {
            case "deposit":
                accountService.deposit(accountParam.getAccountNumber(), accountParam.getAmount());
                break;
            case "depositEuros":
                accountService.depositEuros(accountParam.getAccountNumber(), accountParam.getAmount());
                break;
            case "withdraw":
                accountService.withdraw(accountParam.getAccountNumber(), accountParam.getAmount());
            break;
            case "withdrawEuros":
                accountService.withdrawEuros(accountParam.getAccountNumber(), accountParam.getAmount());
                break;
            case "transferFunds":
                accountService.transferFunds(accountParam.getFromAccountNumber(),  accountParam.getToAccountNumber(),  accountParam.getAmount(),  accountParam.getDescription());
                break;
            default:
                break;
        }

        return new ResponseEntity<AccountParam>(accountParam, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<AccountsDTO> getAccounts() {
        AccountsDTO accounts = new AccountsDTO();
        accounts.setAccounts(accountService.getAllAccounts());
        return new ResponseEntity<AccountsDTO>(accounts, HttpStatus.OK);
    }


}

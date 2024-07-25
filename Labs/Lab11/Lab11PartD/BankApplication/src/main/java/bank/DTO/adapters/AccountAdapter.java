package bank.DTO.adapters;

import java.util.List;

import bank.DTO.AccountDTO;
import bank.DTO.AccountEntryDTO;
import bank.domain.Account;
import bank.domain.AccountEntry;

public class AccountAdapter {
    public static Account getAccountFromAccountDTO(AccountDTO accountDTO) {
        List<AccountEntry> accountEntries = accountDTO.entryList().stream()
                                                .map(m -> AccountEntryAdapter.getAccountEntryFromAccountEntryDTO(m)).toList();

        return new Account(accountDTO.accountnumber(), accountEntries, CustomerAdapter.getCustomerFromCustomerDTO(accountDTO.customer()));
    }

    public static AccountDTO getAccountDTOFromAccount(Account account) {
        List<AccountEntryDTO> accountEntries = account.getEntryList().stream()
                                                .map(m -> AccountEntryAdapter.getAccountEntryDTOFromAccountEntry(m)).toList();

        return new AccountDTO(account.getAccountnumber(), accountEntries, CustomerAdapter.getCustomerDTOFromCustomer(account.getCustomer()));
    }
}
package bank.DTO.adapters;

import bank.DTO.AccountEntryDTO;
import bank.domain.AccountEntry;

public class AccountEntryAdapter {
    public static AccountEntry getAccountEntryFromAccountEntryDTO(AccountEntryDTO accountEntryDTO) {
        return new AccountEntry(accountEntryDTO.id(), accountEntryDTO.date(), accountEntryDTO.amount(), accountEntryDTO.description(), accountEntryDTO.fromAccountNumber(), accountEntryDTO.fromPersonName());
    }

    public static AccountEntryDTO getAccountEntryDTOFromAccountEntry(AccountEntry accountEntry) {
        return new AccountEntryDTO(accountEntry.getId(), accountEntry.getDate(), accountEntry.getAmount(), accountEntry.getDescription(), accountEntry.getFromAccountNumber(), accountEntry.getFromPersonName());
    }
}
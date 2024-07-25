package bank.DTO;

import java.util.List;

public class AccountsDTO {
    List<AccountDTO> accounts;

    public AccountsDTO() {
    }

    public AccountsDTO(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public List<AccountDTO> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
    @Override
    public String toString() {
        return "{" +
            " accounts='" + getAccounts() + "'" +
            "}";
    }
}

package bank.DTO;

import java.util.Collection;

public record AccountDTO(
	long accountnumber, 
	Collection<AccountEntryDTO> entryList,
	CustomerDTO customer
) {}
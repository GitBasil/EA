package bank.DTO;

import java.util.Date;

public record AccountEntryDTO(
	 long id,
	 Date date,
	 double amount,
	 String description,
	 String fromAccountNumber,
	 String fromPersonName
) {}
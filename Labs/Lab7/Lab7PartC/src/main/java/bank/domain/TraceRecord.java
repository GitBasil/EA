package bank.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TraceRecord {
	@Id
	@GeneratedValue
	private long id;
	private String description;
	private LocalDate date;

	public TraceRecord() {
	}

	public TraceRecord(String description, LocalDate date) {
		this.description = description;
		this.date = date;
	}

	public long getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", description='" + getDescription() + "'" +
			", date='" + getDate() + "'" +
			"}";
	}


}

package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
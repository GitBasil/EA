package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
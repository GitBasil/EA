package bank.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import bank.domain.TraceLog;

public interface TraceLogRepository extends MongoRepository<TraceLog, Long> {
    @Query("SELECT MAX(t.id) FROM TraceLog t")
    Optional<Long> findMaxId();
}

package bank.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bank.domain.TraceRecord;


public interface TraceRecordRepository extends JpaRepository<TraceRecord, Long> {
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public default void storeTraceRecord(TraceRecord traceRecord) {
		this.save(traceRecord);
    }

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    <S extends TraceRecord> S save(S entity);
}





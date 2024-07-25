package bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bank.domain.TraceRecord;
import bank.repositories.TraceRecordRepository;

@Service
public class TraceRecordService {
    @Autowired
	private TraceRecordRepository traceRecordRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logRecord(TraceRecord traceRecord){
        traceRecordRepository.save(traceRecord);
    }

}

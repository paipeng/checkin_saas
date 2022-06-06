package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Code;
import com.paipeng.saas.checkin.tenant.entity.Record;
import com.paipeng.saas.checkin.tenant.repository.CodeRepository;
import com.paipeng.saas.checkin.tenant.repository.RecordRepository;
import com.paipeng.saas.checkin.util.exception.SC_BAD_REQUEST;
import com.paipeng.saas.checkin.util.exception.SC_CONFLICT;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class RecordService extends BaseService{
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private CodeRepository codeRepository;

    @Override
    public Record query(Long taskId) {
        return recordRepository.findById(taskId).orElse(null);
    }

    @Override
    public List<Record> query() {
        return recordRepository.findAll();
    }


    public Record save(Record record) {
        if (record.getCode() == null) {
            throw new SC_BAD_REQUEST();
        }
        if (record.getUser() == null) {
            throw new SC_BAD_REQUEST();
        }

        Code code = codeRepository.findById(record.getCode().getId()).orElse(null);
        if (code == null) {
            throw new SC_BAD_REQUEST();
        }
        if (code.getState() == 0) {
            logger.error("code state inactive");
            throw new SC_CONFLICT();
        }
        if (code.getTask().getState() == 0) {
            logger.error("task state inactive");
            throw new SC_CONFLICT();
        }

        // check time validation
        if (code.getTask().getStartTime() != null && code.getTask().getStartTime() != null) {
            Instant instant = Instant.now();
            long timeStampMillis = instant.toEpochMilli();
            if (timeStampMillis < code.getTask().getStartTime().toInstant().toEpochMilli() ||
                    timeStampMillis > code.getTask().getEndTime().toInstant().toEpochMilli()) {
                logger.error("task start/end time invalid");
                throw new SC_CONFLICT();
            }
        }

        return recordRepository.saveAndFlush(record);
    }

    public Record update(Long recordId, Record record) {
        Record foundRecord = query(recordId);
        if (foundRecord == null) {
            throw new SC_NOT_FOUND();
        }
        foundRecord.setLatitude(record.getLatitude());
        foundRecord.setLongitude(record.getLongitude());
        foundRecord.setUser(record.getUser());
        foundRecord.setDevice(record.getDevice());
        foundRecord.setCode(record.getCode());
        logger.trace("foundRecord: " + foundRecord.getCode().getName());
        return recordRepository.saveAndFlush(foundRecord);
    }

    @Override
    public void delete(Long recordId) {
        Record foundRecord = query(recordId);
        if (foundRecord == null) {
            throw new SC_NOT_FOUND();
        }
        recordRepository.delete(foundRecord);
    }

    public List<Record> queryByTaskId(Long taskId) {
        return recordRepository.findAllByTaskId(taskId);
    }
}

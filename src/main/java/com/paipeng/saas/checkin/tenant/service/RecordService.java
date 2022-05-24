package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Code;
import com.paipeng.saas.checkin.tenant.entity.Record;
import com.paipeng.saas.checkin.tenant.repository.CodeRepository;
import com.paipeng.saas.checkin.tenant.repository.RecordRepository;
import com.paipeng.saas.checkin.util.exception.SC_BAD_REQUEST;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService extends BaseService{
    @Autowired
    private RecordRepository recordRepository;

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

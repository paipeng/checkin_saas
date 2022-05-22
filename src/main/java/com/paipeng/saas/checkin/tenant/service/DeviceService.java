package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Device;
import com.paipeng.saas.checkin.tenant.entity.User;
import com.paipeng.saas.checkin.tenant.repository.DeviceRepository;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService extends BaseService{
    @Autowired
    private DeviceRepository deviceRepository;

    public Device query(long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    public List<Device> query() {
        return deviceRepository.findAll();
    }

    public Device save(Device device) {
        logger.trace("save");
        if (device.getCompany() == null) {
            User user = getUserFromSecurity();
            device.setCompany(user.getCompany());
        }
        return deviceRepository.saveAndFlush(device);
    }

    public Device update(long deviceId, Device device) {
        Device foundDevice = query(deviceId);
        if (foundDevice == null) {
            throw new SC_NOT_FOUND();
        }
        foundDevice.setName(device.getName());
        foundDevice.setLatitude(device.getLatitude());
        foundDevice.setLongitude(device.getLongitude());
        foundDevice.setPlace(device.getPlace());
        return deviceRepository.saveAndFlush(device);
    }

    public void delete(long deviceId) {
        Device foundDevice = query(deviceId);
        if (foundDevice == null) {
            throw new SC_NOT_FOUND();
        }
        deviceRepository.delete(foundDevice);
    }
}

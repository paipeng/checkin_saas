package com.paipeng.saas.checkin.tenant.service;

import com.paipeng.saas.checkin.tenant.entity.Device;
import com.paipeng.saas.checkin.tenant.entity.User;
import com.paipeng.saas.checkin.tenant.repository.DeviceRepository;
import com.paipeng.saas.checkin.util.exception.SC_NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService extends BaseService{
    @Autowired
    private DeviceRepository deviceRepository;

    public Device get(long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    public Device save(Device device) {
        if (device.getCompany() == null) {
            User user = getUserFromSecurity();
            device.setCompany(user.getCompany());
        }
        return deviceRepository.saveAndFlush(device);
    }

    public Device update(Device device) {
        Device foundDevice = get(device.getId());
        if (foundDevice == null) {
            throw new SC_NOT_FOUND();
        }
        return deviceRepository.saveAndFlush(device);
    }

    public void delete(long deviceId) {
        Device foundDevice = get(deviceId);
        if (foundDevice == null) {
            throw new SC_NOT_FOUND();
        }
        deviceRepository.delete(foundDevice);
    }
}

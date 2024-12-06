package com.GreenShadow.WebSystem.service.impl;

import com.GreenShadow.WebSystem.dao.CropDao;
import com.GreenShadow.WebSystem.dao.FieldDao;
import com.GreenShadow.WebSystem.dao.LogMonitoringDao;
import com.GreenShadow.WebSystem.dao.StaffDao;
import com.GreenShadow.WebSystem.dto.impl.LogMonitoringDTO;
import com.GreenShadow.WebSystem.service.LogMonitorinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class LogMonitoringServiceImpl implements LogMonitorinService {

    @Autowired
    private LogMonitoringDao logMonitoringDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private CropDao cropDao;

    @Autowired
    private StaffDao staffDao;


    @Override
    public void saveMonitoringLogDetails(LogMonitoringDTO monitoringLogDTO) {

    }




    @Override
    public String generateNewLogCode() {
        try {
            List<String> logCodeList = logMonitoringDao.findAllIds();
            ArrayList<Integer> numberList = new ArrayList<>();
            for (String logCode: logCodeList) {
                numberList.add(Integer.parseInt(logCode.replace("M_LOG_", "")));
            }
            return "M_LOG_"+ (Collections.max(numberList)+1);
        } catch (Exception e) {
            return "M_LOG_1";
        }
    }

    @Override
    public List<String> getAllFieldIds() {
        return fieldDao.findAllIds();
    }

    @Override
    public List<String> getAllCropCodes() {
        return cropDao.findAllCropCodes();
    }

    @Override
    public List<String> getAllStaffMemberIDs() {
        return staffDao.findAllIds();
    }
}

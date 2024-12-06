package com.GreenShadow.WebSystem.service;

import com.GreenShadow.WebSystem.dto.impl.LogMonitoringDTO;
import com.GreenShadow.WebSystem.entity.MonitoringLogEntity;
import org.locationtech.jts.index.strtree.SIRtree;

import java.util.List;

public interface LogMonitorinService {
    void saveMonitoringLogDetails(LogMonitoringDTO monitoringLogDTO);
    String generateNewLogCode();
    List<String> getAllFieldIds();
    List<String> getAllCropCodes();
    List<String> getAllStaffMemberIDs();
}

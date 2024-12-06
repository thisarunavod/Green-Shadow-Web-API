package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.FieldStaffDetails;
import com.GreenShadow.WebSystem.entity.MonitoringLogEntity;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMonitoringDao extends JpaRepository<MonitoringLogEntity, String> {

    @Query("select logCode from monitoring_log ")
    List<String> findAllIds();



}

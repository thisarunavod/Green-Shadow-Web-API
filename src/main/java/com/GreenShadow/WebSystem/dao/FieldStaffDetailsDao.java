package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.FieldStaffDetails;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldStaffDetailsDao extends JpaRepository<FieldStaffDetails, FieldStaffDetailPK> {
    FieldStaffDetails getFieldStaffDetailsByStaffId(String staffId);
    List<FieldStaffDetails> findAllByField_FieldCode(String fieldCode);
    Optional<FieldStaffDetails> findByFieldStaffDetailPK_Id(String fieldStaffDetailPKId);
}

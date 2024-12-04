package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.FieldStaffDetails;
import com.GreenShadow.WebSystem.entity.embedded.FieldStaffDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldStaffDetailsDao extends JpaRepository<FieldStaffDetails, FieldStaffDetailPK> {

}

package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
    StaffEntity getStaffEntityById(String id);
}

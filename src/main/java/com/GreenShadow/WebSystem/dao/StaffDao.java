package com.GreenShadow.WebSystem.dao;

import com.GreenShadow.WebSystem.entity.Designation;
import com.GreenShadow.WebSystem.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
    StaffEntity getStaffEntityById(String id);

    @Query("select id from staff")
    List<String> findAllIds();

    List<StaffEntity> findAllByDesignationContaining(Designation designation);



}

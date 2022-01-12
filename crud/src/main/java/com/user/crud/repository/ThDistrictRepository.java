package com.user.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.user.crud.model.ThDistrict;

public interface ThDistrictRepository extends JpaRepository<ThDistrict, Long> {

    List<ThDistrict> findByProvinceId(Long provinceId);

    ThDistrict findByNameTh(String nameTh);

    @Query("select d from ThDistrict d where d.nameTh = :nameTh")
    
    ThDistrict searchNameTh(@Param("nameTh") String nameTh);

}
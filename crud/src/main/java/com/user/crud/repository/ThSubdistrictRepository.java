package com.user.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.user.crud.model.ThSubdistrict;

public interface ThSubdistrictRepository extends JpaRepository<ThSubdistrict, Long> {

    List<ThSubdistrict> findByDistrictId(Long districtId);

}

package com.user.crud.repository;

import com.user.crud.model.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
	public List<Address> findAll();

	public Address findById(long id);

}

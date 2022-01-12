package com.user.crud.repository;

import com.user.crud.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findAll();

	public List<User> findById(int id);

}
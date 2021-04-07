package com.example.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.IdCard;

public interface IdRepo extends JpaRepository<IdCard, Integer>{

	
}

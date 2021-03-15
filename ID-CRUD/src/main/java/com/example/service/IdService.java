package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.IdCard;
import com.example.reponsitory.IdRepo;

@Service
public class IdService {
	
	@Autowired
	IdRepo idRepo;
	
	public List<IdCard> getAllIdCard(){
		return idRepo.findAll();
	}
	public IdCard addIdCard(IdCard IdCard){
		return idRepo.save(IdCard);
	}
	public IdCard updateIdCard(Integer id,IdCard idCard){
		IdCard idCardTemp = idRepo.getOne(id);
		idCardTemp.setName(idCard.getName());
		idCardTemp.setIdNumber(idCard.getIdNumber());
		idCardTemp.setBirth(idCard.getBirth());
		idCardTemp.setAddress(idCard.getAddress());
		return idRepo.save(idCardTemp);
	}
	
	public void deleteById(Integer id){
		 idRepo.deleteById(id);
	}
	
	
	public Optional<IdCard> getIdCardById(int parseInt) {
		
//		return idRepo.getOne(parseInt);
		//tra ve reference => loi khi tra ve json
		
		return idRepo.findById(parseInt);
	}
	public boolean existByid(int parseInt) {
		 
		return idRepo.existsById(parseInt);
	} 
	

	
}

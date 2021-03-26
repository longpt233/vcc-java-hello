package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.IdCard;
import com.example.service.IdService;

@RestController
public class IdController {
	@Autowired
	IdService idService;
	

	@RequestMapping(value = "/idcards", method = RequestMethod.GET)
	public List<IdCard> getIdCards() {
		return idService.getAllIdCard();
	}

	@RequestMapping(value = "/idcard/{id}", method = RequestMethod.GET)
	public Optional<IdCard> getIdCardById(@PathVariable("id") String id) {
		return idService.getIdCardById(Integer.parseInt(id));
	}

	@RequestMapping(value = "/idcard", method = RequestMethod.POST)
	public IdCard addIdCard(@Valid @RequestBody IdCard IdCard) {
		return idService.addIdCard(IdCard);
	}

	@RequestMapping(value = "/idcard/{id}", method = RequestMethod.PUT)
	public IdCard addIdCard(@PathVariable("id") String id, @Valid @RequestBody IdCard IdCard) {
		return idService.updateIdCard(Integer.parseInt(id), IdCard);
	}

	// jpa khong cho tra ve . thich thi tu impl trong repo
	@RequestMapping(value = "/idcard/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id") String id) {
		idService.deleteById(Integer.parseInt(id));
			
	}

	
}

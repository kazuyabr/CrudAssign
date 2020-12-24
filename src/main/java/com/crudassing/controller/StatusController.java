package com.crudassing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudassing.dto.StatusDTO;
import com.crudassing.entity.Status;
import com.crudassing.service.StatusService;
import com.crudassing.util.Response;

@RestController
@RequestMapping("status")
public class StatusController {

	@Autowired
	StatusService service;

	@GetMapping
	public ResponseEntity<Response<StatusDTO>> getAll() {

		Response<StatusDTO> response = new Response<StatusDTO>();

		List<Status> listStatus = service.getAll();

		listStatus.forEach(assign -> System.out.println(assign.toString()));

//		@SuppressWarnings("static-access")
//		List<StatusDTO> convertedDTO = response.convertList(listStatus, StatusDTO.class);

		List<StatusDTO> convertedDTO = new ArrayList<StatusDTO>();

		listStatus.forEach(item -> {
			StatusDTO statusDTO = new StatusDTO(item.getId(), item.getTipo());
			convertedDTO.add(statusDTO);
		});

		System.out.println("Teste de conversão Generica: " + convertedDTO);

		if (listStatus.isEmpty()) {
			List<String> errs = new ArrayList<String>();
			errs.add("Nenhum dado encontrado!");

			response.setErrors(errs);
			return ResponseEntity.ok().body(response);
		}

		response.setDataList(convertedDTO);

		return ResponseEntity.ok().body(response);
	}

}

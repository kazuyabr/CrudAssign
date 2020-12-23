package com.crudassing.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudassing.dto.AssinaturaDTO;
import com.crudassing.entity.Assinatura;
import com.crudassing.entity.Status;
import com.crudassing.service.AssinaturaService;
import com.crudassing.util.Response;

@RestController
@RequestMapping("assinatura")
public class AssinaturaController {

	@Autowired
	private AssinaturaService service;

	@PostMapping
	public ResponseEntity<Response<AssinaturaDTO>> create(@Valid @RequestBody AssinaturaDTO dto, BindingResult result) {

		dto.setCriadoEm(Timestamp.valueOf(LocalDateTime.now()));
		dto.setAtualizadoEm(Timestamp.valueOf(LocalDateTime.now()));

		Response<AssinaturaDTO> response = new Response<AssinaturaDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		Assinatura assinatura = service.save(this.convertDtoToEntity(dto));

		response.setData(this.convertEntityToDto(assinatura));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<Response<AssinaturaDTO>> getAll(BindingResult result) {

		Response<AssinaturaDTO> response = new Response<AssinaturaDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		List<Assinatura> listAssinatura = service.getAll();

		listAssinatura.forEach(assign -> System.out.println(assign.toString()));

		@SuppressWarnings("static-access")
		List<AssinaturaDTO> convertedDTO = response.convertList(listAssinatura, AssinaturaDTO.class);

		System.out.println("Teste de conversão Generica: " + convertedDTO);

		response.setDataList(convertedDTO);

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> cancelAssinatura(@PathVariable("id") Integer id, BindingResult result) {

		Response<AssinaturaDTO> response = new Response<AssinaturaDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		service.cancelById(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private Assinatura convertDtoToEntity(AssinaturaDTO dto) {
		Assinatura entity = new Assinatura();

		entity.setId(dto.getId());

		Status status = new Status();
		status.setId(dto.getStatus());
		entity.setStatus(status);

		entity.setCriadoEm(dto.getCriadoEm());
		entity.setAtualizadoEm(dto.getAtualizadoEm());

		return entity;
	}

	private AssinaturaDTO convertEntityToDto(Assinatura entity) {
		AssinaturaDTO dto = new AssinaturaDTO();
		dto.setId(entity.getId());
		dto.setStatus(entity.getStatus().getId());
		dto.setCriadoEm(entity.getCriadoEm());
		dto.setAtualizadoEm(entity.getAtualizadoEm());

		return dto;
	}

}

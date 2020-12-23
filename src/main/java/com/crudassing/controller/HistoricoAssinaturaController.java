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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudassing.dto.HistoricoAssinaturaDTO;
import com.crudassing.entity.Assinatura;
import com.crudassing.entity.HistoricoAssinatura;
import com.crudassing.service.HistoricoService;
import com.crudassing.util.Response;

@RestController
@RequestMapping("historico")
public class HistoricoAssinaturaController {

	@Autowired
	private HistoricoService service;

	@PostMapping
	public ResponseEntity<Response<HistoricoAssinaturaDTO>> create(@Valid @RequestBody HistoricoAssinaturaDTO dto,
			BindingResult result) {

		dto.setCriadoEm(Timestamp.valueOf(LocalDateTime.now()));

		Response<HistoricoAssinaturaDTO> response = new Response<HistoricoAssinaturaDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		HistoricoAssinatura assinatura = service.save(this.convertDtoToEntity(dto));

		response.setData(this.convertEntityToDto(assinatura));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<Response<HistoricoAssinaturaDTO>> getAll(BindingResult result) {

		Response<HistoricoAssinaturaDTO> response = new Response<HistoricoAssinaturaDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		List<HistoricoAssinatura> listHistoricoAssinatura = service.getAll();

		listHistoricoAssinatura.forEach(assign -> System.out.println(assign.toString()));

		@SuppressWarnings("static-access")
		List<HistoricoAssinaturaDTO> convertedDTO = response.convertList(listHistoricoAssinatura,
				HistoricoAssinaturaDTO.class);

		System.out.println("Teste de conversão Generica: " + convertedDTO);

		response.setDataList(convertedDTO);

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<Response<HistoricoAssinaturaDTO>> updateHistorico(@RequestBody HistoricoAssinatura entity,
			BindingResult result) {

		Response<HistoricoAssinaturaDTO> response = new Response<HistoricoAssinaturaDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		service.save(entity);

		return ResponseEntity.ok().body(response);
	}

	private HistoricoAssinatura convertDtoToEntity(HistoricoAssinaturaDTO dto) {
		HistoricoAssinatura entity = new HistoricoAssinatura();

		entity.setId(dto.getId());

		entity.setTipo(dto.getTipo());

		entity.setCriadoEm(dto.getCriadoEm());

		Assinatura assinatura = new Assinatura();
		assinatura.setId(dto.getAssinatura());

		entity.setAssinatura(assinatura);

		return entity;
	}

	private HistoricoAssinaturaDTO convertEntityToDto(HistoricoAssinatura entity) {
		HistoricoAssinaturaDTO dto = new HistoricoAssinaturaDTO();
		dto.setId(entity.getId());
		dto.setAssinatura(entity.getAssinatura().getId());
		dto.setCriadoEm(entity.getCriadoEm());
		dto.setTipo(entity.getTipo());

		return dto;
	}

}

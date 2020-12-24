package com.crudassing.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.crudassing.dto.AssinaturaDTO;
import com.crudassing.entity.Assinatura;
import com.crudassing.entity.Status;
import com.crudassing.service.AssinaturaService;
import com.crudassing.util.enums.StatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AssinaturaControllerTest {

	private static final Integer ID = 5;
	private static final Integer STATUS = 1;
	private static final LocalDateTime CRIADOEM = LocalDateTime.now().minusDays(2L);
	private static final LocalDateTime ATUALIZADOEM = LocalDateTime.now();
	private static final String URL = "/assinatura";

	@MockBean
	AssinaturaService service;

	@Autowired
	MockMvc mvc;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Test
	public void testSave() throws Exception {

		given(service.save(Mockito.any(Assinatura.class))).willReturn(getMockAssinatura());

		mvc.perform(MockMvcRequestBuilders.post(URL)
				.content(getJsonPayload(ID, STATUS, Timestamp.valueOf(CRIADOEM), Timestamp.valueOf(ATUALIZADOEM)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.status").value(StatusEnum.ATIVA.getValue()))
				.andExpect(jsonPath("$.data.criado_em").value(CRIADOEM.format(formatter)))
				.andExpect(jsonPath("$.data.atualizado_em").value(ATUALIZADOEM.format(formatter)));

	}

	@Test
	public void testGetAll() throws Exception {

		given(service.getAll()).willReturn(getMockListAssinatura());

		mvc.perform(MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.dataList[0].id").value(1))
				.andExpect(jsonPath("$.dataList[0].status").value(StatusEnum.ATIVA.getValue()))
				.andExpect(jsonPath("$.dataList[0].criado_em").value(CRIADOEM.format(formatter)))
				.andExpect(jsonPath("$.dataList[0].atualizado_em").value(ATUALIZADOEM.format(formatter)))
				.andExpect(jsonPath("$.dataList[1].id").value(2))
				.andExpect(jsonPath("$.dataList[1].status").value(StatusEnum.ATIVA.getValue()))
				.andExpect(
						jsonPath("$.dataList[1].criado_em").value(LocalDateTime.now().minusDays(5L).format(formatter)))
				.andExpect(jsonPath("$.dataList[1].atualizado_em")
						.value(LocalDateTime.now().minusDays(2L).format(formatter)))
				.andExpect(jsonPath("$.dataList[2].id").value(3))
				.andExpect(jsonPath("$.dataList[2].status").value(StatusEnum.ATIVA.getValue()))
				.andExpect(
						jsonPath("$.dataList[2].criado_em").value(LocalDateTime.now().minusDays(10L).format(formatter)))
				.andExpect(jsonPath("$.dataList[2].atualizado_em").value(LocalDateTime.now().format(formatter)));

	}

	@Test
	public void testCancelaAssinatura() throws Exception {

		given(service.findById(Mockito.anyInt())).willReturn(Optional.of(getMockAssinatura()));

		mvc.perform(MockMvcRequestBuilders.put(URL + "/1")
				.content(getJsonPayload(ID, STATUS, Timestamp.valueOf(CRIADOEM), Timestamp.valueOf(ATUALIZADOEM)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		Optional<Assinatura> atualizada = service.findById(1);

		assertTrue(atualizada.isPresent());

	}

	public Assinatura getMockAssinatura() {
		Status s = new Status(1, StatusEnum.ATIVA);

		Assinatura a = new Assinatura(s, Timestamp.valueOf(CRIADOEM), Timestamp.valueOf(ATUALIZADOEM));

		a.setId(ID);

		return a;
	}

	public List<Assinatura> getMockListAssinatura() {
		List<Assinatura> list = new ArrayList<Assinatura>();

		Status s = new Status(1, StatusEnum.ATIVA);

		Assinatura a = new Assinatura(s, Timestamp.valueOf(CRIADOEM), Timestamp.valueOf(ATUALIZADOEM));
		Assinatura b = new Assinatura(s, Timestamp.valueOf(LocalDateTime.now().minusDays(5L)),
				Timestamp.valueOf(LocalDateTime.now().minusDays(2L)));
		Assinatura c = new Assinatura(s, Timestamp.valueOf(LocalDateTime.now().minusDays(10L)),
				Timestamp.valueOf(LocalDateTime.now()));

		a.setId(1);
		b.setId(2);
		c.setId(3);

		list.add(a);
		list.add(b);
		list.add(c);

		return list;

	}

	public String getJsonPayload(Integer id, Integer status, Date criadoEm, Date atualizadoEm)
			throws JsonProcessingException {
		AssinaturaDTO dto = new AssinaturaDTO(id, status, criadoEm, atualizadoEm);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}

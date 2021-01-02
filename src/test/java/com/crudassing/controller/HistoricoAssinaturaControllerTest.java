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
import org.mockito.Mock;
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

import com.crudassing.dto.HistoricoAssinaturaDTO;
import com.crudassing.entity.Assinatura;
import com.crudassing.entity.HistoricoAssinatura;
import com.crudassing.entity.Status;
import com.crudassing.service.HistoricoService;
import com.crudassing.util.enums.StatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HistoricoAssinaturaControllerTest {

	private static final Integer ID = 5;
	private static final Integer STATUS = 1;
	private static final LocalDateTime CRIADOEM = LocalDateTime.now().minusDays(2L);
	private static final LocalDateTime ATUALIZADOEM = LocalDateTime.now();
	private static final String URL = "/historico";

	@MockBean
	private HistoricoService service;

	@Autowired
	MockMvc mvc;

	@Mock
	Status status;

	@Mock
	Assinatura assign;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Test
	public void testSave() throws Exception {

		given(service.save(Mockito.any(HistoricoAssinatura.class))).willReturn(getMockHistoricoAssinatura());

		mvc.perform(
				MockMvcRequestBuilders.post(URL).content(getJsonPayload(1, "PIX", 1, Timestamp.valueOf(ATUALIZADOEM)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.status").value(StatusEnum.ATIVA.getValue()))
				.andExpect(jsonPath("$.data.criado_em").value(CRIADOEM.format(formatter)))
				.andExpect(jsonPath("$.data.atualizado_em").value(ATUALIZADOEM.format(formatter)));

	}

	@Test
	public void testGetAll() throws Exception {

		given(service.getAll()).willReturn(getMockListHistoricoAssinatura());

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
	public void testUpdateHistorico() throws Exception {

		mvc.perform(MockMvcRequestBuilders.put(URL + "/1")
				.content(getJsonPayload(1, "PIX", 1, Timestamp.valueOf(ATUALIZADOEM)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testDeleteHistorico() throws Exception {

		mvc.perform(MockMvcRequestBuilders.delete(URL + "/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("Historico com o ID:  " + ID + " foi apagado com sucesso"));

		Optional<HistoricoAssinatura> atualizada = service.getById(1);

		assertTrue(atualizada.isPresent());

	}

	public HistoricoAssinatura getMockHistoricoAssinatura() {
		status = new Status(1, StatusEnum.ATIVA);

		assign = new Assinatura(status, Timestamp.valueOf(LocalDateTime.now().minusDays(5L)),
				Timestamp.valueOf(LocalDateTime.now().minusDays(2L)));

		assign.setId(1);

		HistoricoAssinatura historyAssign = new HistoricoAssinatura("PIX", assign,
				Timestamp.valueOf(LocalDateTime.now()));

		historyAssign.setId(1);

		return historyAssign;
	}

	public List<HistoricoAssinatura> getMockListHistoricoAssinatura() {
		List<HistoricoAssinatura> list = new ArrayList<HistoricoAssinatura>();

		status = new Status(1, StatusEnum.ATIVA);

		assign = new Assinatura(status, Timestamp.valueOf(LocalDateTime.now().minusDays(5L)),
				Timestamp.valueOf(LocalDateTime.now().minusDays(2L)));

		assign.setId(1);

		HistoricoAssinatura a = new HistoricoAssinatura("PIX", assign,
				Timestamp.valueOf(LocalDateTime.now().minusDays(10L)));
		HistoricoAssinatura b = new HistoricoAssinatura("PIX", assign,
				Timestamp.valueOf(LocalDateTime.now().minusDays(5L)));
		HistoricoAssinatura c = new HistoricoAssinatura("PIX", assign, Timestamp.valueOf(LocalDateTime.now()));

		a.setId(1);
		b.setId(2);
		c.setId(3);

		list.add(a);
		list.add(b);
		list.add(c);

		return list;

	}

	public String getJsonPayload(Integer id, String tipo, Integer id_assinatura, Date criadoEm)
			throws JsonProcessingException {
		HistoricoAssinaturaDTO dto = new HistoricoAssinaturaDTO(id, tipo, id_assinatura, criadoEm);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}

}

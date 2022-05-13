package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.NaoEncontradoException;
import com.example.demo.feign.ChaturbateClient;
import com.example.demo.model.ChaturbateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Modelo;
import com.example.demo.repository.ModeloRepository;

@Service
public class ModeloService {

	public static Logger logger = LoggerFactory.getLogger(ModeloService.class);
	
	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private ChaturbateClient chaturbateClient;
	
	public Modelo buscarModelo(Long id) {
		return modeloRepository.findById(id).get();
	}
	
	
	public Modelo salvarModelo(Modelo modelo) {
		return modeloRepository.save(modelo);
	}
	
	public List<Modelo> listarModelos(){
		return modeloRepository.findAll();
	}

	public void atualizarModelo(Long id, Modelo modelo) {
		Modelo modeloAtual = this.buscarModelo(id);

		if (!modelo.getNome().isBlank() || !modelo.getNome().isEmpty()) modeloAtual.setNome(modelo.getNome());

		if (!modelo.getUrlFoto().isBlank() || !modelo.getUrlFoto().isEmpty()) modeloAtual.setUrlFoto(modelo.getUrlFoto());

		modeloRepository.save(modeloAtual);
	}

	public ChaturbateModel buscarChaturbateModelo(String nome) {

		try{
			ChaturbateModel  chaturbateModel = chaturbateClient.getChaturbateModel(nome).getBody();

			if(chaturbateModel.getRoom_status().equals("public")){
				logger.info(chaturbateModel.getBroadcaster_username() + " is online now!");
			}

			return chaturbateModel;
		}
		catch (Exception e){
			throw new NaoEncontradoException("MODELO NAO ENCONTRADA");
		}


	}

	public Page<Modelo> buscarModelosPaginavel(
			int page,
			int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");

		return modeloRepository.buscarModeloPaginavel(pageRequest);
	}

	public Page<Modelo> findAll(){
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
		return new PageImpl<>(modeloRepository.findAll(), pageRequest, size);
	}

}

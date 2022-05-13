package com.example.demo.controller;

import java.util.List;

import com.example.demo.exception.ErroInternoException;
import com.example.demo.exception.NaoEncontradoException;
import com.example.demo.model.ChaturbateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Modelo;
import com.example.demo.service.ModeloService;



@Controller
@RestController
@RequestMapping("/api/modelos")
@CrossOrigin("*")
public class ModeloController {
	
	@Autowired
	private ModeloService modeloService;
	
	
	@GetMapping
	public ResponseEntity<List<Modelo>> listarModelos(){
		List<Modelo> modelos = modeloService.listarModelos();
		return ResponseEntity.ok(modelos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Modelo> buscarModelo(@PathVariable Long id){
		return ResponseEntity.ok(modeloService.buscarModelo(id));
	}
	
	@PostMapping
	public ResponseEntity<Modelo> salvarModelo(@RequestBody Modelo modelo){
		return ResponseEntity.ok(modeloService.salvarModelo(modelo));
	}

	@PutMapping("/{id}")
	public void atualizarModelo(@PathVariable Long id, @RequestBody Modelo modelo){
		modeloService.atualizarModelo(id, modelo);
	}

	@GetMapping("/chat/{nomeModelo}")
	public ResponseEntity<ChaturbateModel> buscarChaturbateModelo(@PathVariable String nomeModelo) throws ErroInternoException, NaoEncontradoException {
		return ResponseEntity.ok(modeloService.buscarChaturbateModelo(nomeModelo));
	}

	@GetMapping("/search")
	public Page<Modelo> getAll(){
		return modeloService.findAll();
	}

	@GetMapping("/search2")
	public  ResponseEntity<Page<Modelo>> getAll2(
			@RequestParam(
					value = "page",
					required = false,
					defaultValue = "0") int page,
			@RequestParam(
					value = "size",
					required = false,
					defaultValue = "4") int size){

		return ResponseEntity.ok(modeloService.buscarModelosPaginavel(page, size));
	}

}

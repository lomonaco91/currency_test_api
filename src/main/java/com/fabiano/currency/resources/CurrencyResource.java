package com.fabiano.currency.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.fabiano.currency.domain.Currency;
import com.fabiano.currency.dto.CurrencyDTO;
import com.fabiano.currency.services.CurrencyService;

@RestController
public class CurrencyResource {

	@Autowired
	private CurrencyService service;

	
	@Operation(
	        summary = "lista MOedas",
	        description = "LIsta moedas especificadas.",
	        responses = {
	            @ApiResponse(responseCode = "201", description = "lista de cotação exibida com sucesso", content = @Content(schema = @Schema(implementation = Currency.class))),
	            @ApiResponse(responseCode = "400", description = "Moeda inválida")
	        }
	    )
	@GetMapping(value = "/currency")
	public ResponseEntity<List<Currency>> getCurrencyRates() {
		return ResponseEntity.ok().body(service.getCurrencyRates());
	}
	
	
	@Operation(
	        summary = "Cria uma nova moeda",
	        description = "Cria uma nova entrada para a moeda especificada.",
	        responses = {
	            @ApiResponse(responseCode = "201", description = "Moeda criada com sucesso", content = @Content(schema = @Schema(implementation = Currency.class))),
	            @ApiResponse(responseCode = "400", description = "Requisição inválida")
	        }
	    )
	@PostMapping(value = "/currency")
	public ResponseEntity<Void> insert(@RequestBody CurrencyDTO objDto) {

		Currency obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	
}

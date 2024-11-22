package com.fabiano.currency.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fabiano.currency.domain.Currency;
import com.fabiano.currency.dto.CurrencyDTO;
import com.fabiano.currency.repository.CurrencyRepository;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository repository;
	
	// private static final String API_URL =
	// "https://api.exchangerate-api.com/v4/latest/";
	private static final String API_URL = "https://economia.awesomeapi.com.br/last/";
	
	
	public List<Currency> findAll(){
		return repository.findAll();
	}

	public Currency insert(Currency obj) {
		
		return repository.insert(obj);
	}
	
	@Cacheable(value = "currency", key = "#id")
	public List<Currency> getCurrencyRates() {
		String base = "USD-BRL,EUR-BRL,BTC-BRL,ARS-BRL";
		String[] currencyArray = { "USDBRL", "EURBRL", "BTCBRL", "ARSBRL" };

		String url = API_URL + base;

		RestTemplate restTemplate = new RestTemplate();
		Map<String, Map<String, Object>> response = restTemplate.getForObject(url, Map.class);

		if (response == null) {
			throw new RuntimeException("Nenhuma resposta recebida da API");
		}

		List<Currency> currencies = new ArrayList<>();

		for (String currencyPair : currencyArray) {
			if (response.containsKey(currencyPair)) {
				Map<String, Object> currencyData = response.get(currencyPair);

				// Cria e popula um objeto Currency com os dados da resposta
				Currency currency = new Currency();
				currency.setCode((String) currencyData.get("code"));
				currency.setCodein((String) currencyData.get("codein"));
				currency.setName((String) currencyData.get("name"));
				currency.setHigh((String) currencyData.get("high"));
				currency.setLow((String) currencyData.get("low"));
				currency.setVarBid((String) currencyData.get("varBid"));
				currency.setPctChange((String) currencyData.get("pctChange"));
				currency.setBid((String) currencyData.get("bid"));
				currency.setAsk((String) currencyData.get("ask"));

				currencies.add(currency);
			}
		}

		return currencies;
	}
	
	public Currency fromDTO(CurrencyDTO objDto) {

		return new Currency(
		        objDto.getId(),
		        objDto.getCode(),
		        objDto.getCodein(),
		        objDto.getName(),
		        objDto.getHigh(),
		        objDto.getLow(),
		        objDto.getVarBid(),
		        objDto.getPctChange(),
		        objDto.getBid(),
		        objDto.getAsk()
		    );
	}
}

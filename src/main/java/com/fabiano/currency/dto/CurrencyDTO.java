package com.fabiano.currency.dto;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;

public class CurrencyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String code;
	private String codein;
	private String name;
	private String high;
	private String low;
	private String varBid;
	private String pctChange;
	private String bid;
	private String ask;
	
	
	public CurrencyDTO() {
		
	}


	public CurrencyDTO(String id, String code, String codein, String name, String high, String low, String varBid,
			String pctChange, String bid, String ask) {
		super();
		this.id = id;
		this.code = code;
		this.codein = codein;
		this.name = name;
		this.high = high;
		this.low = low;
		this.varBid = varBid;
		this.pctChange = pctChange;
		this.bid = bid;
		this.ask = ask;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCodein() {
		return codein;
	}


	public void setCodein(String codein) {
		this.codein = codein;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHigh() {
		return high;
	}


	public void setHigh(String high) {
		this.high = high;
	}


	public String getLow() {
		return low;
	}


	public void setLow(String low) {
		this.low = low;
	}


	public String getVarBid() {
		return varBid;
	}


	public void setVarBid(String varBid) {
		this.varBid = varBid;
	}


	public String getPctChange() {
		return pctChange;
	}


	public void setPctChange(String pctChange) {
		this.pctChange = pctChange;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getAsk() {
		return ask;
	}


	public void setAsk(String ask) {
		this.ask = ask;
	}

}

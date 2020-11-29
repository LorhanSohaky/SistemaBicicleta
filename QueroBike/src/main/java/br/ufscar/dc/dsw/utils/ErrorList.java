package br.ufscar.dc.dsw.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ErrorList implements Serializable {
	private static final long serialVersionUID = 1L;
	private final List<String> erros;
    
	public ErrorList() {
		erros = new ArrayList<>();
	}	
	public ErrorList(String message) {
		erros = new ArrayList<>();
		erros.add(message);
	}
	
	public void add(String message) {
		erros.add(message);
	}
	public boolean isNotEmpty() {
		return !erros.isEmpty();
	}
	public List<String> getErrors() {
		return erros;
	}
}
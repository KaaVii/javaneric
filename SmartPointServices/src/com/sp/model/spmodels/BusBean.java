package com.sp.model.spmodels;

public class BusBean {
	/**
	 * @author dan
	 */
	private int id;
	private int idApp;
	
	//remover
	private String nome;
	//******
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Onibus: [ id=" + id + ", idApp=" + idApp +  " ]";
	}
	
	public String toStringid() {
		return "Ponto [ id=" + id + " ]";
		}
	
	public int getIdOnibus() {
		return id;
	}
	public void setIdOnibus(int idOnibus) {
		this.id = idOnibus;
	}
	public int getIdApp() {
		return idApp;
	}
	public void setIdApp(int idApp) {
		this.idApp = idApp;
	}
}

package com.sp.model.spmodels;

public class LinhasBean {
	/**
	 * @author dan
	 */
	//pontos
	private int idPonto;
	private int idLinha;

	private int idOnibus;
	public int getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(int idOnibus) {
		this.idOnibus = idOnibus;
	}

	
	
	
	public int getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}

	public int getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(int idLinha) {
		this.idLinha = idLinha;
	}

	
	public String toString() {
		return "Ponto: [ idLinha=" + idLinha + ", idPonto=" + idPonto + ", idOnibus="
				+ idOnibus  + " ]";
	}

	}

package com.sp.model.spmodels;

import java.io.Serializable;

public class BusLinhasPontoClienteRelationship {
	/**
	 * @author dan
	 */
	

	private BusBean bus = new BusBean();
	private LinhasBean linhas = new LinhasBean();
	private PontoBean ponto = new PontoBean();
	private String nomeCliente;
	private int distancia;
	
	

	@Override
	public String toString() {
		return "Ponto: [ BusBean=" + bus.toString() + ", LinhaBean=" + linhas.toString() + ", PontoBean="
				+ ponto.toString() +  "nomeCliente= "+ nomeCliente +"distancia="+ distancia +" ]";
	}
	
	public Integer getDistancia() {
		return distancia;
	}
	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BusBean getBus() {
		return bus;
	}
	public void setBus(BusBean bus) {
		this.bus = bus;
	}
	public LinhasBean getLinhas() {
		return linhas;
	}
	public void setLinhas(LinhasBean linhas) {
		this.linhas = linhas;
	}
	public PontoBean getPonto() {
		return ponto;
	}
	public void setPonto(PontoBean ponto) {
		this.ponto = ponto;
	}
	
	
}

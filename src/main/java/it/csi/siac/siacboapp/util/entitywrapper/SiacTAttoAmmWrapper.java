/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

public class SiacTAttoAmmWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113501935479168505L;
	private Integer anno;
	private Integer numero;
	private String oggetto;
	private EntityCodificaWrapper sac;
	private EntityCodificaWrapper tipo;
	private EntityCodificaWrapper stato;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public EntityCodificaWrapper getSac() {
		return sac;
	}

	public void setSac(EntityCodificaWrapper sac) {
		this.sac = sac;
	}

	public EntityCodificaWrapper getTipo() {
		return tipo;
	}

	public void setTipo(EntityCodificaWrapper tipo) {
		this.tipo = tipo;
	}

	public EntityCodificaWrapper getStato() {
		return stato;
	}

	public void setStato(EntityCodificaWrapper stato) {
		this.stato = stato;
	}
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.math.BigDecimal;
import java.util.Date;

public class SiacTSubdocWrapper extends BaseEntityWrapper {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1804615616264725240L;
	
	
	private String convalidaManuale;
	private Date dataPagamentoCec;
	private Date dataScadenza;
	private String descrizione;
	private BigDecimal importo;
	private BigDecimal importoDaDedurre;
	private String nregIva;
	private Integer numero;
	private Boolean pagatoCec;
	private BigDecimal splitreverseImporto;

	public String getConvalidaManuale() {
		return convalidaManuale;
	}

	public void setConvalidaManuale(String convalidaManuale) {
		this.convalidaManuale = convalidaManuale;
	}

	public Date getDataPagamentoCec() {
		return dataPagamentoCec;
	}

	public void setDataPagamentoCec(Date dataPagamentoCec) {
		this.dataPagamentoCec = dataPagamentoCec;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public BigDecimal getImportoDaDedurre() {
		return importoDaDedurre;
	}

	public void setImportoDaDedurre(BigDecimal importoDaDedurre) {
		this.importoDaDedurre = importoDaDedurre;
	}

	public String getNregIva() {
		return nregIva;
	}

	public void setNregIva(String nregIva) {
		this.nregIva = nregIva;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getPagatoCec() {
		return pagatoCec;
	}

	public void setPagatoCec(Boolean pagatoCec) {
		this.pagatoCec = pagatoCec;
	}

	public BigDecimal getSplitreverseImporto() {
		return splitreverseImporto;
	}

	public void setSplitreverseImporto(BigDecimal splitreverseImporto) {
		this.splitreverseImporto = splitreverseImporto;
	}

}
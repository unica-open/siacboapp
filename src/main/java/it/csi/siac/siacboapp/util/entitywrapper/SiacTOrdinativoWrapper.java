/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.math.BigDecimal;
import java.util.Date;

public class SiacTOrdinativoWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1040815075806347284L;
	private BigDecimal numero;
	private String descrizione;
	private Integer anno;
	private EntityCodificaWrapper tipo;
	private Date dataAnnullamento;
	private Date dataEmissione;
	private Date dataTrasmissioneOil;
	private Date dataSpostamento;
	private EntityCodificaWrapper stato;
	private Boolean daTrasmettere;
	private String codiceFlusso;
	private BigDecimal importo;

	public BigDecimal getNumero() {
		return numero;
	}

	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public EntityCodificaWrapper getTipo() {
		return tipo;
	}

	public void setTipo(EntityCodificaWrapper tipo) {
		this.tipo = tipo;
	}

	public Date getDataAnnullamento() {
		return dataAnnullamento;
	}

	public void setDataAnnullamento(Date dataAnnullamento) {
		this.dataAnnullamento = dataAnnullamento;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public Date getDataTrasmissioneOil() {
		return dataTrasmissioneOil;
	}

	public void setDataTrasmissioneOil(Date dataTrasmissioneOil) {
		this.dataTrasmissioneOil = dataTrasmissioneOil;
	}

	public Date getDataSpostamento() {
		return dataSpostamento;
	}

	public void setDataSpostamento(Date dataSpostamento) {
		this.dataSpostamento = dataSpostamento;
	}

	public EntityCodificaWrapper getStato() {
		return stato;
	}

	public void setStato(EntityCodificaWrapper stato) {
		this.stato = stato;
	}

	public Boolean getDaTrasmettere() {
		return daTrasmettere;
	}

	public void setDaTrasmettere(Boolean daTrasmettere) {
		this.daTrasmettere = daTrasmettere;
	}

	public String getCodiceFlusso() {
		return codiceFlusso;
	}

	public void setCodiceFlusso(String codiceFlusso) {
		this.codiceFlusso = codiceFlusso;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

}

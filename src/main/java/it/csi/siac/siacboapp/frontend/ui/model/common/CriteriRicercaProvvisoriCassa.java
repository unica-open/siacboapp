/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.siac.siaccommon.util.number.NumberUtil;

public class CriteriRicercaProvvisoriCassa implements Serializable {

	private static final long serialVersionUID = 7344336227491328075L;

	private Integer annoDa;
	private Integer annoA;
	private Integer numeroDa;
	private Integer numeroA;
	private String descrizioneCausale;
	private String descrizioneSottoCausale;
	private String descrizioneSoggetto;
	private String codiceTipo;
	private Date dataEmissioneDa;
	private Date dataEmissioneA;
	private BigDecimal importoDa;
	private BigDecimal importoA;
	private boolean importoDaRegolarizzarePositivo = false;
	private Integer dataRegolarizzazionePresente;
	private Integer dataAnnullamentoPresente;

	public Integer getAnnoDa() {
		return annoDa;
	}

	public void setAnnoDa(Integer annoDa) {
		this.annoDa = annoDa;
	}

	public Integer getAnnoA() {
		return annoA;
	}

	public void setAnnoA(Integer annoA) {
		this.annoA = annoA;
	}

	public Integer getNumeroDa() {
		return numeroDa;
	}

	public void setNumeroDa(Integer numeroDa) {
		this.numeroDa = numeroDa;
	}

	public Integer getNumeroA() {
		return numeroA;
	}

	public void setNumeroA(Integer numeroA) {
		this.numeroA = numeroA;
	}

	public Date getDataEmissioneDa() {
		return dataEmissioneDa;
	}

	public void setDataEmissioneDa(Date dataEmissioneDa) {
		this.dataEmissioneDa = dataEmissioneDa;
	}

	public Date getDataEmissioneA() {
		return dataEmissioneA;
	}

	public void setDataEmissioneA(Date dataEmissioneA) {
		this.dataEmissioneA = dataEmissioneA;
	}

	public BigDecimal getImportoDa() {
		return importoDa;
	}

	public void setImportoDa(BigDecimal importoDa) {
		this.importoDa = importoDa;
	}

	public BigDecimal getImportoA() {
		return importoA;
	}

	public void setImportoA(BigDecimal importoA) {
		this.importoA = importoA;
	}

	public String getCodiceTipo() {
		return codiceTipo;
	}

	public void setCodiceTipo(String codiceTipo) {
		this.codiceTipo = codiceTipo;
	}
	
	public void setImportoDa(String importoDa) {
		this.importoDa = NumberUtil.importoToBigDecimal(importoDa);
	}

	public void setImportoA(String importoA) {
		this.importoA = NumberUtil.importoToBigDecimal(importoA);
	}

	public boolean isImportoDaRegolarizzarePositivo() {
		return importoDaRegolarizzarePositivo;
	}

	public void setImportoDaRegolarizzarePositivo(boolean importoDaRegolarizzarePositivo) {
		this.importoDaRegolarizzarePositivo = importoDaRegolarizzarePositivo;
	}

	public Integer getDataAnnullamentoPresente() {
		return dataAnnullamentoPresente;
	}

	public void setDataAnnullamentoPresente(Integer dataAnnullamentoPresente) {
		this.dataAnnullamentoPresente = dataAnnullamentoPresente;
	}

	public Integer getDataRegolarizzazionePresente() {
		return dataRegolarizzazionePresente;
	}

	public void setDataRegolarizzazionePresente(Integer dataRegolarizzazionePresente) {
		this.dataRegolarizzazionePresente = dataRegolarizzazionePresente;
	}

	public String getDescrizioneCausale() {
		return descrizioneCausale;
	}

	public void setDescrizioneCausale(String descrizioneCausale) {
		this.descrizioneCausale = descrizioneCausale;
	}

	public String getDescrizioneSottoCausale() {
		return descrizioneSottoCausale;
	}

	public void setDescrizioneSottoCausale(String descrizioneSottoCausale) {
		this.descrizioneSottoCausale = descrizioneSottoCausale;
	}

	public String getDescrizioneSoggetto() {
		return descrizioneSoggetto;
	}

	public void setDescrizioneSoggetto(String descrizioneSoggetto) {
		this.descrizioneSoggetto = descrizioneSoggetto;
	}
}

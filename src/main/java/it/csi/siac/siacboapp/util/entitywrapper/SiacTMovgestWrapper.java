/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.math.BigDecimal;

import org.apache.commons.lang3.ObjectUtils;

import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;

public abstract class SiacTMovgestWrapper extends BaseEntityWrapper {

	private static final long serialVersionUID = -2794482215050783158L;

	private Integer anno;
	private BigDecimal numero;
	private String descrizione;
	private SiacTSoggettoWrapper soggetto;
	private SiacDSoggettoClasse classeSoggetto;
	private SiacTClassWrapper strutturaAmministrativoContabile;
	private String cup;
	private Boolean flagSoggettoDurc;
	private Boolean flagSdf;
	private Boolean flagPrenotazione;
	private Boolean flagPrenotazioneLiquidabile;
	private Boolean parereFinanziario;
	private SiacTAttoAmmWrapper attoAmministrativo;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

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

	public SiacTSoggettoWrapper getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggettoWrapper soggetto) {
		this.soggetto = soggetto;
	}

	public SiacDSoggettoClasse getClasseSoggetto() {
		return classeSoggetto;
	}

	public void setClasseSoggetto(SiacDSoggettoClasse classeSoggetto) {
		this.classeSoggetto = classeSoggetto;
	}
	public SiacTClassWrapper getStrutturaAmministrativoContabile() {
		return strutturaAmministrativoContabile;
	}

	public void setStrutturaAmministrativoContabile(SiacTClassWrapper strutturaAmministrativoContabile) {
		this.strutturaAmministrativoContabile = strutturaAmministrativoContabile;
	}

	public String getCup() {
		return cup;
	}

	public void setCup(String cup) {
		this.cup = cup;
	}

	public Boolean getFlagSoggettoDurc() {
		return ObjectUtils.defaultIfNull(flagSoggettoDurc, Boolean.FALSE);
	}

	public void setFlagSoggettoDurc(Boolean flagSoggettoDurc) {
		this.flagSoggettoDurc = flagSoggettoDurc;
	}

	public Boolean getFlagSdf() {
		return ObjectUtils.defaultIfNull(flagSdf, Boolean.FALSE);
	}

	public void setFlagSdf(Boolean flagSdf) {
		this.flagSdf = flagSdf;
	}

	public Boolean getFlagPrenotazione() {
		return ObjectUtils.defaultIfNull(flagPrenotazione, Boolean.FALSE);
	}
	
	public void setFlagPrenotazione(Boolean flagPrenotazione) {
		this.flagPrenotazione = flagPrenotazione;
	}

	public Boolean getFlagPrenotazioneLiquidabile() {
		return ObjectUtils.defaultIfNull(flagPrenotazioneLiquidabile, Boolean.FALSE);
	}

	public void setFlagPrenotazioneLiquidabile(Boolean flagPrenotazioneLiquidabile) {
		this.flagPrenotazioneLiquidabile = flagPrenotazioneLiquidabile;
	}

	public Boolean getParereFinanziario() {
		return parereFinanziario;
	}

	public void setParereFinanziario(Boolean parereFinanziario) {
		this.parereFinanziario = parereFinanziario;
	}

	public SiacTAttoAmmWrapper getAttoAmministrativo() {
		return attoAmministrativo;
	}

	public void setAttoAmministrativo(SiacTAttoAmmWrapper attoAmministrativo) {
		this.attoAmministrativo = attoAmministrativo;
	}}

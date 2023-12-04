/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import it.csi.siac.siacboapp.util.DefaultDateConverter;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

public class CriteriRicercaOrdinativi implements Serializable {
	public static final Date MIN_DATE = new GregorianCalendar(1900, 0, 1).getTime();
	public static final Date MAX_DATE = new GregorianCalendar(9999, 0, 1).getTime();

	private static final long serialVersionUID = -9070522334726079016L;
	
	private Integer numeroDa;
	private Integer numeroA;
	private Integer anno;
	private Date dataEmissioneDa;
	private Date dataEmissioneA;
	private Date dataTrasmissioneOilDa;
	private Date dataTrasmissioneOilA;
	
	//Evolutiva BackofficeGestioneOrdinativi
	private Date dataOrdSpostamentoDa;
	private Date dataOrdSpostamentoA;
	
	private Integer dataTrasmissioneOilPresente;
	private String[] codiciStato;
	private String[] codiciStatoEsclusi;
	private String codiceTipo;
	private String codiceFlusso;
	private Integer idCodiceDistinta;
	private Boolean includiVBDaTrasmettere;
	private Boolean includiAnnulliDaTrasmettere;
	private Integer daTrasmettere;
	private SiacTAttoAmmWrapper attoAmministrativo; 
	private SiacTSoggettoWrapper soggetto;
	private boolean escludiCollegatiProvvisoriCassa = false;
	
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

	public Date getDataTrasmissioneOilDa() {
		return dataTrasmissioneOilDa;
	}

	public void setDataTrasmissioneOilDa(Date dataTrasmissioneOilDa) {
		this.dataTrasmissioneOilDa = dataTrasmissioneOilDa;
	}

	public Date getDataTrasmissioneOilA() {
		return dataTrasmissioneOilA;
	}

	public void setDataTrasmissioneOilA(Date dataTrasmissioneOilA) {
		this.dataTrasmissioneOilA = dataTrasmissioneOilA;
	}

	// EVOLUTIVA BackofficeGestioneOrdinativi
	public Date getDataOrdSpostamentoDa() {
		return dataOrdSpostamentoDa;
	}
	
	// EVOLUTIVA BackofficeGestioneOrdinativi
	public void setDataOrdSpostamentoDa(Date dataOrdSpostamentoDa) {
		this.dataOrdSpostamentoDa = dataOrdSpostamentoDa;
	}
	
	// EVOLUTIVA BackofficeGestioneOrdinativi
	public Date getDataOrdSpostamentoA() {
		return dataOrdSpostamentoA;
	}
	
	// EVOLUTIVA BackofficeGestioneOrdinativi
	public void setDataOrdSpostamentoA(Date dataOrdSpostamentoA) {
		this.dataOrdSpostamentoA = dataOrdSpostamentoA;
	}

	public void setDataEmissioneDaStr(String dataEmissioneDaStr) {
		setDataEmissioneDa(DefaultDateConverter.convertToDefaultDate(dataEmissioneDaStr, MIN_DATE)); 
	}

	public void setDataEmissioneAStr(String dataEmissioneAStr) {
		setDataEmissioneA(DefaultDateConverter.convertToDefaultDate(dataEmissioneAStr, MAX_DATE));
	}

	public void setDataTrasmissioneOilDaStr(String dataTrasmissioneOilDaStr) {
		setDataTrasmissioneOilDa(DefaultDateConverter.convertToDefaultDate(dataTrasmissioneOilDaStr, MIN_DATE));
	}

	public void setDataTrasmissioneOilAStr(String dataTrasmissioneOilAStr) {
		setDataTrasmissioneOilA(DefaultDateConverter.convertToDefaultDate(dataTrasmissioneOilAStr, MAX_DATE));
	}

	// EVOLUTIVA BackofficeGestioneOrdinativi
	public void setDataOrdSpostamentoDaStr(String dataOrdSpostamentoDa) {
//		setDataOrdSpostamentoDa(DefaultDateConverter.convertToDefaultDate(dataOrdSpostamentoDa, MIN_DATE));
		setDataOrdSpostamentoDa(DefaultDateConverter.convertToDefaultDate(dataOrdSpostamentoDa, null));
	}
	
	// EVOLUTIVA BackofficeGestioneOrdinativi
	public void setDataOrdSpostamentoAStr(String dataOrdSpostamentoA) {
//		setDataOrdSpostamentoA(DefaultDateConverter.convertToDefaultDate(dataOrdSpostamentoA, MAX_DATE));
		setDataOrdSpostamentoA(DefaultDateConverter.convertToDefaultDate(dataOrdSpostamentoA, null));
	}

	public String getCodiceTipo() {
		return codiceTipo;
	}

	public void setCodiceTipo(String codiceTipo) {
		this.codiceTipo = codiceTipo;
	}

	public Integer getDataTrasmissioneOilPresente() {
		return dataTrasmissioneOilPresente;
	}

	public void setDataTrasmissioneOilPresente(Integer dataTrasmissioneOilPresente) {
		this.dataTrasmissioneOilPresente = dataTrasmissioneOilPresente;
	}

	public String[] getCodiciStatoEsclusi() {
		return codiciStatoEsclusi;
	}

	public void setCodiciStatoEsclusi(String[] codiciStatoEsclusi) {
		this.codiciStatoEsclusi = codiciStatoEsclusi;
	}

	public String[] getCodiciStato() {
		return codiciStato;
	}

	public void setCodiciStato(String[] codiciStato) {
		this.codiciStato = codiciStato;
	}

	public String getCodiceFlusso() {
		return codiceFlusso;
	}

	public void setCodiceFlusso(String codiceFlusso) {
		this.codiceFlusso = codiceFlusso;
	}

	/**
	 * @return the anno
	 */
	public Integer getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	/**
	 * @return the includiVBDaTrasmettere
	 */
	public Boolean getIncludiVBDaTrasmettere() {
		return includiVBDaTrasmettere;
	}

	/**
	 * @param includiVBDaTrasmettere the includiVBDaTrasmettere to set
	 */
	public void setIncludiVBDaTrasmettere(Boolean includiVBDaTrasmettere) {
		this.includiVBDaTrasmettere = includiVBDaTrasmettere;
	}

	/**
	 * @return the includiAnnulliDaTrasmettere
	 */
	public Boolean getIncludiAnnulliDaTrasmettere() {
		return includiAnnulliDaTrasmettere;
	}

	/**
	 * @param includiAnnulliDaTrasmettere the includiAnnulliDaTrasmettere to set
	 */
	public void setIncludiAnnulliDaTrasmettere(Boolean includiAnnulliDaTrasmettere) {
		this.includiAnnulliDaTrasmettere = includiAnnulliDaTrasmettere;
	}

	public SiacTAttoAmmWrapper getAttoAmministrativo() {
		return attoAmministrativo;
	}

	public void setAttoAmministrativo(SiacTAttoAmmWrapper attoAmministrativo) {
		this.attoAmministrativo = attoAmministrativo;
	}

	public Integer getDaTrasmettere() {
		return daTrasmettere;
	}

	public void setDaTrasmettere(Integer daTrasmettere) {
		this.daTrasmettere = daTrasmettere;
	}

	public SiacTSoggettoWrapper getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggettoWrapper soggetto) {
		this.soggetto = soggetto;
	}

	public boolean getEscludiCollegatiProvvisoriCassa() {
		return escludiCollegatiProvvisoriCassa;
	}

	public void setEscludiCollegatiProvvisoriCassa(boolean escludiCollegatiProvvisoriCassa) {
		this.escludiCollegatiProvvisoriCassa = escludiCollegatiProvvisoriCassa;
	}

	public Integer getIdCodiceDistinta() {
		return idCodiceDistinta;
	}

	public void setIdCodiceDistinta(Integer idCodiceDistinta) {
		this.idCodiceDistinta = idCodiceDistinta;
	}

}

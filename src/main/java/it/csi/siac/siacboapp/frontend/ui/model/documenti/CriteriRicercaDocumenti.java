/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.documenti;

import java.io.Serializable;
import java.util.Date;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

public class CriteriRicercaDocumenti implements Serializable {

	private static final long serialVersionUID = 7098450757795628153L;

	private String codiceTipologia;
	private String codiceTipo;
	private Integer anno;
	private String numero;
	private Date dataEmissione;
	private SiacTSoggettoWrapper soggetto;

	public String getCodiceTipologia() {
		return codiceTipologia;
	}

	public void setCodiceTipologia(String codiceTipologia) {
		this.codiceTipologia = codiceTipologia;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodiceTipo() {
		return codiceTipo;
	}

	public void setCodiceTipo(String codiceTipo) {
		this.codiceTipo = codiceTipo;
	}

	public SiacTSoggettoWrapper getSoggetto() {
		return soggetto;
	}

	public void setSoggetto(SiacTSoggettoWrapper soggetto) {
		this.soggetto = soggetto;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

}

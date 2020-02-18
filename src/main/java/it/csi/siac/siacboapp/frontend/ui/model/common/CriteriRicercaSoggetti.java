/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.io.Serializable;

public class CriteriRicercaSoggetti implements Serializable {

	private static final long serialVersionUID = 7124472157564281613L;

	private String codice;
	private String codiceFiscale;
	private String partitaIva;
	private String denominazione;
	private Integer idClasse;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Integer getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Integer idClasse) {
		this.idClasse = idClasse;
	}

}

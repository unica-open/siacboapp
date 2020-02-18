/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

public class SiacTSoggettoWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3523979310137386906L;
	private String codice;
	private String codiceFiscale;
	private String partitaIva;
	private String descrizione;
	private EntityCodificaWrapper stato;

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public EntityCodificaWrapper getStato() {
		return stato;
	}

	public void setStato(EntityCodificaWrapper stato) {
		this.stato = stato;
	}
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

public class SiacTBilWrapper extends BaseEntityWrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codice;
	private String descrizione;
	private SiacTPeriodoWrapper periodo;
	
	public SiacTBilWrapper() {}
	
	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}
	
	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	/**
	 * @return the periodo
	 */
	public SiacTPeriodoWrapper getPeriodo() {
		return periodo;
	}
	
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(SiacTPeriodoWrapper periodo) {
		this.periodo = periodo;
	}
	
	
}

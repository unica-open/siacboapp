/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

public class SiacDEventoWrapper {

	private Integer uid;
	private String codice;
	private String descrizione;
	private SiacDEventoTipoWrapper siacDEventoTipo;
	private SiacDCollegamentoTipoWrapper siacDCollegamentoTipo;

	public SiacDEventoWrapper() {}

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

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
	 * @return the siacDEventoTipo
	 */
	public SiacDEventoTipoWrapper getSiacDEventoTipo() {
		return siacDEventoTipo;
	}

	/**
	 * @param siacDEventoTipo the siacDEventoTipo to set
	 */
	public void setSiacDEventoTipo(SiacDEventoTipoWrapper siacDEventoTipo) {
		this.siacDEventoTipo = siacDEventoTipo;
	}

	/**
	 * @return the siacDCollegamentoTipo
	 */
	public SiacDCollegamentoTipoWrapper getSiacDCollegamentoTipo() {
		return siacDCollegamentoTipo;
	}

	/**
	 * @param siacDCollegamentoTipo the siacDCollegamentoTipo to set
	 */
	public void setSiacDCollegamentoTipo(SiacDCollegamentoTipoWrapper siacDCollegamentoTipo) {
		this.siacDCollegamentoTipo = siacDCollegamentoTipo;
	}
	
	

}

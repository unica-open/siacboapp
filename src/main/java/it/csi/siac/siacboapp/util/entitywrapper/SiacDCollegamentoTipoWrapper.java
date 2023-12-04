/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.util.List;

public class SiacDCollegamentoTipoWrapper {

	private Integer uid;	
	private String codice;
	private String descrizione;
	private List<SiacDEventoWrapper> siacDEventos;
	
	public SiacDCollegamentoTipoWrapper() {}

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
	 * @return the siacDEventos
	 */
	public List<SiacDEventoWrapper> getSiacDEventos() {
		return siacDEventos;
	}

	/**
	 * @param siacDEventos the siacDEventos to set
	 */
	public void setSiacDEventos(List<SiacDEventoWrapper> siacDEventos) {
		this.siacDEventos = siacDEventos;
	}
	
	

}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.io.Serializable;

public class CriteriRicercaProvvedimenti implements Serializable {

	private static final long serialVersionUID = -7937153251566036956L;

	private Integer anno;
	private Integer numero;
	private Integer idTipo;
	private Integer idStrutturaAmministrativoContabile;
	private String oggetto;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public Integer getIdStrutturaAmministrativoContabile() {
		return idStrutturaAmministrativoContabile;
	}

	public void setIdStrutturaAmministrativoContabile(Integer idStrutturaAmministrativoContabile) {
		this.idStrutturaAmministrativoContabile = idStrutturaAmministrativoContabile;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

}

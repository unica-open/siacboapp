/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.MifTFlussoElaborato;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RisultatiRicercaFlussiModel extends GenericModel {
	private static final long serialVersionUID = -7920036784911470784L;

	private List<MifTFlussoElaborato> elencoFlussi;
	private String codiceTipoFlussi;
	//SIAC-7615
	private Integer uidFlussoConsulta;

	public List<MifTFlussoElaborato> getElencoFlussi() {
		return elencoFlussi;
	}

	public void setElencoFlussi(List<MifTFlussoElaborato> elencoFlussi) {
		this.elencoFlussi = elencoFlussi;
	}

	public String getCodiceTipoFlussi() {
		return codiceTipoFlussi;
	}

	public void setCodiceTipoFlussi(String codiceTipoFlussi) {
		this.codiceTipoFlussi = codiceTipoFlussi;
	}

	/**
	 * @return the uidFlussoConsulta
	 */
	public Integer getUidFlussoConsulta() {
		return uidFlussoConsulta;
	}

	/**
	 * @param uidFlussoConsulta the uidFlussoConsulta to set
	 */
	public void setUidFlussoConsulta(Integer uidFlussoConsulta) {
		this.uidFlussoConsulta = uidFlussoConsulta;
	}

	
}

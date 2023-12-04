/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

public class AssociaProvvisoriCassaModel extends ConfermaGestioneOrdinativiModel {

	private static final long serialVersionUID = -3604421420961402127L;

	private Integer[] idProvvisoriCassa;
	private String elencoIdProvvisoriCassa;

	@Override
	public String getOperazione() {
		return "associa provvisori di cassa";
	}

	public boolean getMostraPulsanteApriModaleProvvisorioCassa() {
		return true;
	}

	public Integer[] getIdProvvisoriCassa() {
		
		if (idProvvisoriCassa == null) {
			idProvvisoriCassa= elencoToIdArray(elencoIdProvvisoriCassa);
		}
		
		return idProvvisoriCassa;
	}

//	public void setIdProvvisoriCassa(Integer[] idProvvisoriCassa) {
//		this.idProvvisoriCassa = idProvvisoriCassa;
//	}
//
	public String getElencoIdProvvisoriCassa() {
		return elencoIdProvvisoriCassa;
	}

	public void setElencoIdProvvisoriCassa(String elencoIdProvvisoriCassa) {
		this.elencoIdProvvisoriCassa = elencoIdProvvisoriCassa;
	}
}

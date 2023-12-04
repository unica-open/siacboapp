/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.decodifica;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;
import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoDecodificheModel extends GenericModel {
	private static final long serialVersionUID = -5114044388304112560L;

	private List<BkoTSystemTable> elencoTabelleDecodifica;
	private Integer idTabellaSelezionata;
	private List<SiacDecodifica> elencoDecodifiche;

	public List<SiacDecodifica> getElencoDecodifiche() {
		return elencoDecodifiche;
	}

	public void setElencoDecodifiche(List<SiacDecodifica> elencoDecodifiche) {
		this.elencoDecodifiche = elencoDecodifiche;
	}

	public List<BkoTSystemTable> getElencoTabelleDecodifica() {
		return elencoTabelleDecodifica;
	}

	public void setElencoTabelleDecodifica(List<BkoTSystemTable> elencoTabelleDecodifica) {
		this.elencoTabelleDecodifica = elencoTabelleDecodifica;
	}

	public Integer getIdTabellaSelezionata() {
		return idTabellaSelezionata;
	}

	public void setIdTabellaSelezionata(Integer idTabellaSelezionata) {
		this.idTabellaSelezionata = idTabellaSelezionata;
	}
}

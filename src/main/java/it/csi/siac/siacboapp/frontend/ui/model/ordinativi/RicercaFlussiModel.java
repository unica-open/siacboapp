/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoTipo;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RicercaFlussiModel extends GenericModel {
	private static final long serialVersionUID = -6155576148318081564L;

	private List<SiacDOrdinativoTipo> elencoTipiFlusso;
	private CriteriRicercaFlussi criteri = new CriteriRicercaFlussi();


	public CriteriRicercaFlussi getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaFlussi criteri) {
		this.criteri = criteri;
	}

	public List<SiacDOrdinativoTipo> getElencoTipiFlusso() {
		return elencoTipiFlusso;
	}

	public void setElencoTipiFlusso(List<SiacDOrdinativoTipo> elencoTipiFlusso) {
		this.elencoTipiFlusso = elencoTipiFlusso;
	}
}

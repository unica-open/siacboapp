/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppoazioni;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoGruppiAzioniModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492292561264162824L;

	private List<SiacDGruppoAzioni> elencoGruppiAzioni;

	public List<SiacDGruppoAzioni> getElencoGruppiAzioni() {
		return elencoGruppiAzioni;
	}

	public void setElencoGruppiAzioni(List<SiacDGruppoAzioni> elencoGruppiAzioni) {
		this.elencoGruppiAzioni = elencoGruppiAzioni;
	}

}

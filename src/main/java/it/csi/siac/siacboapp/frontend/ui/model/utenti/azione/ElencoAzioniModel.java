/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.azione;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTAzione;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoAzioniModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2387644569609058723L;

	private List<SiacTAzione> elencoAzioni;

	public List<SiacTAzione> getElencoAzioni() {
		return elencoAzioni;
	}

	public void setElencoAzioni(List<SiacTAzione> elencoAzioni) {
		this.elencoAzioni = elencoAzioni;
	}

}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.gruppo;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoGruppiModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492292561264162824L;
	/**
	 * 
	 */
	/**
	 * 
	 */

	private List<SiacTGruppo> elencoGruppi;

	public List<SiacTGruppo> getElencoGruppi() {
		return elencoGruppi;
	}

	public void setElencoGruppi(List<SiacTGruppo> elencoGruppi) {
		this.elencoGruppi = elencoGruppi;
	}

}

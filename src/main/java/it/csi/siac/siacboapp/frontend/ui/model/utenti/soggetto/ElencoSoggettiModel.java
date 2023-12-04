/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.soggetto;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoSoggettiModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7921453052861251132L;

	private List<SiacTSoggetto> elencoSoggetti;

	public List<SiacTSoggetto> getElencoSoggetti() {
		return elencoSoggetti;
	}

	public void setElencoSoggetti(List<SiacTSoggetto> elencoSoggetti) {
		this.elencoSoggetti = elencoSoggetti;
	}

}

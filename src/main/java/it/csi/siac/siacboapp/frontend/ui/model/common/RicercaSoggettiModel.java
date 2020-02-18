/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.util.List;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RicercaSoggettiModel extends GenericModel {

	private static final long serialVersionUID = 7830373656043150422L;

	private CriteriRicercaSoggetti criteriSoggetti;
	private List<SiacTSoggettoWrapper> elencoSoggetti;

	public CriteriRicercaSoggetti getCriteriSoggetti() {
		return criteriSoggetti;
	}

	public void setCriteriSoggetti(CriteriRicercaSoggetti criteriSoggetti) {
		this.criteriSoggetti = criteriSoggetti;
	}

	public List<SiacTSoggettoWrapper> getElencoSoggetti() {
		return elencoSoggetti;
	}

	public void setElencoSoggetti(List<SiacTSoggettoWrapper> elencoSoggetti) {
		this.elencoSoggetti = elencoSoggetti;
	}

}

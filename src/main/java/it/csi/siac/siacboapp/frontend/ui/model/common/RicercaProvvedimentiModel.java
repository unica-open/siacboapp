/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.util.List;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RicercaProvvedimentiModel extends GenericModel {   

	private static final long serialVersionUID = -8875892886885069281L;
	
	private CriteriRicercaProvvedimenti criteriProvvedimenti;
	private List<SiacTAttoAmmWrapper> elencoProvvedimenti;

	public List<SiacTAttoAmmWrapper> getElencoProvvedimenti() {
		return elencoProvvedimenti;
	}

	public void setElencoProvvedimenti(List<SiacTAttoAmmWrapper> elencoProvvedimenti) {
		this.elencoProvvedimenti = elencoProvvedimenti;
	}

	public CriteriRicercaProvvedimenti getCriteriProvvedimenti() {
		return criteriProvvedimenti;
	}

	public void setCriteriProvvedimenti(CriteriRicercaProvvedimenti criteriProvvedimenti) {
		this.criteriProvvedimenti = criteriProvvedimenti;
	}
}

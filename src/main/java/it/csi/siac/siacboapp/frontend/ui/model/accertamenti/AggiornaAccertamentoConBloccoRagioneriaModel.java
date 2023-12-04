/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.accertamenti;

import it.csi.siac.siacboapp.frontend.ui.model.base.BaseAggiornaConBloccoRagioneriaModel;
import it.csi.siac.siacboapp.util.entitywrapper.AccertamentoWrapper;

public class AggiornaAccertamentoConBloccoRagioneriaModel extends BaseAggiornaConBloccoRagioneriaModel {

	private static final long serialVersionUID = 1546702850869053596L;
	
	private CriteriRicercaAccertamenti criteri;
	private AccertamentoWrapper accertamento;


	public CriteriRicercaAccertamenti getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaAccertamenti criteri) {
		this.criteri = criteri;
	}

	public AccertamentoWrapper getAccertamento() {
		return accertamento;
	}

	public void setAccertamento(AccertamentoWrapper accertamento) {
		this.accertamento = accertamento;
	}
	
	@Override
	public void reset() {
		super.reset();
		accertamento = null;
		criteri = null;
	}
}

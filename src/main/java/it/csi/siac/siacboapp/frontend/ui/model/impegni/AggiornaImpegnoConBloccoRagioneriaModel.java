/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.impegni;

import it.csi.siac.siacboapp.frontend.ui.model.base.BaseAggiornaConBloccoRagioneriaModel;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;

public class AggiornaImpegnoConBloccoRagioneriaModel extends BaseAggiornaConBloccoRagioneriaModel {
	
	private static final long serialVersionUID = -4897517326959538701L;
	
	
	private CriteriRicercaImpegni criteri;
	private ImpegnoWrapper impegno;


	public CriteriRicercaImpegni getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaImpegni criteri) {
		this.criteri = criteri;
	}

	public ImpegnoWrapper getImpegno() {
		return impegno;
	}

	public void setImpegno(ImpegnoWrapper impegno) {
		this.impegno = impegno;
	}
	
	public void reset() {
		super.reset();
		impegno = null;
		criteri = null;
	}

}

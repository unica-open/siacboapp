/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.base;

import it.csi.siac.siaccommonapp.model.GenericModel;

public abstract class BaseAggiornaConBloccoRagioneriaModel extends GenericModel {


	private static final long serialVersionUID = 9031506640759536335L;

	private String codiceInc;

	public String getCodiceInc() {
		return codiceInc;
	}

	public void setCodiceInc(String codiceInc) {
		this.codiceInc = codiceInc;
	}

	public void reset() {
		codiceInc = null;
	}

}

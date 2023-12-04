/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.decodifica;

import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class DecodificaModel extends GenericModel {
	private static final long serialVersionUID = -1815763584251954166L;

	private SiacDecodifica decodifica;

	public SiacDecodifica getDecodifica() {
		return decodifica;
	}

	public void setDecodifica(SiacDecodifica decodifica) {
		this.decodifica = decodifica;
	}

}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.documenti;

public enum TipoDocumento {
	ENTRATA("E"),
	SPESA("S"),

	;
	
	private String codice;

	TipoDocumento(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}
}

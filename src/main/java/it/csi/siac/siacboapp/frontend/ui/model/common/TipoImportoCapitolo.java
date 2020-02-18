/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

public enum TipoImportoCapitolo {
	COMPETENZA("STA"),
	RESIDUO("STR"),
	CASSA("SCA"),
	MASSIMO_IMPEGNABILE("MI"),

	;
	
	private String codice;

	TipoImportoCapitolo(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}
}

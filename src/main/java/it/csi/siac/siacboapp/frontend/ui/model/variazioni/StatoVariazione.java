/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.variazioni;

public enum StatoVariazione {
	DEFINITIVA("D"),
	ANNULLATA("A"),

	;
	
	private String codice;

	StatoVariazione(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}
}

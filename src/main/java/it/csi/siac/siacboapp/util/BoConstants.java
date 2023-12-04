/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util;

// simulazione rilascio

public enum BoConstants {
	ID_FAMIGLIA_TREE_STRUTTURA_AMMINISTRATIVA_CONTABILE("00005"),
	
	// SIAC-6139
	GESTIONE_UEB("GESTIONE_UEB"),
	;

	private String val;

	BoConstants(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}

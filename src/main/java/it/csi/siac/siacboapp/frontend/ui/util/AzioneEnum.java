/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.util;

public enum AzioneEnum {
	ASSOCIA_PROVVISORI_CASSA("OP-OIL-associaProvisoriCassa");
	
	private String nome;

	AzioneEnum(String nome) {
		this.setNome(nome);	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

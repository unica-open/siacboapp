/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import it.csi.siac.siacboapp.frontend.ui.model.variazioni.StatoVariazione;

public class SiacTVariazioneWrapper extends BaseEntityWrapper {
	
	private static final long serialVersionUID = -2037456800190971195L;

	private Integer numero;
	private String descrizione;
	private EntityCodificaWrapper stato;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public EntityCodificaWrapper getStato() {
		return stato;
	}

	public void setStato(EntityCodificaWrapper stato) {
		this.stato = stato;
	}

	private boolean statoEquals(StatoVariazione statoVariazione) {
		return stato != null && statoVariazione.getCodice().equals(stato.getCodice());
	}

	public boolean isDefinibile() {
		return ! statoEquals(StatoVariazione.ANNULLATA) && ! statoEquals(StatoVariazione.DEFINITIVA);
	}
}

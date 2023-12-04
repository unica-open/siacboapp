/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.variazioni;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTVariazioneWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class DefinisciVariazioneSenzaBonitaModel extends GenericModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3251692949154172380L;

	private CriteriRicercaVariazioni criteri;
	private SiacTVariazioneWrapper variazione;
	private String codiceInc;

	public CriteriRicercaVariazioni getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaVariazioni criteri) {
		this.criteri = criteri;
	}

	public SiacTVariazioneWrapper getVariazione() {
		return variazione;
	}

	public void setVariazione(SiacTVariazioneWrapper variazione) {
		this.variazione = variazione;
	}

	public String getCodiceInc() {
		return codiceInc;
	}

	public void setCodiceInc(String codiceInc) {
		this.codiceInc = codiceInc;
	}

	public void reset() {
		criteri = null;
		variazione = null;
		codiceInc = null;
	}
}

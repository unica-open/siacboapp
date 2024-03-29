/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.handler.session;

import it.csi.siac.siaccommonapp.handler.session.SessionParameter;

public enum BoSessionParameter implements SessionParameter {
	REDIRECT_ACTION_INFO,
	REDIRECT_ACTION_MESSAGES,
	REDIRECT_ACTION_ERRORS,
	ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE,
	ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE_JSON,
	GESTIONE_FILE_RISULTATI_RICERCA_FILE, 
	GESTIONE_FILE_CRITERI_RICERCA_FILE, 
	GESTIONE_ORDINATIVI_CRITERI_RICERCA_ORDINATIVI,
	GESTIONE_LIMITE_IMPEGNABILE_CRITERI_RICERCA_LIMITE_IMPEGNABILE,
	RICERCA_FLUSSI_CRITERI_RICERCA_FLUSSI, 
	GESTIONE_PROPOSTE_PRELIMINARI_CRITERI_SELEZIONE_CAPITOLI,
	GESTIONE_PROPOSTE_PRELIMINARI_ELENCO_CAPITOLI
	;

	@Override
	public boolean isEliminabile() {
		return true;
	}

	@Override
	public String getName() {
		return name();
	}

}

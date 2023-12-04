/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.indicatori;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.utenti.decodifica.GestioneLivelloAction;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ConfNumeroAnniBilancioPrevAction extends GestioneLivelloAction {

	private static final long serialVersionUID = -1232331451107322752L;

	@Override
	protected String getPostOperationActionName() {
		return "confNumeroAnniBilancioPrev";
	}

	@Override
	protected String getLabel()	{
		return "Numero anni";
	}

	@Override
	protected String getCodiceGestioneLivello()	{
		return String.format("%s_%s", TipologiaGestioneLivelli.CONF_NUM_ANNI_BIL_PREV_INDIC.getCodice(), sessionHandler.getAnnoEsercizio());
	}
}

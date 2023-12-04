/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.decodifica;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class MessaggioInformativoAction extends GestioneLivelloAction {
	private static final long serialVersionUID = -8762836185032631276L;


	@Override
	protected String getPostOperationActionName() {
		return "homeUtenti";
	}

	@Override
	protected String getLabel()
	{
		return "Messaggio informativo";
	}

	@Override
	protected String getCodiceGestioneLivello()
	{
		return TipologiaGestioneLivelli.MESSAGGIO_INFORMATIVO.getCodice();
	}
}

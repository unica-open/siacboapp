/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.gestioneordinativi;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.PreparaVBOrdinativiModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class PreparaVBOrdinativiAction extends ConfermaGestioneOrdinativiAction<PreparaVBOrdinativiModel> {
	private static final long serialVersionUID = 8041632318811628438L;

	@Override
	public void executeConferma() throws Exception {
		getOrdinativoService().preparaVB(sessionHandler.getEnte().getUid(), model.getIdOrdinativi(),
				 sessionHandler.getRichiedente().getOperatore().getCodiceFiscale());
	}
}
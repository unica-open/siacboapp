/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.TrasmettiOrdinativiModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class TrasmettiOrdinativiAction extends ConfermaGestioneOrdinativiAction<TrasmettiOrdinativiModel> {
	private static final long serialVersionUID = 3400467158052313587L;

	@Override
	protected void executeConferma() throws Exception {
		getOrdinativoService().trasmettiOrdinativiEGeneraXml(sessionHandler.getEnte(),
				sessionHandler.getBilancio().getAnno(), model.getIdOrdinativi(), model.getCodiceTipoOrdinativi(),
				sessionHandler.getRichiedente());
	}
}

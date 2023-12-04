/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.GenericOrdinativoModel;

public abstract class GenericOrdinativoAction<M extends GenericOrdinativoModel> extends GenericBoAction<M> {

	/** Per la serializzazione */
	private static final long serialVersionUID = 7090483301666461816L;
	
	@Autowired protected OrdinativoService ordinativoService;
	
	protected void storeCriteriIntoSession() {
		sessionHandler.setParametro(BoSessionParameter.GESTIONE_ORDINATIVI_CRITERI_RICERCA_ORDINATIVI,
				model.getCriteri());
	}
	

}

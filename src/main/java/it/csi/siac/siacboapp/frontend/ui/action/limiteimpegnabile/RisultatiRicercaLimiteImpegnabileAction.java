/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.limiteimpegnabile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.limiteimpegnabile.LimiteImpegnabileService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile.CriteriRicercaLimiteImpegnabile;
import it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile.RisultatiRicercaLimiteImpegnabileModel;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RisultatiRicercaLimiteImpegnabileAction extends GenericBoAction<RisultatiRicercaLimiteImpegnabileModel> {
	private static final long serialVersionUID = -7846806707164587048L;

	@Autowired
	protected LimiteImpegnabileService limiteImpegnabileService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		cerca();

		log.debugEnd(methodName, "");
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public void cerca() {
		CriteriRicercaLimiteImpegnabile criteri = sessionHandler.getParametro(
				BoSessionParameter.GESTIONE_LIMITE_IMPEGNABILE_CRITERI_RICERCA_LIMITE_IMPEGNABILE,
				CriteriRicercaLimiteImpegnabile.class);

		int idEnte = sessionHandler.getEnte().getUid();
		
		criteri.setAnno(sessionHandler.getAnnoEsercizio());

		model.setElencoCapitoliLimiteImpegnabile(
				limiteImpegnabileService.ricercaCapitoliLimiteImpegnabile(idEnte, criteri));
	}

}

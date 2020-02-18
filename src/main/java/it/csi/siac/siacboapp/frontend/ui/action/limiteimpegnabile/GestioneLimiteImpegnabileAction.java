/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.limiteimpegnabile;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile.GestioneLimiteImpegnabileModel;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class GestioneLimiteImpegnabileAction extends GenericBoAction<GestioneLimiteImpegnabileModel> {
	private static final long serialVersionUID = 3997446190035360559L;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();
		// SIAC-6139: aggiungo l'ente per permettere la discriminazione sulla visualizzazione della UEB
		model.setEnte(sessionHandler.getEnte());

		model.setElencoStruttureAmministrativoContabiliJson(readElencoStruttureAmministrativoContabiliUtenteJson());

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

	public void validateCerca() {
		if (model.getCriteri() == null || (StringUtils.isBlank(model.getCriteri().getNumeroCapitolo()) && model.getCriteri().getIdStrutturaAmministrativoContabile() == null)) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("'Numero capitolo' oppure 'Struttura Amministrativa Responsabile'"));
		}
	}

	public String cerca() throws Exception {
		sessionHandler.setParametro(BoSessionParameter.GESTIONE_LIMITE_IMPEGNABILE_CRITERI_RICERCA_LIMITE_IMPEGNABILE, model.getCriteri());
		return "cerca";
	}
}

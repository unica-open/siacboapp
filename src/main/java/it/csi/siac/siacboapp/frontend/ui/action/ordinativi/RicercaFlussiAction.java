/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaFlussi;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.RicercaFlussiModel;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RicercaFlussiAction extends GenericBoAction<RicercaFlussiModel> {
	private static final long serialVersionUID = 4465664376490608467L;

	@Autowired
	private OrdinativoService ordinativoService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int idEnte = sessionHandler.getEnte().getUid();

		model.setElencoTipiFlusso(ordinativoService.readElencoTipiOrdinativo(idEnte));

		model.getCriteri().setAnno(sessionHandler.getAnnoEsercizio());

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

	public String cerca() throws Exception {
		sessionHandler.setParametro(BoSessionParameter.RICERCA_FLUSSI_CRITERI_RICERCA_FLUSSI,
				model.getCriteri());

		return "risultatiRicercaFlussi";
	}

	public void validateCerca() {
		CriteriRicercaFlussi criteri = model.getCriteri();

		if (StringUtils.isBlank(criteri.getCodiceTipo()))
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("tipo"));

		if (StringUtils.isNotEmpty(criteri.getAnno()) && !criteri.getAnno().matches("^\\d\\d\\d\\d$"))
			addErrore(ErroreCore.VALORE_NON_CONSENTITO.getErrore("anno", criteri.getAnno()));
	}
}

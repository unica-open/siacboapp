/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.soggetto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.business.service.utenti.AmbitoService;
import it.csi.siac.siacboapp.business.service.utenti.RuoloService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.soggetto.SoggettoModel;
import it.csi.siac.siacboapp.integration.entity.SiacRSoggettoRuolo;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SoggettoAction extends GenericBoAction<SoggettoModel> {
	private static final long serialVersionUID = 5221371874273974078L;

	private static final String CODICE_AMBITO_CRUSCOTTO = "Ambito Cruscotto";

	@Autowired
	private SoggettoService soggettoService;

	@Autowired
	private RuoloService ruoloService;

	@Autowired
	private AmbitoService ambitoService;

	private Integer uid;

	public void prepareExecute() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();
		model.setElencoRuoli(ruoloService.getElencoRuoli(enteId));
		// model.setElencoAmbiti(ambitoService.getElencoAmbiti(enteId));

		if (uid != null)
			model.setSoggetto(soggettoService.read(uid, enteId));

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

	public String update() {
		int enteId = sessionHandler.getEnte().getUid();
		SiacTSoggetto soggetto = model.getSoggetto();

		SiacTSoggetto soggettoOld = soggettoService.read(soggetto.getUid(), enteId);

		soggetto.setAmbito(ambitoService.getAmbito(CODICE_AMBITO_CRUSCOTTO, enteId));

		SiacTEnteProprietario ente = new SiacTEnteProprietario(enteId);

		soggetto.setEnteProprietario(ente);
		soggetto.setLoginOperazione(sessionHandler.getOperatore().getCodiceFiscale());
		soggetto.setLoginCreazione(soggettoOld.getLoginCreazione());

		SiacRSoggettoRuolo sr = soggetto.getRuoli().iterator().next();
		sr.setEnteProprietario(ente);
		sr.setLoginOperazione(soggetto.getLoginOperazione());
		SiacRSoggettoRuolo srOld = soggettoOld.getRuoli().iterator().next();
		sr.setUid(srOld.getUid());
		sr.init(enteId, soggetto.getLoginOperazione());

		soggettoService.update(soggetto);

		return "elencoSoggetti";

	}

	public String create() {
		int enteId = sessionHandler.getEnte().getUid();
		SiacTSoggetto soggetto = model.getSoggetto();

		SiacTEnteProprietario ente = new SiacTEnteProprietario(enteId);

		soggetto.setAmbito(ambitoService.getAmbito(CODICE_AMBITO_CRUSCOTTO, enteId));

		soggetto.setEnteProprietario(ente);
		soggetto.setLoginOperazione(sessionHandler.getOperatore().getCodiceFiscale());
		soggetto.setLoginCreazione(soggetto.getLoginOperazione());
		
		SiacRSoggettoRuolo sr = soggetto.getRuoli().iterator().next();
		sr.init(enteId, soggetto.getLoginOperazione());

		soggettoService.create(soggetto);

		return "elencoSoggetti";
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}

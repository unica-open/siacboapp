/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.azione;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.AzioneService;
import it.csi.siac.siacboapp.business.service.utenti.GruppoAzioniService;
import it.csi.siac.siacboapp.business.service.utenti.RuoloOpService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.azione.AzioneModel;
import it.csi.siac.siacboapp.integration.entity.SiacRRuoloOpAzione;
import it.csi.siac.siacboapp.integration.entity.SiacTAzione;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AzioneAction extends GenericBoAction<AzioneModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6845262774626534404L;

	/**
	 * 
	 */

	@Autowired
	private AzioneService azioneService;

	@Autowired
	private GruppoAzioniService gruppoAzioniService;

	@Autowired
	private RuoloOpService ruoloOpService;

	private Integer uid;

	public void prepareExecute() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null)
			model.setAzione(azioneService.read(uid, enteId));

		model.setElencoTipiAzione(azioneService.getElencoTipiAzione(enteId));
		model.setElencoGruppiAzioni(gruppoAzioniService.getElencoGruppiAzioni(enteId));
		model.setElencoRuoliOp(ruoloOpService.getElencoRuoliOp(enteId));

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
		azioneService.update(initAzione());

		return "elencoAzioni";

	}

	public String create() {
		azioneService.create(initAzione());

		return "elencoAzioni";
	}

	private SiacTAzione initAzione() {
		SiacTAzione azione = model.getAzione();

		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();
		int enteId = sessionHandler.getEnte().getUid();

		if (azione.getRuoliOp() != null)
			for (SiacRRuoloOpAzione roa : azione.getRuoliOp())
				roa.init(enteId, codiceFiscale);

		azione.setEnteProprietario(new SiacTEnteProprietario(enteId));
		azione.setLoginOperazione(codiceFiscale);

		if (azione.getVerificaUo() == null)
			azione.setVerificaUo(Boolean.FALSE);

		return azione;
	}

	public String delete() {
		int enteId = sessionHandler.getEnte().getUid();

		model.setAzione(azioneService.read(uid, enteId));

		model.getAzione().setDataCancellazione(new Date());

		azioneService.update(model.getAzione());

		return "elencoAzioni";
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}

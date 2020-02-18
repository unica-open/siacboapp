/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.ruoloop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.utenti.AzioneService;
import it.csi.siac.siacboapp.business.service.utenti.BilancioService;
import it.csi.siac.siacboapp.business.service.utenti.RuoloOpService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.ruoloop.RuoloOpModel;
import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siacboapp.integration.entity.SiacRRuoloOpAzione;
import it.csi.siac.siacboapp.integration.entity.SiacRRuoloOpBil;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RuoloOpAction extends GenericBoAction<RuoloOpModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7874943551552866292L;

	@Autowired
	private RuoloOpService ruoloOpService;

	@Autowired
	private AzioneService azioneService;

	@Autowired
	private BilancioService bilancioService;

	private Integer uid;

	public void prepareExecute() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null)
			model.setRuoloOp(ruoloOpService.read(uid, enteId));

		model.setElencoAzioni(azioneService.getElencoAzioni(enteId));
		model.setElencoBilanci(bilancioService.getElencoBilanci(enteId));

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
		ruoloOpService.update(initRuoloOp());

		return "elencoRuoliOp";

	}

	private SiacDRuoloOp initRuoloOp() {
		int enteId = sessionHandler.getEnte().getUid();
		String codiceFiscale = sessionHandler.getOperatore().getCodiceFiscale();

		SiacDRuoloOp ruoloOp = model.getRuoloOp();

		if (ruoloOp.getAzioni() != null)
			for (SiacRRuoloOpAzione roa : ruoloOp.getAzioni())
				roa.init(enteId, codiceFiscale);

		if (ruoloOp.getBilanci() != null)
			for (SiacRRuoloOpBil rob : ruoloOp.getBilanci())
				rob.init(enteId, codiceFiscale);

		ruoloOp.setLoginOperazione(sessionHandler.getOperatore().getCodiceFiscale());
		ruoloOp.setEnteProprietario(new SiacTEnteProprietario(enteId));

		return ruoloOp;
	}

	public String create() {
		ruoloOpService.create(initRuoloOp());

		return "elencoRuoliOp";
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}

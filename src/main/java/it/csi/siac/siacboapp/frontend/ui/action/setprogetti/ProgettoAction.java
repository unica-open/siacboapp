/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.setprogetti;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.setprogetti.ProgettoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.setprogetti.ProgettoModel;
import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ProgettoAction extends GenericBoAction<ProgettoModel> {

	private static final long serialVersionUID = 7515015086369350871L;

	@Autowired
	private ProgettoService progettoService;

	private Integer uid;

	private Integer setProgettiId;
	
	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		int enteId = sessionHandler.getEnte().getUid();

		if (uid != null) {
			model.setProgetto(progettoService.read(uid, enteId));
	
			model.setElencoCronoprogrammi(progettoService.getElencoCronoprogrammi(model.getProgetto().getProgramma().getUid(),
					sessionHandler.getBilancio().getUid()));
		}

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
		SiacRFpvSetCronop progettoModel = model.getProgetto();
		
		if (! progettoModel.getUsaGestione() && progettoModel.getCronoprogramma() == null) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("cronoprogramma"));
			
			return INPUT;
		}
		
		Ente ente = sessionHandler.getEnte();
				
		SiacRFpvSetCronop progetto = progettoService.read(progettoModel.getUid(), ente.getUid());
		
		progetto.setCronoprogramma(progettoModel.getCronoprogramma());
		progetto.setUsaGestione(progettoModel.getUsaGestione());
		
		progettoService.update(progetto);

		return "elencoProgetti";  
	}


	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSetProgettiId() {
		return setProgettiId;
	}

	public void setSetProgettiId(Integer setProgettiId) {
		this.setProgettiId = setProgettiId;
	}

	
	public String delete() {
		int enteId = sessionHandler.getEnte().getUid();

		SiacRFpvSetCronop progetto = progettoService.read(getUid(), enteId);

		progetto.setDataCancellazione(new Date());

		progettoService.update(progetto);

		return "elencoProgetti";
	}
	

}

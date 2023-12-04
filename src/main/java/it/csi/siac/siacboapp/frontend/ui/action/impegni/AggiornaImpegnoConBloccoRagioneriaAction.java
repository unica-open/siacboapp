/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.impegni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.impegni.ImpegnoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.BaseAggiornaConBloccoRagioneriaAction;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.AggiornaImpegnoConBloccoRagioneriaModel;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.CriteriRicercaImpegni;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestAttoAmministrativoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestCupMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestFlagPrenotazioneLiquidabileMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestFlagPrenotazioneMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestFlagSdfMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestFlagSoggettoDurcMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestSacMapper;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class AggiornaImpegnoConBloccoRagioneriaAction extends BaseAggiornaConBloccoRagioneriaAction<AggiornaImpegnoConBloccoRagioneriaModel> {


	private static final long serialVersionUID = -7301410317995332464L;
	@Autowired private ImpegnoService impegnoService;

	
	
	@SuppressWarnings("unchecked")
	public String cerca() throws Exception {
		
		CriteriRicercaImpegni criteri = model.getCriteri();
		
		int idEnte = sessionHandler.getEnte().getUid();
		int idBilancio = sessionHandler.getBilancio().getUid();
	
		ImpegnoWrapper impegno = null;
		
		try {
			impegno = impegnoService.cercaImpegno(idEnte, idBilancio, criteri, 
					SiacTMovgestAttoAmministrativoMapper.class,
					SiacTMovgestCupMapper.class,
					SiacTMovgestFlagSoggettoDurcMapper.class,
					SiacTMovgestFlagPrenotazioneMapper.class,
					SiacTMovgestFlagPrenotazioneLiquidabileMapper.class,
					SiacTMovgestFlagSdfMapper.class,
					SiacTMovgestSacMapper.class);
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		model.setImpegno(impegno);
		
		return SUCCESS;
	}

	public String aggiornaParereFinanziario() {
		
		try {
			impegnoService.aggiornaParereFinanziario(model.getImpegno().getUid(),
					model.getImpegno().getParereFinanziario(), defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}

	//SIAC-7884
	public String aggiornaFlagPrenotazione() {
		
		int idEnte = sessionHandler.getEnte().getUid();
		
		try {
			impegnoService.aggiornaFlagPrenotazione(idEnte, model.getImpegno().getUid(),
					model.getImpegno().getFlagPrenotazione(), defaultGetCodiceInc());
			
			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}

	public String aggiornaFlagPrenotazioneLiquidabile() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			impegnoService.aggiornaFlagPrenotazioneLiquidabile(idEnte, model.getImpegno().getUid(),
					model.getImpegno().getFlagPrenotazioneLiquidabile(), defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}
	

	public String aggiornaFlagSoggettoDurc() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			impegnoService.aggiornaFlagSoggettoDurc(idEnte, model.getImpegno().getUid(),
					model.getImpegno().getFlagSoggettoDurc(), defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}
	

	public String aggiornaFlagSdf() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			impegnoService.aggiornaFlagSdf(idEnte, model.getImpegno().getUid(), model.getImpegno().getFlagSdf(),
					defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}
	

	public String aggiornaCup() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			impegnoService.aggiornaCup(idEnte, model.getImpegno().getUid(), model.getImpegno().getCup(),
					defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}


	public String aggiornaTutto() {
		
		int idEnte = sessionHandler.getEnte().getUid();

		try {
			impegnoService.aggiornaImpegnoConBloccoRagioneria(idEnte, model.getImpegno(), defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		return SUCCESS;
	}
	
	@Override
	protected String getDefaultCodiceInc() {
		return "BackofficeAggiornaImpegnoConBloccoRagioneria";
	}


}

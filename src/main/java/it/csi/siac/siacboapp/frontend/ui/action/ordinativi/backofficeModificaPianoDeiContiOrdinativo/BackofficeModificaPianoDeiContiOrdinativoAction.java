/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.ordinativi.backofficeModificaPianoDeiContiOrdinativo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.ordinativi.GenericOrdinativoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.backofficeModificaPianoDeiContiOrdinativo.BackofficeModificaPianoDeiContiOrdinativoModel;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.backofficeModificaPianoDeiContiOrdinativo.ModificaPianoDeiContiOrdinativo;
import it.csi.siac.siaccorser.model.Errore;
import it.csi.siac.siaccorser.model.Messaggio;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * Classe action per il Backoffice delle modifiche sull'ordinativo 
 * per il piando dei conti.
 * 
 * @author Alessandro Todesco
 */
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class BackofficeModificaPianoDeiContiOrdinativoAction extends GenericOrdinativoAction<BackofficeModificaPianoDeiContiOrdinativoModel> {

	/** Per la serializzazione */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";
		log.debugStart(methodName, "Preparazione della action");
		super.prepare();
	}

	@Override
	protected void initModel() {
		super.initModel();
		initCriteri();
		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		storeCriteriIntoSession();
		return SUCCESS;
	}
	
	private void initCriteri() {
		model.setCriteri(new CriteriRicercaOrdinativi());
		model.getCriteri().setAnno(new Integer(sessionHandler.getAnnoEsercizio()));
		model.setIdEnte(sessionHandler.getEnte().getUid());
	}
	
	public String modificaPianoDeiContiOrdinativo() {
		List<Errore> listErrori = new ArrayList<Errore>();
		
		ModificaPianoDeiContiOrdinativo request = model.creaRequestModificaPianoDeiContiOrdinativoBackoffice();
		String response = ordinativoService.modificaPianoDeiContiOrdinativoBackoffice(request);
		
		if(!"0".equals(response)) {
			listErrori.add(ErroreCore.ERRORE_DI_SISTEMA.getErrore(response));
			return INPUT;
		}

		List<Messaggio> list = new ArrayList<Messaggio>();
		list.add(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());
		
		sessionHandler.setParametro(BoSessionParameter.REDIRECT_ACTION_MESSAGES, list);	
		
		return SUCCESS;
	}
	
	public String controlliFormaliModificaPianoDeiContiOrdinativo() {
		
		if(model.getCriteri() == null) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Criteri"));
		}

		if(model.getCriteri().getNumeroDa() == null) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Numero Ordinativo"));
		}
		
		if(StringUtils.isBlank(model.getCriteri().getCodiceTipo())) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Tipo Ordinativo"));
		}
		
		// SIAC-7756
//		if(StringUtils.isBlank(StringEscapeUtils.escapeHtml4(model.getNumeroRemedy()))) {
//			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Login operazione"));
//		}
		
		if(model.getIdTitoloCapitolo() == null || model.getIdTitoloCapitolo() == 0) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Titolo"));
		}
			
		if("I".equals(model.getCriteri().getCodiceTipo()) && (model.getIdTipologiaCapitolo() == null || model.getIdTipologiaCapitolo() == 0)) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Tipologia"));
		}
		
		if(model.getIdMacroAggregato() == null || model.getIdMacroAggregato() == 0) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Macroaggregato o Categoria"));
		}
		
		if(model.getPianoDeiConti() != null 
				&& model.getPianoDeiConti().getTipoClassificatore() != null 
				&& model.getPianoDeiConti().getTipoClassificatore().getCodice() != null
				&& (!"PDC_V".equals(model.getPianoDeiConti().getTipoClassificatore().getCodice()) 
						|| StringUtils.isBlank(model.getPianoDeiConti().getTipoClassificatore().getCodice())) ) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Piano dei conti V livello"));
		}
		
		if((Boolean.TRUE.equals(model.getInserisciGenerale()) || Boolean.TRUE.equals(model.getInserisciGSAGenerale()))
				&& (StringUtils.isBlank(model.getTipoEventoCodice()) || "0".equals(model.getTipoEventoCodice()))) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Tipo evento"));
		}

		if((Boolean.TRUE.equals(model.getInserisciGSAGenerale()) || Boolean.TRUE.equals(model.getInserisciGenerale())) 
				&& (StringUtils.isBlank(model.getEventoCodice()) || "0".equals(model.getEventoCodice()))) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Evento"));
		}
		
		if(model.getIdEnte() == null || model.getIdEnte() == 0) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(" Impossibile raggiungere il server "));
		}

		return SUCCESS; 
	}
	
}

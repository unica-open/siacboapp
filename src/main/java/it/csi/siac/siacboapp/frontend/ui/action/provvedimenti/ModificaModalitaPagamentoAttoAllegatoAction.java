/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.provvedimenti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.ProvvedimentoService;
import it.csi.siac.siacboapp.business.service.common.SoggettoService;
import it.csi.siac.siacboapp.business.service.documenti.DocumentoService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.messaggio.MessaggioBo;
import it.csi.siac.siacboapp.frontend.ui.model.provvedimenti.ModificaModalitaPagamentoAttoAllegatoModel;
import it.csi.siac.siacboapp.frontend.ui.util.comparator.SiacTClassWrapperComparator;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.frontend.webservice.ClassificatoreService;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabile;
import it.csi.siac.siaccorser.frontend.webservice.msg.LeggiStrutturaAmminstrativoContabileResponse;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ModificaModalitaPagamentoAttoAllegatoAction extends GenericBoAction<ModificaModalitaPagamentoAttoAllegatoModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1993388855339574474L;

	
	@Autowired
	private ProvvedimentoService provvedimentoService;
	
	@Autowired
	private DocumentoService documentoService;

	@Autowired 
	private SoggettoService soggettoService;

	@Autowired
	protected ClassificatoreService classificatoreService;

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();
		
		int idEnte = sessionHandler.getEnte().getUid();
		
		model.setElencoClassiSoggetto(soggettoService.readElencoClassiSoggetto(idEnte));
		model.setElencoTipiAttoAmministrativo(provvedimentoService.readElencoTipiAttoAmministrativo(idEnte));

		log.debugEnd(methodName, "");
	}
	
	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		//SIAC-8485
		impostaElenctoStruttureTree();
		return SUCCESS;
	}

	//SIAC-8485
	@Override
	public boolean hasZTreeMultipli() {
		return Boolean.FALSE;
	}
	
	public String cercaStruttureAmministrativoContabili() {
		model.setElencoStruttureAmministrativoContabili(
			caricaStruttureContabili()
		);
		return SUCCESS;
	}
	
	//SIAC-8485
	@SuppressWarnings("unchecked")
	private List<SiacTClassWrapper> caricaStruttureContabili() {
		// Controllo se ho gia' le strutture
		if(sessionHandler.containsKey(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE)) {
			return (List<SiacTClassWrapper>) sessionHandler.getParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE, SiacTClassWrapper.class);
			
		}
		
		// cerco le strutture
		List<SiacTClassWrapper> list = readElencoStruttureAmministrativoContabili();
		if(list != null && !list.isEmpty()) {
			Collections.sort(list, SiacTClassWrapperComparator.INSTANCE);
			sessionHandler.setParametro(BoSessionParameter.ELENCO_STRUTTURE_AMMINISTRATIVE_CONTABILI_UTENTE, list);
		}
		return list;
	}

	public String cerca() throws Exception {
		int idEnte = sessionHandler.getEnte().getUid();
	
		SiacTAttoAmmWrapper attoAmministrativo = model.getAttoAmministrativo();
		SiacTSoggettoWrapper soggetto = model.getSoggetto();

		try {
			model.setQuote(documentoService.cercaQuoteAttoAmministrativoSoggetto(
				 idEnte, 
				 attoAmministrativo.getUid(), 
				 attoAmministrativo.getSac().getUid(),
				 soggetto.getCodice())
			);
			
			if (model.getQuote().isEmpty()) {
				addErrore(ErroreCore.NESSUN_DATO_REPERITO.getErrore());
			} else {
				
				model.setElencoModalitaPagamentoSoggetto(soggettoService.readElencoModalitaPagamentoSoggetto(idEnte, soggetto.getCodice()));	
			}
			
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		//SIAC-8485
		impostaElenctoStruttureTree();
				
		return SUCCESS;
	}

	public String modificaModalitaPagamento() {
		
		int idEnte = sessionHandler.getEnte().getUid();
		int idBilancio = sessionHandler.getBilancio().getUid();

		SiacTAttoAmmWrapper attoAmministrativo = model.getAttoAmministrativo();
		SiacTSoggettoWrapper soggetto = model.getSoggetto();

		try {
			documentoService.modificaModalitaPagamentoAttoAmministrativo(
					idEnte, 
					idBilancio, 
					attoAmministrativo.getUid(), 
					soggetto.getCodice(),
					model.getIdModalitaPagamentoSoggetto(),
					defaultGetCodiceInc());

			addMessaggio(MessaggioBo.OPERAZIONE_EFFETTUATA_CORRETTAMENTE.getMessaggio());

			model.reset();
		} catch (BusinessException be) {
			addErrore(be.getErrore());
		} catch (Throwable t) {
			addErrore(ErroreCore.ERRORE_DI_SISTEMA.getErrore(t.getMessage()));
		}
		
		//SIAC-8485
		impostaElenctoStruttureTree();
		
		return SUCCESS;
	}
	

	private String defaultGetCodiceInc() {
		return String.format("%s-%s", 
				sessionHandler.getRichiedente().getOperatore().getCodiceFiscale(), 
				StringUtils.isBlank(model.getCodiceInc()) ? "BackofficeModificaModalitaPagamentoAttoAllegato" : model.getCodiceInc());
	}

	private List<SiacTClassWrapper> readElencoStruttureAmministrativoContabili() {

		LeggiStrutturaAmminstrativoContabile leggiStrutturaAmminstrativoContabile = new LeggiStrutturaAmminstrativoContabile();

		leggiStrutturaAmminstrativoContabile.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
		leggiStrutturaAmminstrativoContabile.setIdEnteProprietario(sessionHandler.getEnte().getUid());
		leggiStrutturaAmminstrativoContabile.setRichiedente(sessionHandler.getRichiedente());
		LeggiStrutturaAmminstrativoContabileResponse response = classificatoreService.leggiStrutturaAmminstrativoContabile(leggiStrutturaAmminstrativoContabile);

		return getAllStrutture(response.getListaStrutturaAmmContabile());
	}

	private List<SiacTClassWrapper> getAllStrutture(List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili) {
		
		List<SiacTClassWrapper> list = new ArrayList<SiacTClassWrapper>();

		for (StrutturaAmministrativoContabile sac : elencoStruttureAmministrativoContabili){
			SiacTClassWrapper siacTClassWrapper = new SiacTClassWrapper();
			siacTClassWrapper.setUid(sac.getUid());
			siacTClassWrapper.setCodice(sac.getCodice());
			siacTClassWrapper.setDescrizione(sac.getDescrizione());
			//SIAC-8485
			siacTClassWrapper.setDescrizioneCompleta(popolaDescrizioneCompleta(sac));
			if(sac.getSubStrutture() != null && !sac.getSubStrutture().isEmpty()) {
				siacTClassWrapper.setChildren(getAllStruttureChildrenWithParent(sac.getSubStrutture(), siacTClassWrapper));
			}
			list.add(siacTClassWrapper);
		}

		return list;
	}

	//SIAC-8485
	private List<SiacTClassWrapper> getAllStruttureChildrenWithParent(List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili, SiacTClassWrapper padre) {
		
		List<SiacTClassWrapper> list = new ArrayList<SiacTClassWrapper>();
		
		for (StrutturaAmministrativoContabile sac : elencoStruttureAmministrativoContabili){
			SiacTClassWrapper siacTClassWrapper = new SiacTClassWrapper();
			siacTClassWrapper.setUid(sac.getUid());
			siacTClassWrapper.setCodice(sac.getCodice());
			siacTClassWrapper.setDescrizione(sac.getDescrizione());
			siacTClassWrapper.setDescrizioneCompleta(popolaDescrizioneCompleta(sac));
			siacTClassWrapper.setParent(padre);
			list.add(siacTClassWrapper);
		}
		
		return list;
	}
	
	private String popolaDescrizioneCompleta(StrutturaAmministrativoContabile struttura) {
		return struttura.getCodice() + " - " + struttura.getDescrizione();
	}
	
	//SIAC-8485
	private void impostaElenctoStruttureTree() {
		model.setElencoStruttureAmministrativoContabiliNoTree(
			allineaAlberaturaStrutture(caricaStruttureContabili())
		);
	}
	
	//SIAC-8485
	private List<SiacTClassWrapper> allineaAlberaturaStrutture(List<SiacTClassWrapper> strutture){
		List<SiacTClassWrapper> allineati = new ArrayList<SiacTClassWrapper>();
		
		if(strutture != null) {
			for(SiacTClassWrapper strutturaPadre : strutture) {
				allineati.add(strutturaPadre);
				if(strutturaPadre.getChildren() != null) {
					allineati.addAll(strutturaPadre.getChildren());					
				}
			}
		}
		
		return allineati;
	}
	
}

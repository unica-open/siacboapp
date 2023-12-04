/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.common.CapitoloService;
import it.csi.siac.siacboapp.business.service.common.CodificheService;
import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoService;
import it.csi.siac.siacboapp.business.service.utenti.ClassificatoreService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.common.RicercaOrdinativoModel;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.util.entitywrapper.ElementoPianoDeiContiWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacDEventoWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTBilElemWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

/**
 * @author Alessandro Todesco
 */
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RicercaOrdinativoAction extends GenericBoAction<RicercaOrdinativoModel> {

	private static final long serialVersionUID = 3509628656906689611L;
	
	protected static final String PDC_V = "PDC_V";
	protected static final String PDC_IV = "PDC_IV";
	protected static final String PDC_III = "PDC_III";
	protected static final String PDC_II = "PDC_II";
	protected static final String PDC_I = "PDC_I";

	@Autowired 
	private OrdinativoService ordinativoService;
	
	@Autowired 
	private CapitoloService capitoloService;

	@Autowired 
	private ClassificatoreService classificatoreService;

	@Autowired 
	private CodificheService codificheService;
	
	private boolean isValid() {
		checkNotNull(model.getCriteri().getAnno(), "anno");
		checkNotNull(model.getCriteri().getNumeroDa(), "numero ordinativo");
		checkCondition(StringUtils.isNotBlank(model.getCriteri().getCodiceTipo()), ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("tipo ordinativo"));
		return !hasActionErrors(); 
	}
	
	@Override
	public String execute() throws Exception {
		
		if(!isValid()) {
			return SUCCESS;
		}

		int idEnte = sessionHandler.getEnte().getUid();
		
		adeguaModelPercRicerca();
		
		// ORDINATIVO
		@SuppressWarnings("unchecked")
		List<SiacTOrdinativoWrapper> listaOridinativi = ordinativoService.ricercaOrdinativo(idEnte, model.getCriteri());
		
		if(checkResultList(listaOridinativi , "ordinativo")) return SUCCESS;

		// CAPITOLO
		SiacTBilElemWrapper capitoloAssociato = capitoloService.readCapitoloByOrdinativoUid(listaOridinativi.get(0).getUid(), idEnte).get(0);

		if(checkResult(capitoloAssociato,"capitolo")) return SUCCESS;
		
		model.setElencoOrdinativi(listaOridinativi);
		model.setCapitoloAssociato(capitoloAssociato);

		// PDC V ORDINATIVO
		List<SiacTClassWrapper> pdcOrd = classificatoreService.findPdcQuintoLivelloDaOrdinativo(listaOridinativi.get(0).getUid(), idEnte);

		if(checkResult(pdcOrd,"piano dei conti associato ad ordinativo")) return SUCCESS;
		
		// mappo i classificatori
		Map<String, SiacTClassWrapper> mappaClassif = mappaClassificatoriPerTipo(pdcOrd);

		// CATEGORIA/MACROAGGREGATO
		List<SiacTClassWrapper> catMac = creaListaClassifByLivello(mappaClassif, 
				"I".equalsIgnoreCase(listaOridinativi.get(0).getTipo().getCodice()) ? PDC_III : PDC_II);
		
		if(checkResultList(catMac, "categoria/macroaggregato")) return SUCCESS;
		
		model.setListaCategorieMacroAggregati(catMac);
		
		List<SiacTClassWrapper> tipologia = null;
		// TIPOLOGIA
		if("I".equalsIgnoreCase(listaOridinativi.get(0).getTipo().getCodice())) {
			tipologia = creaListaClassifByLivello(mappaClassif, PDC_II);	
			
			if(checkResultList(tipologia, "tipologia")) return SUCCESS;
			
			model.setTipologie(tipologia);
		}
		
		// TITOLO
		List<SiacTClassWrapper> titoli = classificatoreService.readTitoliEntrataSpesaByEnteProprietario(PDC_I,
				"I".equalsIgnoreCase(listaOridinativi.get(0).getTipo().getCodice()) ? "E" : "U",
				idEnte);
		
		if(checkResultList(titoli, "titolo")) return SUCCESS;
		
		model.setTitoli(titoli);

		// PIANO DEI CONTI
		// faccio una conversione per far digerire i dati allo zTree
		List<ElementoPianoDeiContiWrapper> listaPdc = estraiClassificatoriPerPianoDeiConti(pdcOrd, model.getCriteri().getCodiceTipo());
		
		model.setClassificatoriWrapper(listaPdc);
		model.setClassificatori(pdcOrd);

		return SUCCESS;
	}

	/**
	 * Ricerca dei pdc per lo ztree
	 * 
	 * @param uidPadre
	 * @param idEnte
	 * @return String
	 */
	public String cercaPianoDeiContiJson() {
		
		// dovrebbe essere sempre negativo
		if(controlliRicercaPianoDeiConti()) {
			return SUCCESS;
		}
		
		// cerco i classificatori associati al macroaggregato o alla categoria
		List<SiacTClassWrapper> classificatoriCapitolo = classificatoreService.findChildrenByParentUid(model.getUidPadre(), sessionHandler.getEnte().getUid());
		
		if(checkResultList(classificatoriCapitolo, model.getTipoClassificatore())) return SUCCESS;
		
		model.setClassificatoriWrapper(estraiFratelliPadreDaFiglio(classificatoriCapitolo));
		
		return SUCCESS;
	}

	/**
	 * Metodo di ricerca per i seguenti tipi classificatori:
	 * 
	 * - Tipologie
	 * - Categorie
	 * - Macroaggregati
	 * 
	 * @param capitoloAssociato
	 * @param idEnte
	 * @return String
	 */
	public String ricercaClassificatoriByParentJson() {
		
		// dovrebbe essere sempre negativo
		if(controlliRicercaPianoDeiConti()) {
			return SUCCESS;
		}
		
		// cerco i classificatori associati all'uidParent
		List<SiacTClassWrapper> classificatoriByParentUid = classificatoreService.findChildrenByParentUid(model.getUidPadre(), sessionHandler.getEnte().getUid());
		
		if(checkResultList(classificatoriByParentUid, model.getTipoClassificatore())) return SUCCESS;
		
		model.setClassificatori(classificatoriByParentUid);
		
		return SUCCESS;
	}
	
	/**
	 * faccio una conversione per far digerire i dati allo zTree
	 * 
	 * @param classificatoriCapitolo
	 * @param codiceTipo 
	 * @return List<ElementoPianoDeiContiWrapper>
	 */
	private List<ElementoPianoDeiContiWrapper> estraiClassificatoriPerPianoDeiConti(List<SiacTClassWrapper> classificatoriCapitolo, String codiceTipo) {
		List<ElementoPianoDeiContiWrapper> listPdc = new ArrayList<ElementoPianoDeiContiWrapper>();
		
		// PDC_III
		if("P".equalsIgnoreCase(codiceTipo) && classificatoriCapitolo != null && !classificatoriCapitolo.isEmpty() 
				&& classificatoriCapitolo.get(0) != null && classificatoriCapitolo.get(0).getParent() != null 
				&& classificatoriCapitolo.get(0).getParent().getParent() != null
				&& classificatoriCapitolo.get(0).getParent().getParent().getParent() != null
				&& classificatoriCapitolo.get(0).getParent().getParent().getParent().getChildren() != null
				&& !classificatoriCapitolo.get(0).getParent().getParent().getParent().getChildren().isEmpty()) {		
			
			for (SiacTClassWrapper siacTclass : classificatoriCapitolo.get(0).getParent().getParent().getParent().getChildren()) {
				ElementoPianoDeiContiWrapper elPdc = new ElementoPianoDeiContiWrapper(siacTclass);
				listPdc.add(elPdc);
			}
			
		}
		
		// PDC_IV
		if("I".equalsIgnoreCase(codiceTipo)) {
			if(classificatoriCapitolo != null && !classificatoriCapitolo.isEmpty() && classificatoriCapitolo.get(0) != null
					&& classificatoriCapitolo.get(0).getParent() != null 
					&& classificatoriCapitolo.get(0).getParent().getParent() != null
					&& classificatoriCapitolo.get(0).getParent().getParent().getChildren() != null
					&& !classificatoriCapitolo.get(0).getParent().getParent().getChildren().isEmpty()) {	
				
				for (SiacTClassWrapper siacTclass : classificatoriCapitolo.get(0).getParent().getParent().getChildren()) {
					ElementoPianoDeiContiWrapper elPdc = new ElementoPianoDeiContiWrapper(siacTclass);
					listPdc.add(elPdc);
				}
				
			}
		}

		return listPdc;
	}

	/**
	 * Mappo i vari livelli dei classificatori dell'ordinativo in un unica struttura ordinata
	 * 
	 * @param list
	 * @return Map<String, SiacTClassWrapper>
	 */
	private Map<String, SiacTClassWrapper> mappaClassificatoriPerTipo(List<SiacTClassWrapper> list){
		
		Map<String, SiacTClassWrapper> map = new TreeMap<String, SiacTClassWrapper>();
		
		if(CollectionUtils.isNotEmpty(list)) {
			
			// PDC_V
			map.put(list.get(0).getTipo().getCodice(), list.get(0));
			// PDC_IV
			map.put(list.get(0).getParent().getTipo().getCodice(), 
					list.get(0).getParent());
			// PDC_III
			map.put(list.get(0).getParent().getParent().getTipo().getCodice(),
					list.get(0).getParent().getParent());
			// PDC_II
			map.put(list.get(0).getParent().getParent().getParent().getTipo().getCodice(),
					list.get(0).getParent().getParent().getParent());
			// PDC_I
			map.put(list.get(0).getParent().getParent().getParent().getParent().getTipo().getCodice(),
					list.get(0).getParent().getParent().getParent().getParent());
		
		}
		
		return map;
	}
	
	private List<SiacTClassWrapper> creaListaClassifByLivello(Map<String, SiacTClassWrapper> mappa, String livelloCode){
		List<SiacTClassWrapper> lista = new ArrayList<SiacTClassWrapper>();
		if(!StringUtils.isBlank(livelloCode) && mappa != null 
				&& mappa.containsKey(livelloCode)) {
			lista.add(mappa.get(livelloCode));
		}
		return lista;
	}

	/**
	 * @param classificatoriCapitolo
	 * @return List<ElementoPianoDeiContiWrapper>
	 */
	private List<ElementoPianoDeiContiWrapper> estraiFratelliPadreDaFiglio(List<SiacTClassWrapper> classificatoriCapitolo) {
		List<ElementoPianoDeiContiWrapper> listPdc = new ArrayList<ElementoPianoDeiContiWrapper>();
		
		if(classificatoriCapitolo != null && !classificatoriCapitolo.isEmpty() && classificatoriCapitolo.get(0) != null
				&& classificatoriCapitolo.get(0).getParent() != null && classificatoriCapitolo.get(0).getParent().getChildren() != null
				&& !classificatoriCapitolo.get(0).getParent().getChildren().isEmpty()) {
			
			for (SiacTClassWrapper siacTclass : classificatoriCapitolo.get(0).getParent().getChildren()) {
				ElementoPianoDeiContiWrapper elPdc = new ElementoPianoDeiContiWrapper(siacTclass);
				listPdc.add(elPdc);
			}
			
		}
		
		return listPdc;
	}

	/**
	 * Ricerco l'evento per la gestione dell'inserimento 
	 * nella contbailita generale e GSA
	 * 
	 * @return String
	 */
	public String ricercaEventoModificaPianoDeiConti() {
		
		if(StringUtils.isBlank(model.getCriteri().getCodiceTipo())) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("Tipo ordinativo"));
			return SUCCESS;
		}

		List<SiacDEventoWrapper> eventi = codificheService.ricercaEventoModificaPianoDeiConti(model.getCriteri().getCodiceTipo(), sessionHandler.getEnte().getUid());

		if(CollectionUtils.isEmpty(eventi)) {
			addErrore(ErroreCore.NESSUN_DATO_REPERITO.getErrore("Evento"));
		}
		
		model.setEventi(eventi);
		
		return SUCCESS;
	}
	
	/**
	 * Controllo che i dati per la ricerca del pdc
	 */
	private boolean controlliRicercaPianoDeiConti() {
		
		if(model.getCapitoloAssociato() == null || model.getCapitoloAssociato().getUid() == null || model.getCapitoloAssociato().getUid() == 0) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("capitolo"));
		}

		if(sessionHandler.getEnte() == null || sessionHandler.getEnte().getUid() == 0) {
			addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("ente"));
		}
		
		if(model.getErrori() != null && !model.getErrori().isEmpty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * controlo ed aggiusto i parametri prima della chiamata al servizio
	 */
	private void adeguaModelPercRicerca() {
		// adeguo il numeroA al gemello in modo da ottenere una ricerca puntuale
		if(model.getCriteri().getNumeroA() == null) {
			model.getCriteri().setNumeroA(model.getCriteri().getNumeroDa());
		}
		
		if(model.getCriteri().getDataEmissioneDa() == null & model.getCriteri().getDataEmissioneA() == null) {
			model.getCriteri().setDataEmissioneDa(CriteriRicercaOrdinativi.MIN_DATE);
			model.getCriteri().setDataEmissioneA(CriteriRicercaOrdinativi.MAX_DATE);
		}
		
		if(model.getCriteri().getDataTrasmissioneOilDa() == null & model.getCriteri().getDataTrasmissioneOilA() == null) {
			model.getCriteri().setDataTrasmissioneOilDa(CriteriRicercaOrdinativi.MIN_DATE);
			model.getCriteri().setDataTrasmissioneOilA(CriteriRicercaOrdinativi.MAX_DATE);
		}
	}
	
}
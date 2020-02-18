/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.propostepreliminari;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import it.csi.siac.siacbilser.frontend.webservice.ClassificatoreBilService;
import it.csi.siac.siacbilser.frontend.webservice.msg.LeggiClassificatoriBilByIdPadre;
import it.csi.siac.siacbilser.frontend.webservice.msg.LeggiClassificatoriBilByIdPadreResponse;
import it.csi.siac.siacbilser.frontend.webservice.msg.LeggiClassificatoriByTipoElementoBil;
import it.csi.siac.siacbilser.frontend.webservice.msg.LeggiClassificatoriByTipoElementoBilResponse;
import it.csi.siac.siacbilser.frontend.webservice.msg.LeggiClassificatoriGenericiByTipoElementoBil;
import it.csi.siac.siacbilser.frontend.webservice.msg.LeggiClassificatoriGenericiByTipoElementoBilResponse;
import it.csi.siac.siacbilser.model.Missione;
import it.csi.siac.siacbilser.model.Programma;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.CriteriSelezioneCapitoli;
import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.GestionePropostePreliminariModel;
import it.csi.siac.siaccorser.frontend.webservice.ClassificatoreService;
import it.csi.siac.siaccorser.model.ClassificatoreGenerico;
import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;

public abstract class GestionePropostePreliminariAction extends BasePropostePreliminariAction<GestionePropostePreliminariModel> {
	private static final long serialVersionUID = -2485716052394256761L;

	@Autowired
	private ClassificatoreBilService classificatoreBilService;

	@Autowired
	@Qualifier("classificatoreCorService")
	private ClassificatoreService classificatoreService;

	@PostConstruct
	private void init() {
		tipoProposta = StringUtils.substringBetween(this.getClass().getName(), "GestionePropostePreliminari", "Action")
				.toLowerCase();
	}

	@Override
	public void prepare() throws Exception {
		final String methodName = "prepare";

		log.debugStart(methodName, "Preparazione della action");

		super.prepare();

		model.setElencoMissioni(readElencoMissioni()); 

		model.setElencoStruttureAmministrativoContabiliJson(readElencoStruttureAmministrativoContabiliUtenteJson());
		
		model.setElencoClassificatoriGenerici(readElencoClassificatoriGenerici());

		log.debugEnd(methodName, "");
	}

	private List<Programma> readElencoProgrammi() {
		LeggiClassificatoriBilByIdPadre req = new LeggiClassificatoriBilByIdPadre();

		req.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
		req.setIdEnteProprietario(sessionHandler.getEnte().getUid());
		req.setIdPadre(model.getCriteriSelezioneCapitoli().getIdMissione());
		req.setRichiedente(sessionHandler.getRichiedente());

		LeggiClassificatoriBilByIdPadreResponse response = classificatoreBilService
				.leggiClassificatoriByIdPadre(req);

		return response.getClassificatoriProgramma();
	}

	private List<Missione> readElencoMissioni() {
		LeggiClassificatoriByTipoElementoBil req = new LeggiClassificatoriByTipoElementoBil();

		req.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
		req.setIdEnteProprietario(sessionHandler.getEnte().getUid());
		req.setTipoElementoBilancio("CAP-UP");
		req.setRichiedente(sessionHandler.getRichiedente());

		LeggiClassificatoriByTipoElementoBilResponse response = classificatoreBilService
				.leggiClassificatoriByTipoElementoBil(req);

		return response.getClassificatoriMissione();
	}

	

	private List<ClassificatoreGenerico> readElencoClassificatoriGenerici() {
		LeggiClassificatoriGenericiByTipoElementoBil req = new LeggiClassificatoriGenericiByTipoElementoBil();

		req.setAnno(Integer.parseInt(sessionHandler.getAnnoEsercizio()));
		req.setIdEnteProprietario(sessionHandler.getEnte().getUid());
		req.setTipoElementoBilancio("CAP-UP");
		req.setRichiedente(sessionHandler.getRichiedente());

		LeggiClassificatoriGenericiByTipoElementoBilResponse response = classificatoreBilService
				.leggiClassificatoriGenericiByTipoElementoBil(req);
		
		
		return response.getClassificatori();
	}	
	
	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {
		List<Programma> elencoProgrammi;

		if (model.getCriteriSelezioneCapitoli().getIdMissione() != null)
			elencoProgrammi = readElencoProgrammi();
		else
			elencoProgrammi = new ArrayList<Programma>();

		model.setElencoProgrammi(elencoProgrammi);

		return SUCCESS;
	}

	public String prosegui() throws Exception {
		CriteriSelezioneCapitoli criteri = model.getCriteriSelezioneCapitoli();

		if (criteri.getIdMissione() == null)
			criteri.setIdProgramma(-1);
		else if (criteri.getIdProgramma() == null)
			criteri.setIdProgrammi(extractIdProgrammi(readElencoProgrammi()));

		if (criteri.getIdStrutturaAmministrativoContabile() == null)
			criteri.setIdStruttureAmministrativoContabili(
					extractIdStruttureAmministrativoContabili(readElencoStruttureAmministrativoContabiliUtente()));

		sessionHandler.setParametro(BoSessionParameter.GESTIONE_PROPOSTE_PRELIMINARI_CRITERI_SELEZIONE_CAPITOLI,
				criteri);

		return "elencoPropostePreliminari" + capitalize(tipoProposta);
	}

	private List<Integer> extractIdProgrammi(List<Programma> elencoProgrammi) {
		List<Integer> list = new ArrayList<Integer>();

		for (Programma programma : elencoProgrammi)
			list.add(programma.getUid());

		return list;
	}

	private List<Integer> extractIdStruttureAmministrativoContabili(
			List<StrutturaAmministrativoContabile> elencoStruttureAmministrativoContabili) {
		List<Integer> list = new ArrayList<Integer>();

		for (StrutturaAmministrativoContabile strutturaAmministrativoContabile : elencoStruttureAmministrativoContabili) {
			list.add(strutturaAmministrativoContabile.getUid());

			if (!strutturaAmministrativoContabile.getSubStrutture().isEmpty())
				list.addAll(
						extractIdStruttureAmministrativoContabili(strutturaAmministrativoContabile.getSubStrutture()));
		}

		return list;
	}

	public void setMid(Integer mid) {
		model.getCriteriSelezioneCapitoli().setIdMissione(mid);
	}

}

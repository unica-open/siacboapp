/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.propostepreliminari;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.propostepreliminari.PropostaPreliminareService;
import it.csi.siac.siacboapp.frontend.ui.handler.session.BoSessionParameter;
import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.CriteriSelezioneCapitoli;
import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.ElencoPropostePreliminariModel;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;

public abstract class ElencoPropostePreliminariAction extends BasePropostePreliminariAction<ElencoPropostePreliminariModel> {
	private static final long serialVersionUID = -2268648363077576283L;

	@Autowired
	private PropostaPreliminareService propostaPreliminareService;

	private Integer puid;
	
	@PostConstruct
	private void init() {
		tipoProposta = StringUtils.substringBetween(this.getClass().getName(), "ElencoPropostePreliminari", "Action")
				.toLowerCase();
	}

	@Override
	public void prepare() throws Exception {
		super.prepare();

		SiacTEnteProprietario ente = new SiacTEnteProprietario(sessionHandler.getEnte().getUid());

		CriteriSelezioneCapitoli criteri = sessionHandler.getParametro(
				BoSessionParameter.GESTIONE_PROPOSTE_PRELIMINARI_CRITERI_SELEZIONE_CAPITOLI, CriteriSelezioneCapitoli.class);
		
		List<SiacTBilElem> elencoCapitoli = propostaPreliminareService.readElencoCapitoliPerPropostePreliminari(ente, criteri);
		
		sessionHandler.setParametro(BoSessionParameter.GESTIONE_PROPOSTE_PRELIMINARI_ELENCO_CAPITOLI, elencoCapitoli);

		model.setElencoCapitoli(propostaPreliminareService.readElencoCapitoliPerPropostePreliminariNonSelezionati(elencoCapitoli));
		
		model.setElencoProposte(propostaPreliminareService.readElencoPropostePreliminari(ente, elencoCapitoli));
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

	public String inviaProposta() throws Exception {
		propostaPreliminareService.inviaPropostaPreliminare(puid,
				new SiacTEnteProprietario(sessionHandler.getEnte().getUid()),
				sessionHandler.getOperatore().getCodiceFiscale());

		addSuccessMessage();
		
		return "elencoPropostePreliminari" + capitalize(tipoProposta);
	}

	public String approvaProposta() throws Exception {
		propostaPreliminareService.approvaPropostaPreliminare(puid,
				new SiacTEnteProprietario(sessionHandler.getEnte().getUid()),
				sessionHandler.getOperatore().getCodiceFiscale());
	
		addSuccessMessage();

		return "elencoPropostePreliminari" + capitalize(tipoProposta);
	}

	public String eliminaProposta() {
		SiacTPropostaPreliminare proposta = propostaPreliminareService.readPropostaPreliminare(puid);

		proposta.setDataCancellazione(new Date());

		propostaPreliminareService.updatePropostaPreliminare(proposta);
		
		addSuccessMessage();
		
		return "elencoPropostePreliminari" + capitalize(tipoProposta);
	}

	public void setPuid(Integer puid) {
		this.puid = puid;
	}

	
}

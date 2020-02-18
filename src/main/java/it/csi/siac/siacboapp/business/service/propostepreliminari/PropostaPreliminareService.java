/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.propostepreliminari;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.CriteriSelezioneCapitoli;
import it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari.StatoPropostaPreliminare;
import it.csi.siac.siacboapp.integration.dao.propostepreliminari.SiacTPropostaPreliminareDao;
import it.csi.siac.siacboapp.integration.entity.SiacDPropostaPreliminareStato;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;
import it.csi.siac.siacboapp.integration.repository.SiacDPropostaPreliminareStatoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTPropostaPreliminareRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class PropostaPreliminareService {
	@Autowired
	private SiacTPropostaPreliminareRepository siacTPropostaPreliminareRepository;

	@Autowired
	private SiacDPropostaPreliminareStatoRepository siacDPropostaPreliminareStatoRepository;

	@Autowired
	private SiacTPropostaPreliminareDao siacTPropostaPreliminareDao;

	public List<SiacTPropostaPreliminare> readElencoPropostePreliminari(SiacTEnteProprietario ente,
			List<SiacTBilElem> elencoCapitoli) {
		List<SiacTBilElem> ec = new ArrayList<SiacTBilElem>(elencoCapitoli);
		SiacTBilElem be = new SiacTBilElem();
		be.setUid(-1);
		ec.add(be);

		return siacTPropostaPreliminareRepository.readElencoPropostePreliminari(ente, ec);
	}

	public List<SiacTBilElem> readElencoCapitoliPerPropostePreliminari(SiacTEnteProprietario ente,
			CriteriSelezioneCapitoli criteri) {
		return siacTPropostaPreliminareDao.readElencoCapitoliPerPropostePreliminari(ente,
				criteri.getIdStruttureAmministrativoContabili(), criteri.getIdProgrammi(),
				criteri.getIdClassificatoriGenerici());
	}

	public List<SiacTBilElem> readElencoCapitoliPerPropostePreliminariNonSelezionati(List<SiacTBilElem> elencoCapitoli) {
		if (elencoCapitoli.isEmpty())
			return new ArrayList<SiacTBilElem>();

		return siacTPropostaPreliminareDao.readElencoCapitoliPerPropostePreliminariNonSelezionati(elencoCapitoli);
	}

	public SiacTPropostaPreliminare readPropostaPreliminare(Integer uid) {
		return siacTPropostaPreliminareRepository.findOne(uid);
	}

	public SiacDPropostaPreliminareStato readStatoPropostaPreliminare(SiacTEnteProprietario ente,
			StatoPropostaPreliminare stato) {
		return siacDPropostaPreliminareStatoRepository.findByCodice(ente, stato.name());
	}

	public void createPropostaPreliminare(SiacTPropostaPreliminare propostaPreliminare) {
		propostaPreliminare.setStato(readStatoPropostaPreliminare(propostaPreliminare.getEnteProprietario(),
				StatoPropostaPreliminare.APERTA));

		propostaPreliminare
				.setNumero(siacTPropostaPreliminareRepository.getNextNumeroAnno(propostaPreliminare.getAnno()));

		siacTPropostaPreliminareDao.create(propostaPreliminare);
	}

	public void updatePropostaPreliminare(SiacTPropostaPreliminare propostaPreliminare) {
		siacTPropostaPreliminareDao.update(propostaPreliminare);
	}

	public void inviaPropostaPreliminare(Integer idProposta, SiacTEnteProprietario ente, String loginOperazione) {
		siacTPropostaPreliminareRepository.updateStatoPropostaPreliminare(idProposta,
				readStatoPropostaPreliminare(ente, StatoPropostaPreliminare.INVIATA), loginOperazione);
	}

	public void approvaPropostaPreliminare(Integer idProposta, SiacTEnteProprietario ente, String loginOperazione) {
		siacTPropostaPreliminareRepository.updateStatoPropostaPreliminare(idProposta,
				readStatoPropostaPreliminare(ente, StatoPropostaPreliminare.APPROVATA), loginOperazione);
	}
}

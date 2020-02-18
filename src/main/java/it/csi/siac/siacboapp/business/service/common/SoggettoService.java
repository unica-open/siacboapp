/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaSoggetti;
import it.csi.siac.siacboapp.integration.dad.SoggettoDad;
import it.csi.siac.siacboapp.integration.dao.utenti.soggetto.SiacTSoggettoDao;
import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;
import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;
import it.csi.siac.siacboapp.integration.repository.SiacDSoggettoClasseRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTSoggettoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class SoggettoService extends BoService {
	@Autowired
	private SiacTSoggettoDao siacTSoggettoDao;

	@Autowired
	private SiacTSoggettoRepository siacTSoggettoRepository;

	@Autowired
	private SiacDSoggettoClasseRepository siacDSoggettoClasseRepository;

	@Autowired
	private SoggettoDad soggettoDad;

	public void create(SiacTSoggetto soggetto) {

		siacTSoggettoDao.create(soggetto);
	}

	public List<SiacTSoggetto> getElencoSoggetti(int enteId) {
		return siacTSoggettoRepository.getElencoSoggetti(enteId);
	}

	public SiacTSoggetto read(Integer uid, int enteId) {
		SiacTSoggetto siacTSoggetto = siacTSoggettoRepository.findOne(uid, enteId);
		
		fetchEntities(siacTSoggetto.getRuoli());
		
		return siacTSoggetto;
	}

	public void update(SiacTSoggetto soggetto) {
		siacTSoggettoDao.update(soggetto);
	}
	
	public List<SiacDSoggettoClasse> readElencoClassiSoggetto(Integer idEnte) {
		return siacDSoggettoClasseRepository.getElencoClassiSoggetto(idEnte);
	}
	
	public List<SiacTSoggettoWrapper> ricercaSoggetti(Integer idEnte, CriteriRicercaSoggetti criteri) {
		return soggettoDad.ricercaSoggetti(idEnte, criteri);
	}
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.utenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.integration.dao.utenti.azione.SiacTAzioneDao;
import it.csi.siac.siacboapp.integration.entity.SiacDAzioneTipo;
import it.csi.siac.siacboapp.integration.entity.SiacTAzione;
import it.csi.siac.siacboapp.integration.repository.SiacDAzioneTipoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRRuoloOpAzioneRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTAzioneRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class AzioneService extends BoService {
	@Autowired
	private SiacTAzioneDao azioneDao;

	@Autowired
	private SiacTAzioneRepository azioneRepository;

	@Autowired
	private SiacDAzioneTipoRepository azioneTipoRepository;

	@Autowired
	private SiacRRuoloOpAzioneRepository siacRRuoloOpAzioneRepository;

	public void create(SiacTAzione azione) {
		azioneDao.create(azione);
	}

	public List<SiacTAzione> getElencoAzioni(int enteId) {
		return azioneRepository.getElencoAzioni(enteId);
	}

	public SiacTAzione read(Integer uid, int enteId) {
		SiacTAzione siacTAzione = azioneRepository.findOne(uid, enteId);
		
		fetchEntities(siacTAzione.getRuoliOp());
		
		return siacTAzione;
	}

	public void update(SiacTAzione azione) {
		siacRRuoloOpAzioneRepository.deleteByAzione(azione.getUid());

		azioneDao.update(azione);
	}

	public List<SiacDAzioneTipo> getElencoTipiAzione(int enteId) {
		return azioneTipoRepository.getElencoTipiAzione(enteId);
	}

}

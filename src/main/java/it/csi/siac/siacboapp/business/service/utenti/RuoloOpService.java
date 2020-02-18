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
import it.csi.siac.siacboapp.integration.dao.utenti.ruoloop.SiacDRuoloOpDao;
import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siacboapp.integration.repository.SiacDRuoloOpRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRRuoloOpAzioneRepository;
import it.csi.siac.siacboapp.integration.repository.SiacRRuoloOpBilRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class RuoloOpService extends BoService {
	
	@Autowired
	private SiacDRuoloOpDao ruoloOpDao;

	@Autowired
	private SiacDRuoloOpRepository ruoloOpRepository;

	@Autowired
	private SiacRRuoloOpAzioneRepository siacRRuoloOpAzioneRepository;

	@Autowired
	private SiacRRuoloOpBilRepository siacRRuoloOpBilRepository;

	public List<SiacDRuoloOp> getElencoRuoliOp(int enteId) {
		return ruoloOpRepository.getElencoRuoliOp(enteId);
	}

	public SiacDRuoloOp read(Integer uid, int enteId) {
		
		SiacDRuoloOp siacDRuoloOp = ruoloOpRepository.findOne(uid, enteId);
		
		fetchEntities(siacDRuoloOp.getAzioni());
		fetchEntities(siacDRuoloOp.getBilanci());
		
		return siacDRuoloOp;
	}

	public void create(SiacDRuoloOp ruoloOp) {
		ruoloOpDao.create(ruoloOp);
	}

	public void update(SiacDRuoloOp ruoloOp) {
		siacRRuoloOpAzioneRepository.deleteByRuoloOp(ruoloOp.getUid());
		siacRRuoloOpBilRepository.deleteByRuoloOp(ruoloOp.getUid());

		ruoloOpDao.update(ruoloOp);
	}

}

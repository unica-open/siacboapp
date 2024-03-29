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
import it.csi.siac.siacboapp.integration.entity.SiacTBil;
import it.csi.siac.siacboapp.integration.repository.SiacTBilRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class BilancioService extends BoService {
	
	@Autowired
	private SiacTBilRepository bilancioRepository;

	public List<SiacTBil> getElencoBilanci(int enteId) {
		return bilancioRepository.getElencoBilanci(enteId);
	}
}

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

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaProvvisoriCassa;
import it.csi.siac.siacboapp.integration.dad.ProvvisorioCassaDad;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTProvCassaWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional(timeout = 60)
public class ProvvisorioCassaService {
	
	@Autowired
	private ProvvisorioCassaDad provvisorioCassaDad;

	public List<SiacTProvCassaWrapper> ricercaProvvisoriCassa(Integer idEnte, CriteriRicercaProvvisoriCassa criteri) {
		return provvisorioCassaDad.ricercaProvvisoriCassa(idEnte, criteri);
	}
}

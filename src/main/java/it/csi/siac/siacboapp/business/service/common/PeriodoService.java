/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.integration.entity.SiacTPeriodo;
import it.csi.siac.siacboapp.integration.repository.SiacTPeriodoRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class PeriodoService {
	@Autowired
	private SiacTPeriodoRepository siacTPeriodoRepository;

	public SiacTPeriodo readPeriodo(int enteId, String anno) {
		return siacTPeriodoRepository.readPeriodo(enteId, anno);
	}
}

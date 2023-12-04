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

import it.csi.siac.siacboapp.integration.dad.CodificheDad;
import it.csi.siac.siacboapp.util.entitywrapper.SiacDEventoWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class CodificheService {

	@Autowired
	private CodificheDad codificheDad;
	
	public List<SiacDEventoWrapper> ricercaEventoModificaPianoDeiConti(String tipoMovimento, Integer idEnte) {
		return "I".equals(tipoMovimento) ? 
				codificheDad.ricercaEventoModificaPianoDeiContiEntrata(tipoMovimento, idEnte) :
				codificheDad.ricercaEventoModificaPianoDeiContiSpesa(tipoMovimento, idEnte);
	}
	
}

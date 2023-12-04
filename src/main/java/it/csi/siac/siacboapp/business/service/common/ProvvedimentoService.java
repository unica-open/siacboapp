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

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaProvvedimenti;
import it.csi.siac.siacboapp.integration.dad.AttoAmministrativoDad;
import it.csi.siac.siacboapp.integration.entity.SiacDAttoAmmTipo;
import it.csi.siac.siacboapp.integration.repository.SiacDAttoAmmTipoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class ProvvedimentoService {
	
	@Autowired
	private SiacDAttoAmmTipoRepository siacDAttoAmmTipoRepository;

	@Autowired
	private AttoAmministrativoDad attoAmministrativoDad;

	public List<SiacDAttoAmmTipo> readElencoTipiAttoAmministrativo(Integer idEnte) {
		return siacDAttoAmmTipoRepository.getElencoTipiAttoAmministrativo(idEnte);
	}
	
	public List<SiacTAttoAmmWrapper> ricercaProvvedimenti(Integer idEnte, CriteriRicercaProvvedimenti criteri) {
		return attoAmministrativoDad.ricercaProvvedimenti(idEnte, criteri);
	}
}

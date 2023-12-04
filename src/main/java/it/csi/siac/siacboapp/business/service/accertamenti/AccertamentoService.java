/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.accertamenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.MovimentoGestioneService;
import it.csi.siac.siacboapp.frontend.ui.model.accertamenti.CriteriRicercaAccertamenti;
import it.csi.siac.siacboapp.integration.dad.AccertamentoDad;
import it.csi.siac.siacboapp.integration.dad.MovimentoGestioneDad;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.util.entitywrapper.AccertamentoWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class AccertamentoService extends MovimentoGestioneService {

	@Autowired
	private AccertamentoDad accertamentoDad;

	@Override
	protected MovimentoGestioneDad getMovimentoGestioneDad() {
		return accertamentoDad;
	}
	
	public AccertamentoWrapper cercaAccertamento(int idEnte, int idBilancio, CriteriRicercaAccertamenti criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {
		return accertamentoDad.cercaAccertamento(idEnte, idBilancio, criteri, mapperTypes);
	}

	public void aggiornaSoggettoClasseSoggettoCollegati(int idEnte, AccertamentoWrapper accertamento, String codiceInc) {
		aggiornaSoggettoClasseSoggettoCollegati(
				idEnte, 
				codiceInc, 
				accertamento.getUid(), 
				accertamento.getSoggetto().getUid(), 
				accertamento.getClasseSoggetto() == null ? null : accertamento.getClasseSoggetto().getUid()
		);
	}
	
	//task-134
	public void aggiornaAccertamentoConBloccoRagioneria(int idEnte, AccertamentoWrapper accertamento, String loginOperazione) {
		aggiornaSac(accertamento.getUid(), accertamento.getStrutturaAmministrativoContabile(), loginOperazione);
	}
	
	//task-134
	public void aggiornaSac(Integer idAccertamento, SiacTClassWrapper sac, String loginOperazione) {
		accertamentoDad.modificaSac(idAccertamento, sac, loginOperazione);
	}


}

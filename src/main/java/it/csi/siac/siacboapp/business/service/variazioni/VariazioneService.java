/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.variazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.frontend.ui.model.variazioni.CriteriRicercaVariazioni;
import it.csi.siac.siacboapp.integration.dad.VariazioneDad;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTVariazioneWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class VariazioneService extends BoService {

	@Autowired
	private VariazioneDad variazioneDad;


	@SuppressWarnings("unchecked")
	public SiacTVariazioneWrapper cercaVariazione(int idEnte, int idBilancio, CriteriRicercaVariazioni criteri) {
		return variazioneDad.cercaVariazione(idEnte, idBilancio, criteri);
	}

	public void definisciVariazione(int idEnte, Integer annoBilancio, SiacTVariazioneWrapper variazione, String codiceInc) {
		variazioneDad.definisciVariazione(idEnte, annoBilancio, variazione, codiceInc);
	}

}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.tefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.integration.dad.TefaDad;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class TefaService extends BoService {

	@Autowired
	private TefaDad tefaDad;

	public byte[] estraiVersamenti(int idEnte, Integer idFile, Integer anno) throws Exception {
		return tefaDad.estraiVersamenti(idEnte, idFile, anno);
	}

	public byte[] estraiComuneAnnoRif(int idEnte, Integer idFile, Integer anno) throws Exception {
		return tefaDad.estraiComuneAnnoRif(idEnte, idFile, anno);
	}

}

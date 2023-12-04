/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacRVariazioneStato;
import it.csi.siac.siacboapp.integration.entity.SiacTVariazione;
import it.csi.siac.siacboapp.util.entitywrapper.EntityCodificaWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTVariazioneWrapper;

@Component
public class SiacTVariazioneStatoMapper extends BaseEntityWrapperMapper<SiacTVariazione, SiacTVariazioneWrapper> {
	
	@Override
	public void map(SiacTVariazione o, SiacTVariazioneWrapper ow) {

		for (SiacRVariazioneStato srvs : o.getStati()) {
			if (srvs != null && srvs.getDataCancellazione() == null) {
				ow.setStato(map(srvs.getStato(), EntityCodificaWrapper.class));
			}
		}
		
	}
}

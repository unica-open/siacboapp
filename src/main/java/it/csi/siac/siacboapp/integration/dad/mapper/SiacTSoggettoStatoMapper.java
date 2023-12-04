/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacRSoggettoStato;
import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;
import it.csi.siac.siacboapp.util.entitywrapper.EntityCodificaWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

@Component
public class SiacTSoggettoStatoMapper extends BaseEntityWrapperMapper<SiacTSoggetto, SiacTSoggettoWrapper> {
	
	@Override
	public void map(SiacTSoggetto o, SiacTSoggettoWrapper ow) {

		Collections.sort(o.getStati(), new Comparator<SiacRSoggettoStato>() {

			@Override
			public int compare(SiacRSoggettoStato o1, SiacRSoggettoStato o2) {
				return -( 
				    o1 == null || o1.getDataCancellazione() != null ? 1 :
					o2 == null || o2.getDataCancellazione() != null  ? -1 :
					o1.getUid().compareTo(o2.getUid())
				);
			}
		});
	
		ow.setStato(map(o.getStati().get(0).getStato(), EntityCodificaWrapper.class));
	}
}

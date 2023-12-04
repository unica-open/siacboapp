/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacTDoc;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTDocWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

@Component
public class SiacTDocSoggettoMapper extends BaseEntityWrapperMapper<SiacTDoc, SiacTDocWrapper> {

	@Override
	public void map(SiacTDoc o, SiacTDocWrapper ow) {

		try {
			ow.setSoggetto(map(o.getSoggetti().iterator().next().getSoggetto(), SiacTSoggettoWrapper.class));
		}
		catch (NullPointerException npe) {}
	}
}

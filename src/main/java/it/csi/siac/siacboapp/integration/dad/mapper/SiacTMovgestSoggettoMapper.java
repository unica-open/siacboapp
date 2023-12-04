/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTMovgestWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

@Component
public class SiacTMovgestSoggettoMapper extends BaseEntityWrapperMapper<SiacTMovgest, SiacTMovgestWrapper> {

	@Override
	public void map(SiacTMovgest o, SiacTMovgestWrapper ow) {
		try {
			ow.setSoggetto(map(o.getSoggetto(), SiacTSoggettoWrapper.class));
		}
		catch (NullPointerException npe) {}
	}
}

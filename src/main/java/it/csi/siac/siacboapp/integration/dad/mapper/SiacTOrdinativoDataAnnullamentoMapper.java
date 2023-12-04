/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.dao.ordinativi.SiacTOrdinativoDao;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

@Component
public class SiacTOrdinativoDataAnnullamentoMapper extends BaseEntityWrapperMapper<SiacTOrdinativo, SiacTOrdinativoWrapper> {
	
	@Autowired
	private SiacTOrdinativoDao siacTOrdinativoDao; 
	
	@Override
	public void map(SiacTOrdinativo o, SiacTOrdinativoWrapper ow) {
		ow.setDataAnnullamento(siacTOrdinativoDao.readDataAnnullamento(o.getUid()));
	}
}

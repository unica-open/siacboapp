/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dad.mapper.base.BaseEntityWrapperMapper;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.integration.repository.SiacTOrdinativoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

@Component
public class SiacTOrdinativoImportoMapper extends BaseEntityWrapperMapper<SiacTOrdinativo, SiacTOrdinativoWrapper> {
	
	@Autowired
	private SiacTOrdinativoRepository siacTOrdinativoRepository; 
	
	@Override
	public void map(SiacTOrdinativo o, SiacTOrdinativoWrapper ow) {
		ow.setImporto(siacTOrdinativoRepository.readImportoOrdinativo(o.getUid()));
	}
}

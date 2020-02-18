/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper.base;

import it.csi.siac.siacboapp.integration.dad.mapper.SiacTOrdinativoImportoMapper;

public enum EntityWrapperMapping  {
	
	SiacTOrdinativo_AssociaProvvisoriCassa(SiacTOrdinativoImportoMapper.class);
	
	Class<? extends EntityWrapperMapper> cls;
	
	EntityWrapperMapping(Class<? extends EntityWrapperMapper> cls) {
		this.cls = cls;
	}
	
	public Class<? extends EntityWrapperMapper> getMapperClass() {
		return cls;
	}
}
	
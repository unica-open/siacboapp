/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper.base;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.util.entitywrapper.BaseEntityWrapper;
import it.csi.siac.siaccommonser.util.dozer.DozerUtil;

public abstract class BaseMapToEntityWrapperMapper<OW extends BaseEntityWrapper> implements MapToEntityWrapperMapper {
	
	@Autowired
	protected DozerUtil dozerUtil;
	
	public abstract void map(Map<String, Object> m, OW ow);
	
	@SuppressWarnings("unchecked")
	@Override
	public void map(Map<String, Object> m, Object ow) {
		map(m, (OW) ow);
	}
	
	protected <EW extends BaseEntityWrapper> EW map(Map<String, Object> map, Class<EW> cls) {
		return dozerUtil.mapNotNull(map, cls);
	}

}

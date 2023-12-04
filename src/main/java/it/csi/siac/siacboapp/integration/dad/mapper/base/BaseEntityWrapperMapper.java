/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper.base;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.util.entitywrapper.BaseEntityWrapper;
import it.csi.siac.siaccommon.util.mapper.BaseMapper;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;
import it.csi.siac.siaccommonser.util.dozer.DozerUtil;

public abstract class BaseEntityWrapperMapper<O extends SiacTBase, OW extends BaseEntityWrapper> extends BaseMapper<O, OW> implements EntityWrapperMapper<O, OW> {
	
	@Autowired
	protected DozerUtil dozerUtil;
	
	protected <EW extends BaseEntityWrapper> EW map(SiacTBase obj, Class<EW> cls) {
		return dozerUtil.mapNotNull(obj, cls);
	}
}

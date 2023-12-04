/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper.base;

import it.csi.siac.siacboapp.util.entitywrapper.BaseEntityWrapper;
import it.csi.siac.siaccommon.util.mapper.Mapper;
import it.csi.siac.siaccommonser.integration.entity.SiacTBase;

public abstract interface EntityWrapperMapper<O extends SiacTBase, OW extends BaseEntityWrapper> extends Mapper<O, OW> {
}

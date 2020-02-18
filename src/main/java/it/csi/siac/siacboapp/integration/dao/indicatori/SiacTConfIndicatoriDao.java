/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.indicatori;

import java.math.BigDecimal;
import java.util.List;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriBase;

public interface SiacTConfIndicatoriDao extends BoBaseDao<SiacTConfIndicatoriBase> {
	public void updateIndicatori(String entity, Integer uid, List<String> fields, List<BigDecimal> values);

}

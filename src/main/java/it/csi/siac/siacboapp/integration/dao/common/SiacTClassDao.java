/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.common;

import java.util.List;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;

public interface SiacTClassDao extends BoBaseDao<SiacTClass> {
	
	public List<SiacTClass> getElencoClassificatoriByTipoFamiglia(int enteId, String codiceTipoFamiglia, Integer anno);
}

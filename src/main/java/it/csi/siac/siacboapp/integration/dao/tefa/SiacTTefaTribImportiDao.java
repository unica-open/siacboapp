/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.tefa;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.repository.SiacTTefaTribImporti;

public interface SiacTTefaTribImportiDao extends BoBaseDao<SiacTTefaTribImporti> { 
	public String uno(int idEnte, String loginOperazione, String codiceTipoOrdinativo,
			String elencoIdOrdinativi);		
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.base;

import java.util.Collection;

import it.csi.siac.siacboapp.integration.entity.SiacTEnteBase;
import it.csi.siac.siaccommon.util.log.LogUtil;
import it.csi.siac.siaccorser.model.ServiceResponse;

public abstract class BoService {
	protected transient LogUtil log = new LogUtil(this.getClass());

	protected void checkServiceResponse(ServiceResponse serviceResponse) throws Exception {
		if (serviceResponse.isFallimento())
			throw new Exception(String.format("Errore invocazione servizio (response class %s): %s",
					serviceResponse.getClass().getName(), serviceResponse.getDescrizioneErrori()));
	}

	protected <T extends SiacTEnteBase> void fetchEntities(Collection<T> entities) {
		entities.size();
	}
}

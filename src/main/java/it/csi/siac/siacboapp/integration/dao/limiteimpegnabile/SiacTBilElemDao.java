/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.limiteimpegnabile;

import java.util.List;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;

public interface SiacTBilElemDao extends BoBaseDao<SiacTBilElem> {
	List<SiacTBilElem> readElencoCapitoliLimiteImpegnabile(Integer idEnte, String annoBilancio,
			String numeroCapitolo, String numeroArticolo, String numeroUeb,
			List<Integer> idStruttureAmministrativoContabili);

}

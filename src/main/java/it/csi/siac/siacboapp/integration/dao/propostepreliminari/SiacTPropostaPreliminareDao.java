/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.propostepreliminari;

import java.util.List;
import java.util.Map;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;

public interface SiacTPropostaPreliminareDao extends BoBaseDao<SiacTPropostaPreliminare> {
	List<SiacTBilElem> readElencoCapitoliPerPropostePreliminari(SiacTEnteProprietario ente,
			List<Integer> idStruttureAmministrativoContabili, List<Integer> idProgrammi, Map<String, Integer> idClassificatoriGenerici);

	List<SiacTBilElem> readElencoCapitoliPerPropostePreliminariNonSelezionati(List<SiacTBilElem> elencoCapitoli);

}

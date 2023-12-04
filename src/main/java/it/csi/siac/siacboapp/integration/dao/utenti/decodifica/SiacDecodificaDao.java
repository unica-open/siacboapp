/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.utenti.decodifica;

import java.util.List;
import java.util.Map;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;
import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

public interface SiacDecodificaDao extends BoBaseDao<SiacDecodifica> {
	List<SiacDecodifica> getElencoDecodifiche(BkoTSystemTable table, SiacDecodifica filter);

	SiacDecodifica getDecodifica(BkoTSystemTable table, SiacDecodifica filter);

	void create(SiacDecodifica decodifica, int idEnte, String loginOperazione,
			BkoTSystemTable table);

	void update(SiacDecodifica decodifica, BkoTSystemTable table);

	void delete(Integer idDecodifica, BkoTSystemTable table);
	
	Map<String, List<SiacDecodifica>> getForeignKeyValues(BkoTSystemTable table,
			SiacTEnteProprietario siacTEnteProprietario);


}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.common;

import java.util.List;
import java.util.Map;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTModpag;

public interface SiacTModpagDao extends BoBaseDao<SiacTModpag> {

	public List<Map<String, Object>> findModalitaPagamentoSoggetto(int idEnte, String codiceSoggetto);
}

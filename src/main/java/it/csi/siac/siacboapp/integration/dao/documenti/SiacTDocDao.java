/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.documenti;

import java.util.Date;
import java.util.List;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTDoc;

public interface SiacTDocDao extends BoBaseDao<SiacTDoc> { 
	
	List<SiacTDoc> ricercaDocumenti(int idEnte, String codiceTipologia, String codiceTipo, Integer anno, String numero, Date dataEmissione, String codiceSoggetto);
	
}

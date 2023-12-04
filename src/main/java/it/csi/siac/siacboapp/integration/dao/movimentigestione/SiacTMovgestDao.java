/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.movimentigestione;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;

public interface SiacTMovgestDao extends BoBaseDao<SiacTMovgest> { 
	
	void insertSiacRMovgestTsAttr(int idEnte, Integer idMovgest, String codiceAttributo, String valoreBoolean, String valoreTesto, String loginOperazione);

	void insertSiacRMovgestTsSog(int idEnte, Integer idMovgest, Integer idSoggetto, String loginOperazione);
	
	void insertSiacRMovgestTsSogClasse(int idEnte, Integer idMovgest, Integer idSoggetto, String loginOperazione);
	
	void insertSiacRMovgestClass(int idEnte, Integer idMovgestTs, Integer classifId, String loginOperazione);
}

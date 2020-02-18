/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.test;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

public interface TestDao extends BoBaseDao<SiacTEnteProprietario> {
	
	public void test();
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.indicatori;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriEntrata;

public class ConfIndicatoriEntrataModel extends BaseConfIndicatoriAnaliticiModel {

	private static final long serialVersionUID = -1593680509615256548L;
	
	private List<SiacTConfIndicatoriEntrata> elencoConfIndicatoriEntrata;

	public List<SiacTConfIndicatoriEntrata> getElencoConfIndicatoriEntrata()
	{
		return elencoConfIndicatoriEntrata;
	}

	public void setElencoConfIndicatoriEntrata(List<SiacTConfIndicatoriEntrata> elencoConfIndicatoriEntrata)
	{
		this.elencoConfIndicatoriEntrata = elencoConfIndicatoriEntrata;
	}
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.indicatori;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriSpesa;

public class ConfIndicatoriSpesaModel extends BaseConfIndicatoriAnaliticiModel {


	private static final long serialVersionUID = -1593680509615256548L;
	
	private List<SiacTConfIndicatoriSpesa> elencoConfIndicatoriSpesa;

	public List<SiacTConfIndicatoriSpesa> getElencoConfIndicatoriSpesa()
	{
		return elencoConfIndicatoriSpesa;
	}

	public void setElencoConfIndicatoriSpesa(List<SiacTConfIndicatoriSpesa> elencoConfIndicatoriSpesa)
	{
		this.elencoConfIndicatoriSpesa = elencoConfIndicatoriSpesa;
	}
}

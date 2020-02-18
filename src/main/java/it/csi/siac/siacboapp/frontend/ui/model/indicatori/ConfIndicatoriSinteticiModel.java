/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.indicatori;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriSint;

public class ConfIndicatoriSinteticiModel extends BaseConfIndicatoriModel {

	private static final long serialVersionUID = -8083839172863773403L;
	
	private List<SiacTConfIndicatoriSint> elencoConfIndicatoriSintetici;

	public List<SiacTConfIndicatoriSint> getElencoConfIndicatoriSintetici()
	{
		return elencoConfIndicatoriSintetici;
	}

	public void setElencoConfIndicatoriSintetici(List<SiacTConfIndicatoriSint> elencoConfIndicatoriSintetici)
	{
		this.elencoConfIndicatoriSintetici = elencoConfIndicatoriSintetici;
	}

}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

public class TrasmettiOrdinativiModel extends ConfermaGestioneOrdinativiModel {

	private static final long serialVersionUID = -7675319820212058527L;

	@Override
	public String getOperazione() {
		return "trasmetti ordinativi";
	}

}

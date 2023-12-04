/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

public class SbloccaOrdinativiModel extends ConfermaGestioneOrdinativiModel {

	private static final long serialVersionUID = 7226063325278650941L;

	@Override
	public String getOperazione() {
		return "sblocca ordinativi";
	}

}

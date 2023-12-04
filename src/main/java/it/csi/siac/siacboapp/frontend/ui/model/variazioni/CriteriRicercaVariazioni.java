/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.variazioni;

import java.io.Serializable;

public class CriteriRicercaVariazioni implements Serializable {

	private static final long serialVersionUID = 1317202772013714016L;

	private Integer numero;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}

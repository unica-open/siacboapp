/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.ruoloop;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacDRuoloOp;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoRuoliOpModel extends GenericModel {

	private static final long serialVersionUID = 5397755950618439283L;

	private List<SiacDRuoloOp> elencoRuoliOp;

	public List<SiacDRuoloOp> getElencoRuoliOp() {
		return elencoRuoliOp;
	}

	public void setElencoRuoliOp(List<SiacDRuoloOp> elencoRuoliOp) {
		this.elencoRuoliOp = elencoRuoliOp;
	}
}

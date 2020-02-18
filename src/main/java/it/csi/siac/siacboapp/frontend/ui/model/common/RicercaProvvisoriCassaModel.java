/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.common;

import java.util.List;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTProvCassaWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class RicercaProvvisoriCassaModel extends GenericModel {

	private static final long serialVersionUID = -2716650753119464434L;
	
	private CriteriRicercaProvvisoriCassa criteriProvvisoriCassa;
	private List<SiacTProvCassaWrapper> elencoProvvisoriCassa;

	public CriteriRicercaProvvisoriCassa getCriteriProvvisoriCassa() {
		return criteriProvvisoriCassa;
	}

	public void setCriteriProvvisoriCassa(CriteriRicercaProvvisoriCassa criteriProvvisoriCassa) {
		this.criteriProvvisoriCassa = criteriProvvisoriCassa;
	}

	public List<SiacTProvCassaWrapper> getElencoProvvisoriCassa() {
		return elencoProvvisoriCassa;
	}

	public void setElencoProvvisoriCassa(List<SiacTProvCassaWrapper> elencoProvvisoriCassa) {
		this.elencoProvvisoriCassa = elencoProvvisoriCassa;
	}
}

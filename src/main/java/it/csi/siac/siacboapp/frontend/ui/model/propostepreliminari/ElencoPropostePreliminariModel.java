/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari;

import java.util.List;

import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoPropostePreliminariModel extends GenericModel {
	private static final long serialVersionUID = 4272608797421441711L;
	
	
	private List<SiacTBilElem> elencoCapitoli;
	private List<SiacTPropostaPreliminare> elencoProposte;

	

	public List<SiacTBilElem> getElencoCapitoli() {
		return elencoCapitoli;
	}


	public void setElencoCapitoli(List<SiacTBilElem> elencoCapitoli) {
		this.elencoCapitoli = elencoCapitoli;
	}


	public List<SiacTPropostaPreliminare> getElencoProposte() {
		return elencoProposte;
	}


	public void setElencoProposte(List<SiacTPropostaPreliminare> elencoProposte) {
		this.elencoProposte = elencoProposte;
	}
}

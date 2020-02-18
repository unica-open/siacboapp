/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile;

import it.csi.siac.siaccommonapp.model.GenericModel;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;

public class GestioneLimiteImpegnabileModel extends GenericModel {

	private static final long serialVersionUID = 3081877865502019307L;

	private Ente ente;
	private CriteriRicercaLimiteImpegnabile criteri;
	
	private String elencoStruttureAmministrativoContabiliJson;


	/**
	 * @return the ente
	 */
	public Ente getEnte() {
		return this.ente;
	}

	/**
	 * @param ente the ente to set
	 */
	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public CriteriRicercaLimiteImpegnabile getCriteri() {
		return criteri;
	}

	public void setCriteri(CriteriRicercaLimiteImpegnabile criteri) {
		this.criteri = criteri;
	}

	public String getElencoStruttureAmministrativoContabiliJson() {
		return elencoStruttureAmministrativoContabiliJson;
	}

	public void setElencoStruttureAmministrativoContabiliJson(String elencoStruttureAmministrativoContabiliJson) {
		this.elencoStruttureAmministrativoContabiliJson = elencoStruttureAmministrativoContabiliJson;
	}
	
	public boolean isGestioneUEB() {
		return this.ente != null
				&& this.ente.getGestioneLivelli() != null
				&& "GESTIONE_UEB".equals(ente.getGestioneLivelli().get(TipologiaGestioneLivelli.LIVELLO_GESTIONE_BILANCIO));
	}
}

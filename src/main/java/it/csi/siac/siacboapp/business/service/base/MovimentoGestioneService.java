/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.base;

import it.csi.siac.siacbilser.model.StatoOperativoMovimentoGestione;
import it.csi.siac.siacboapp.integration.dad.MovimentoGestioneDad;

public abstract class MovimentoGestioneService extends BoService {
	
	protected void aggiornaSoggettoClasseSoggettoCollegati(int idEnte, String codiceInc, Integer idMovgest, Integer idSoggetto, Integer idClasseSoggetto) {
		getMovimentoGestioneDad().aggiornaSoggettoCollegato(idEnte, idMovgest, idSoggetto, codiceInc);
		getMovimentoGestioneDad().aggiornaClasseSoggettoCollegata(idEnte, idMovgest, idClasseSoggetto, codiceInc);
		
		StatoOperativoMovimentoGestione statoOperativo = 
				idSoggetto != null || idClasseSoggetto != null ?  
						StatoOperativoMovimentoGestione.DEFINITIVO : StatoOperativoMovimentoGestione.DEFINITIVO_NON_LIQUIDABILE;
		
		getMovimentoGestioneDad().modificaStatoOperativo(idMovgest, statoOperativo.getCodice(), codiceInc);
	}


	protected abstract MovimentoGestioneDad getMovimentoGestioneDad();
}

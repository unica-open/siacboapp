/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.messaggio;

import java.text.MessageFormat;

import it.csi.siac.siaccorser.model.Messaggio;
import it.csi.siac.siaccorser.model.messaggio.TipoMessaggio;

public enum MessaggioBo implements TipoMessaggio {

	OPERAZIONE_EFFETTUATA_CORRETTAMENTE("BO_INF_0001", "Operazione effettuata correttamente");

	private final String codice;
	private final String messaggio;

	MessaggioBo(String codice, String messaggio) {
		this.codice = codice;
		this.messaggio = messaggio;
	}

	@Override
	public String getCodice() {
		return codice;
	}

	@Override
	public Messaggio getMessaggio(Object... args) {

		Messaggio errore = new Messaggio(codice, MessageFormat.format(
				messaggio, args));
		return errore;
	}

}

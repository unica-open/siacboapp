/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import it.csi.siac.siaccorser.model.StrutturaAmministrativoContabile;

public final class StrutturaAmministrativoContabileComparator implements Comparator<StrutturaAmministrativoContabile>, Serializable {

	private static final long serialVersionUID = 8487464758186559301L;

	public static final StrutturaAmministrativoContabileComparator INSTANCE = new StrutturaAmministrativoContabileComparator();

	private StrutturaAmministrativoContabileComparator() {
		super();
	}

	@Override
	public int compare(StrutturaAmministrativoContabile sac1, StrutturaAmministrativoContabile sac2) {
		return 
			sac1 == null && sac2 == null ? 0 :
			sac1 == null ? -1 :
			sac2 == null ? 1 :
			sac1.equals(sac2) ? 0 : 	   
			sac1.getCodice() == null && sac2.getCodice() == null ? 0 :
			sac1.getCodice() == null ? -1 :
			sac2.getCodice() == null ? 1 :
		    sac1.getCodice().compareTo(sac2.getCodice());
	}
}

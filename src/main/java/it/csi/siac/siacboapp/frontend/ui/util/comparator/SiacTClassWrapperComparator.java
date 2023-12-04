/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;

public class SiacTClassWrapperComparator implements Comparator<SiacTClassWrapper>, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final SiacTClassWrapperComparator INSTANCE = new SiacTClassWrapperComparator();

	private SiacTClassWrapperComparator() {
		super();
	}

	@Override
	public int compare(SiacTClassWrapper o1, SiacTClassWrapper o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			}
			return -1;
		}

		if (o2 == null) {
			return 1;
		}

		return o1.getCodice().compareTo(o2.getCodice());
	}

}

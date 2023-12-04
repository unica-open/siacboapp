/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

import it.csi.siac.siacboapp.integration.entity.SiacTClass;

public final class SiacTClassComparator implements Comparator<SiacTClass>, Serializable {

	private static final long serialVersionUID = 8487464758186559301L;

	public static final SiacTClassComparator INSTANCE = new SiacTClassComparator();

	private SiacTClassComparator() {
		super();
	}

	@Override
	public int compare(SiacTClass o1, SiacTClass o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			}
			return -1;
		}

		if (o2 == null) {
			return 1;
		}

		return o1.getCodiceGerarchico().compareTo(o2.getCodiceGerarchico());
	}
}

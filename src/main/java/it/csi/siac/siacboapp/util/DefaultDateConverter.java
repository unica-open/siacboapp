/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import it.csi.siac.siaccommon.util.date.DateConverter;

public final class DefaultDateConverter {
	private DefaultDateConverter() {
		// Non permettere l'instanziazione
	}
	public static Date convertToDefaultDate(String dateStr, Date defaultDate) {
		if (StringUtils.isBlank(dateStr))
			return defaultDate;
		
		return DateConverter.convertFromString(dateStr);
	}
}

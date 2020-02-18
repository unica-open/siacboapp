/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

public class CaseUtils {
	
	public static String toCamelCase(String str, char delimiter) {
		
		if (StringUtils.isEmpty(str)) {
			return str;
		}

		return WordUtils.uncapitalize(
					WordUtils.capitalizeFully(str, new char[]{delimiter})
						.replaceAll(String.valueOf(delimiter), ""));
	}
}

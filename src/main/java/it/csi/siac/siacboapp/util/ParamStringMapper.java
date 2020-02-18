/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public final class ParamStringMapper {
	private ParamStringMapper() {
		// Non permettere l'instanziazione
	}
	public static Map<String, String> mapParamString(String paramStr) {
		Map<String, String> map = new HashMap<String, String>();

		for (String p : paramStr.split("\\|"))
			map.put(StringUtils.substringBefore(p, "=").trim(), StringUtils.substringAfter(p, "="));

		return map;
	}
}

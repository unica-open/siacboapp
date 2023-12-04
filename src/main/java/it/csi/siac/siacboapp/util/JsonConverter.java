/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public final class JsonConverter {
	private JsonConverter() {
		// Non permettere l'instanziazione
	}
	
	public static String objectToJson(Object o) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
	}
}

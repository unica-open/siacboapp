/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.utenti.decodifica;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.frontend.ui.action.decodifiche.CustomDecodificaAction;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemColumn;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public abstract class GestioneLivelloAction extends CustomDecodificaAction {

	private static final long serialVersionUID = 6713879041426916465L;

	@Override
	protected Map<String, BkoTSystemColumn> getColumnConfig() throws Exception {
		ObjectMapper om = new ObjectMapper();

		String jsonInString = "{ "
				+ " \"gestione_livello_id\": { \"actions\": [] }, "
				+ " \"gestione_livello_code\": { \"actions\": [] } , "
				+ " \"gestione_livello_desc\": { \"label\": \"" + getLabel() +"\" }, "
				+ " \"gestione_tipo_id\": { \"actions\": [] }, "
				+ " \"ente_proprietario_id\": { \"actions\": [] }"
				+ " }";
		
			return om.readValue(jsonInString, new TypeReference<Map<String, BkoTSystemColumn>>(){
				// Empty subclass
			});
	}

	protected abstract String getLabel();

	@Override
	protected String getTableName()	{
		return "siac_d_gestione_livello";
	}

	@Override
	protected Map<String, Object> getFilterValues()	{
		Map<String, Object> v = new HashMap<String, Object>();

		v.put("gestione_livello_code", getCodiceGestioneLivello());	
		
		return v;
	}

	protected abstract String getCodiceGestioneLivello();
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.decodifiche;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.csi.siac.siacboapp.integration.entity.BkoTSystemColumn;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;
import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;

public abstract class CustomDecodificaAction extends BaseDecodificaAction {

	private static final long serialVersionUID = 8851725406284957778L;

	@Override
	public String execute() throws Exception {
		model.setDecodifica(new SiacDecodifica());

		BkoTSystemTable t = decodificaService.getTabellaByName(getTableName()); 

		model.getDecodifica().setTabella(t);

		List<SiacDecodifica> elencoDecodifiche = decodificaService.getElencoDecodifiche(t, sessionHandler.getEnte(), getFilterValues());
		
		if (! elencoDecodifiche.isEmpty()) {
			SiacDecodifica decodifica = elencoDecodifiche.get(0);
			
			setUid(decodifica.getUid());
		}

		String ret = super.execute();
		
		Iterator<BkoTSystemColumn> i = model.getDecodifica().getTabella().getColumns().iterator();
		
		Map<String, BkoTSystemColumn> colCfg = getColumnConfig();
		
		while(i.hasNext()) {
			BkoTSystemColumn c = i.next();
			
			if (colCfg.containsKey(c.getName())) {
				BkoTSystemColumn c0 = colCfg.get(c.getName());

				if (c0.getActions() != null)
					c.setActions(c0.getActions());
				
				if (c0.getLabel() != null)
					c.setLabel(c0.getLabel());
			}
		}
		
		return ret;
	}

	protected abstract String getTableName();

	protected abstract Map<String, Object> getFilterValues();	
	
	protected abstract Map<String, BkoTSystemColumn> getColumnConfig() throws Exception;
}

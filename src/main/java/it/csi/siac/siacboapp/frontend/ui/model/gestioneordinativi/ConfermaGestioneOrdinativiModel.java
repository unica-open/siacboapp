/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public abstract class ConfermaGestioneOrdinativiModel extends GenericModel {
	private static final long serialVersionUID = -7920036784911470784L;

	private List<SiacTOrdinativoWrapper> elencoOrdinativi;
	private String codiceTipoOrdinativi;
	private Integer[] idOrdinativi;
	private String elencoIdOrdinativi;

	public List<SiacTOrdinativoWrapper> getElencoOrdinativi() {
		return elencoOrdinativi;
	}

	public void setElencoOrdinativi(List<SiacTOrdinativoWrapper> elencoOrdinativi) {
		this.elencoOrdinativi = elencoOrdinativi;
	}

	public Integer[] getIdOrdinativi() {
		if (idOrdinativi == null) {
			idOrdinativi = elencoToIdArray(elencoIdOrdinativi);
		}
			
		return idOrdinativi;
	}

//	public void setIdOrdinativi(Integer[] idOrdinativi) {
//		this.idOrdinativi = idOrdinativi;
//	}

	public String getCodiceTipoOrdinativi() {
		return codiceTipoOrdinativi;
	}

	public void setCodiceTipoOrdinativi(String codiceTipoOrdinativi) {
		this.codiceTipoOrdinativi = codiceTipoOrdinativi;
	}

	public abstract String getOperazione();
	
	public String getCapitalizedOperazione() {
		return WordUtils.capitalize(getOperazione());
	}

	public String getElencoIdOrdinativi() {
		return elencoIdOrdinativi;
	}

	public void setElencoIdOrdinativi(String elencoIdOrdinativi) {
		this.elencoIdOrdinativi = elencoIdOrdinativi;
	}
	
	protected Integer[] elencoToIdArray(String elencoId) {
			
		Integer[] idArray = null;
		
		if (StringUtils.isNotBlank(elencoId)) {
			
			String[] elencoIdArr = StringUtils.split(elencoId, ',');
			
			idArray = new Integer[elencoIdArr.length];
			
			for (int i = 0; i < elencoIdArr.length; i++) {
				idArray[i] = new Integer(elencoIdArr[i]);
			}
		}

		return idArray;
	}
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.ordinativi;

import java.util.List;

import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ConsultaFlussoModel extends GenericModel {
	private static final long serialVersionUID = -7920036784911470784L;

	private List<SiacTOrdinativoWrapper> elencoOrdinativi;
	
	private Integer uid;
	private String anno;
	private String codiceTipoOrdinativi;


	public List<SiacTOrdinativoWrapper> getElencoOrdinativi() {
		return elencoOrdinativi;
	}

	public void setElencoOrdinativi(List<SiacTOrdinativoWrapper> elencoOrdinativi) {
		this.elencoOrdinativi = elencoOrdinativi;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getCodiceTipoOrdinativi() {
		return codiceTipoOrdinativi;
	}

	public void setCodiceTipoOrdinativi(String codiceTipoOrdinativi) {
		this.codiceTipoOrdinativi = codiceTipoOrdinativi;
	}

}

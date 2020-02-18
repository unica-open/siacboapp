/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile;

import java.util.Map;

import it.csi.siac.siacboapp.frontend.ui.model.common.TipoImportoCapitolo;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElemDet;
import it.csi.siac.siacboapp.integration.repository.SiacTBilElemDetRepository;
import it.csi.siac.siacboapp.util.NumberFormatter;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ImportiLimiteImpegnabileModel extends GenericModel {
	private static final long serialVersionUID = 5420912340988086398L;

	private Integer anno;

	private Integer idImportoAnno;
	private Integer idImportoAnno1;
	private Integer idImportoAnno2;

	private String importoAnno;
	private String importoAnno1;
	private String importoAnno2;

	public void setImportiCapitolo(Map<String, SiacTBilElemDet> importiMap) {
		SiacTBilElemDet siacTBilElemDet;

		siacTBilElemDet = importiMap.get(String.format(SiacTBilElemDetRepository.MAP_IMPORTI_KEY_PATTERN,
				TipoImportoCapitolo.MASSIMO_IMPEGNABILE.getCodice(), anno));
		if (siacTBilElemDet != null) {
			setIdImportoAnno(siacTBilElemDet.getUid());
			setImportoAnno(NumberFormatter.numberToImporto(siacTBilElemDet.getImporto()));
		}

		siacTBilElemDet = importiMap.get(String.format(SiacTBilElemDetRepository.MAP_IMPORTI_KEY_PATTERN,
				TipoImportoCapitolo.MASSIMO_IMPEGNABILE.getCodice(), anno + 1));
		if (siacTBilElemDet != null) {
			setIdImportoAnno1(siacTBilElemDet.getUid());
			setImportoAnno1(NumberFormatter.numberToImporto(siacTBilElemDet.getImporto()));
		}

		siacTBilElemDet = importiMap.get(String.format(SiacTBilElemDetRepository.MAP_IMPORTI_KEY_PATTERN,
				TipoImportoCapitolo.MASSIMO_IMPEGNABILE.getCodice(), anno + 2));
		if (siacTBilElemDet != null) {
			setIdImportoAnno2(siacTBilElemDet.getUid());
			setImportoAnno2(NumberFormatter.numberToImporto(siacTBilElemDet.getImporto()));
		}
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getImportoAnno() {
		return importoAnno;
	}

	public void setImportoAnno(String importoAnno) {
		this.importoAnno = importoAnno;
	}

	public String getImportoAnno1() {
		return importoAnno1;
	}

	public void setImportoAnno1(String importoAnno1) {
		this.importoAnno1 = importoAnno1;
	}

	public String getImportoAnno2() {
		return importoAnno2;
	}

	public void setImportoAnno2(String importoAnno2) {
		this.importoAnno2 = importoAnno2;
	}

	public Integer getIdImportoAnno() {
		return idImportoAnno;
	}

	public void setIdImportoAnno(Integer idImportoAnno) {
		this.idImportoAnno = idImportoAnno;
	}

	public Integer getIdImportoAnno1() {
		return idImportoAnno1;
	}

	public void setIdImportoAnno1(Integer idImportoAnno1) {
		this.idImportoAnno1 = idImportoAnno1;
	}

	public Integer getIdImportoAnno2() {
		return idImportoAnno2;
	}

	public void setIdImportoAnno2(Integer idImportoAnno2) {
		this.idImportoAnno2 = idImportoAnno2;
	}

}

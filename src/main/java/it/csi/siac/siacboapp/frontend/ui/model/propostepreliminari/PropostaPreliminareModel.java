/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.propostepreliminari;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import it.csi.siac.siacboapp.frontend.ui.model.common.TipoImportoCapitolo;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElemDet;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;
import it.csi.siac.siacboapp.integration.repository.SiacTBilElemDetRepository;
import it.csi.siac.siacboapp.util.NumberFormatter;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class PropostaPreliminareModel extends GenericModel {
	private static final long serialVersionUID = -4907810740282198264L;

	public static final String[] ELENCO_TIPI_IMPORTO = new String[] { "Competenza", "Residuo", "Cassa" };
	public static final String[] ELENCO_OFFSET_ANNO = new String[] { "", "1", "2" };

	private SiacTPropostaPreliminare proposta;
	private List<SiacTBilElem> elencoCapitoli;
	
	private String importoCompetenzaAnno;
	private String importoCompetenzaAnno1;
	private String importoCompetenzaAnno2;
	private String importoCassaAnno;
	private String importoCassaAnno1;
	private String importoCassaAnno2;
	private String importoResiduoAnno;
	private String importoResiduoAnno1;
	private String importoResiduoAnno2;
	
	
	public SiacTPropostaPreliminare getProposta() {
		return proposta;
	}

	public void setProposta(SiacTPropostaPreliminare proposta) {
		this.proposta = proposta;
	}

	public void setProposta(SiacTBilElem capitolo) {
		proposta = new SiacTPropostaPreliminare();

		proposta.setDescrizione(capitolo.getDescrizione());
		proposta.setDescrizioneArticolo(capitolo.getDescrizioneArticolo());
		proposta.setCapitolo(capitolo);
	}

	public List<SiacTBilElem> getElencoCapitoli() {
		return elencoCapitoli;
	}

	public void setElencoCapitoli(List<SiacTBilElem> elencoCapitoli) {
		this.elencoCapitoli = elencoCapitoli;
	}

	public String getImportoCompetenzaAnno() {
		return importoCompetenzaAnno;
	}

	public void setImportoCompetenzaAnno(String importoCompetenzaAnno) {
		this.importoCompetenzaAnno = importoCompetenzaAnno;
	}

	public String getImportoCompetenzaAnno1() {
		return importoCompetenzaAnno1;
	}

	public void setImportoCompetenzaAnno1(String importoCompetenzaAnno1) {
		this.importoCompetenzaAnno1 = importoCompetenzaAnno1;
	}

	public String getImportoCompetenzaAnno2() {
		return importoCompetenzaAnno2;
	}

	public void setImportoCompetenzaAnno2(String importoCompetenzaAnno2) {
		this.importoCompetenzaAnno2 = importoCompetenzaAnno2;
	}

	public String getImportoCassaAnno() {
		return importoCassaAnno;
	}

	public void setImportoCassaAnno(String importoCassaAnno) {
		this.importoCassaAnno = importoCassaAnno;
	}

	public String getImportoCassaAnno1() {
		return importoCassaAnno1;
	}

	public void setImportoCassaAnno1(String importoCassaAnno1) {
		this.importoCassaAnno1 = importoCassaAnno1;
	}

	public String getImportoCassaAnno2() {
		return importoCassaAnno2;
	}

	public void setImportoCassaAnno2(String importoCassaAnno2) {
		this.importoCassaAnno2 = importoCassaAnno2;
	}

	public String getImportoResiduoAnno() {
		return importoResiduoAnno;
	}

	public void setImportoResiduoAnno(String importoResiduoAnno) {
		this.importoResiduoAnno = importoResiduoAnno;
	}

	public String getImportoResiduoAnno1() {
		return importoResiduoAnno1;
	}

	public void setImportoResiduoAnno1(String importoResiduoAnno1) {
		this.importoResiduoAnno1 = importoResiduoAnno1;
	}

	public String getImportoResiduoAnno2() {
		return importoResiduoAnno2;
	}

	public void setImportoResiduoAnno2(String importoResiduoAnno2) {
		this.importoResiduoAnno2 = importoResiduoAnno2;
	}

	public void setImporti(Map<String, SiacTBilElemDet> mapImporti) throws IllegalAccessException, InvocationTargetException {
		for (String tipoImporto : PropostaPreliminareModel.ELENCO_TIPI_IMPORTO)
			for (String offsetAnno : PropostaPreliminareModel.ELENCO_OFFSET_ANNO) {
				String property = String.format("importo%sAnno%s", tipoImporto, offsetAnno);

				BeanUtils.setProperty(this, property, getImporto(mapImporti, TipoImportoCapitolo.valueOf(tipoImporto.toUpperCase()), offsetAnno));
			}	
	}

	private String getImporto(Map<String, SiacTBilElemDet> mapImporti, TipoImportoCapitolo tipo, String offsetAnno) {
		SiacTBilElemDet impObj = mapImporti.get(String.format(SiacTBilElemDetRepository.MAP_IMPORTI_KEY_PATTERN,
				tipo.getCodice(), String.valueOf(offsetToAnno(offsetAnno))));
		
		return impObj != null ? NumberFormatter.numberToImporto(impObj.getImporto()) : null;
	}

	public void setImporti(SiacTPropostaPreliminare proposta2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (String tipoImporto : PropostaPreliminareModel.ELENCO_TIPI_IMPORTO)
			for (String offsetAnno : PropostaPreliminareModel.ELENCO_OFFSET_ANNO) {
				String property = String.format("importo%sAnno%s", tipoImporto, offsetAnno);

				BeanUtils.setProperty(this, property,
						NumberFormatter.numberToImporto((BigDecimal) PropertyUtils.getSimpleProperty(proposta, property)));
			}	
	}
	
	public Integer offsetToAnno(String idx) {
		return proposta.getAnno() + ("".equals(idx) ? 0 : Integer.parseInt(idx));
	}

}

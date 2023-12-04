/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.utenti.report.importi;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import it.csi.siac.siacboapp.integration.entity.SiacTReport;
import it.csi.siac.siacboapp.integration.entity.SiacTReportImporti;
import it.csi.siac.siaccommon.util.number.NumberUtil;
import it.csi.siac.siaccommonapp.model.GenericModel;

public class ElencoImportiReportModel extends GenericModel {
	private static final long serialVersionUID = -28371713748011239L;

	private Integer annoEsercizio;

	private List<SiacTReport> elencoReport;

	private Map<String, Importi> elencoImportiFissi = new LinkedHashMap<String, Importi>();

	private Map<String, Map<String, Importi>> elencoImportiVariabili = new LinkedHashMap<String, Map<String, Importi>>();

	private Map<String, Map<String, Importi>> elencoDescrizioniNull = new LinkedHashMap<String, Map<String, Importi>>();

	private Map<Integer, String> importo;
	private Map<String, String> descrizioneImporti;

	public void initImporti(List<SiacTReportImporti> elencoImporti) {
		for (SiacTReportImporti imp : elencoImporti) {
			if (imp.isModificabile()) {
				addToImportiVariabili(imp);
			}
			else {
				addImporto(elencoImportiFissi, imp);
				
			}
		}
		
		for (String key : elencoDescrizioniNull.keySet())
			elencoImportiVariabili.get(key).putAll(elencoDescrizioniNull.get(key));
	}

	private void addToImportiVariabili(SiacTReportImporti imp) {
		String codice = imp.getCodice();

		if (!elencoImportiVariabili.containsKey(codice))
			elencoImportiVariabili.put(codice, new LinkedHashMap<String, Importi>());

		if (StringUtils.isBlank(imp.getDescrizione())) {
			if (!elencoDescrizioniNull.containsKey(codice))
				elencoDescrizioniNull.put(codice, new LinkedHashMap<String, Importi>());

			addImporto(elencoDescrizioniNull.get(codice), imp);
		} else
			addImporto(elencoImportiVariabili.get(codice), imp);
	}

	private void addImporto(Map<String, Importi> elencoImporti, SiacTReportImporti imp) {
		String key = String.valueOf(imp.getRiga());

		if (!elencoImporti.containsKey(key))
			elencoImporti.put(key, new Importi(imp.getDescrizione()));

		elencoImporti.get(key).getImporti().put(Integer.valueOf(imp.getPeriodo().getAnno()), imp);
	}

	public List<SiacTReport> getElencoReport() {
		return elencoReport;
	}

	public void setElencoReport(List<SiacTReport> elencoReport) {
		this.elencoReport = elencoReport;
	}

	public Map<String, Importi> getElencoImportiFissi() {
		return elencoImportiFissi;
	}

	public void setElencoImportiFissi(Map<String, Importi> elencoImportiFissi) {
		this.elencoImportiFissi = elencoImportiFissi;
	}

	public Map<String, Map<String, Importi>> getElencoImportiVariabili() {
		return elencoImportiVariabili;
	}

	public void setElencoImportiVariabili(Map<String, Map<String, Importi>> elencoImportiVariabili) {
		this.elencoImportiVariabili = elencoImportiVariabili;
	}

	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}

	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}

	public void setImporto(Map<Integer, String> importo) {
		this.importo = importo;
	}

	public Map<Integer, String> getImporto() {
		return importo;
	}

	public Map<String, String> getDescrizioneImporti() {
		return descrizioneImporti;
	}

	public void setDescrizioneImporti(Map<String, String> descrizioneImporti) {
		this.descrizioneImporti = descrizioneImporti;
	}

	public String toImporto(BigDecimal bd) {
		return NumberUtil.toImporto(bd);
	}

	// ////////////////////////////////////////

	public static class Importi {
		public static final String SEPARATOR = "_";

		private String descrizione;

		private Map<Integer, SiacTReportImporti> importi = new HashMap<Integer, SiacTReportImporti>();

		public Importi(String descrizione) {
			this();
			this.descrizione = descrizione;
		}

		public Importi() {
			// Costruttore vuoto
		}

		public String getDescrizione() {
			return descrizione;
		}

		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}

		public Map<Integer, SiacTReportImporti> getImporti() {
			return importi;
		}

		public void setImporti(Map<Integer, SiacTReportImporti> importi) {
			this.importi = importi;
		}

		public String getElencoIdImporti() {
			StringBuilder sb = new StringBuilder();

			for (SiacTReportImporti imp : importi.values())
				sb.append(imp.getUid() + SEPARATOR);

			sb.deleteCharAt(sb.length() - 1);

			return sb.toString();
		}
	}
}

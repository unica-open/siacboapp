/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.tefa.TefaDao;
import it.csi.siac.siaccommon.util.number.NumberUtil;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TefaDad extends BoBaseDad {

	@Autowired
	private TefaDao tefaDao;
	
	private static final String SEP = ",";
	
	private static final String SEP_METADATA = "sep=" + SEP;
	
	public byte[] estraiVersamenti(int idEnte, Integer idFile, Integer anno) throws Exception {
		List<Map<String, Object>> list = tefaDao.estraiVersamenti(idEnte, idFile, anno);
		
		StringBuilder sb = new StringBuilder(SEP_METADATA).append("\n")
				.append(formatCsvLine(
				"data_ripartizione", 
				"data_bonifico", 
				"raggruppamento_codice_tributo", 
				"importo_a_debito_versato", 
				"importo_a_debito_versato_2", 
				"importo_a_credito_compensato", 
				"importo_a_credito_compensato_2", 
				"anno_di_riferimento_str", 
				"importo_tefa_lordo", 
				"importo_tefa_lordo_2", 
				"importo_credito", 
				"importo_credito_2", 
				"importo_comm", 
				"importo_comm_2", 
				"importo_tefa_netto",
				"importo_tefa_netto_2"
		));

		for (Map<String, Object> map : list) {
			sb.append(formatCsvLine(
					map.get("data_ripartizione"),
					map.get("data_bonifico"),
					map.get("raggruppamento_codice_tributo"),
					formatDecimal5(map.get("importo_a_debito_versato")), 
					formatDecimal2(map.get("importo_a_debito_versato")),
					formatDecimal5(map.get("importo_a_credito_compensato")), 
					formatDecimal2(map.get("importo_a_credito_compensato")),
					map.get("anno_di_riferimento_str"),
					formatDecimal5(map.get("importo_tefa_lordo")),
					formatDecimal2(map.get("importo_tefa_lordo")),
					formatDecimal5(map.get("importo_credito")),
					formatDecimal2(map.get("importo_credito")),
					formatDecimal5(map.get("importo_comm")),
					formatDecimal2(map.get("importo_comm")),
					formatDecimal5(map.get("importo_tefa_netto")),
					formatDecimal2(map.get("importo_tefa_netto"))
			));
		}
		
		return sb.toString().getBytes("utf-8");
	}
	
	
	public byte[] estraiComuneAnnoRif(int idEnte, Integer idFile, Integer anno) throws Exception {
		List<Map<String, Object>> list = tefaDao.estraiComuneAnnoRif(idEnte, idFile, anno);
		
		StringBuilder sb = new StringBuilder(SEP_METADATA).append("\n")
				.append(formatCsvLine(
				"codice_comune", 
				"raggruppamento_codice_tributo", 
				"importo_a_debito_versato", 
				"importo_a_debito_versato_2", 
				"importo_a_credito_compensato", 
				"importo_a_credito_compensato_2", 
				"anno_di_riferimento_str", 
				"ente", 
				"anno_di_riferimento", 
				"tipologia", 
				"importo_tefa_lordo", 
				"importo_tefa_lordo_2", 
				"importo_credito", 
				"importo_credito_2", 
				"importo_comm", 
				"importo_comm_2", 
				"importo_tefa_netto",
				"importo_tefa_netto_2"
		));

		for (Map<String, Object> map : list) {
			sb.append(formatCsvLine(
					map.get("codice_comune"),
					map.get("raggruppamento_codice_tributo"),
					formatDecimal5(map.get("importo_a_debito_versato")),
					formatDecimal2(map.get("importo_a_debito_versato")),
					formatDecimal5(map.get("importo_a_credito_compensato")),
					formatDecimal2(map.get("importo_a_credito_compensato")),
					map.get("anno_di_riferimento_str"),
					map.get("ente"),
					map.get("anno_di_riferimento"),
					map.get("tipologia"),
					formatDecimal5(map.get("importo_tefa_lordo")),
					formatDecimal2(map.get("importo_tefa_lordo")),
					formatDecimal5(map.get("importo_credito")),
					formatDecimal2(map.get("importo_credito")),
					formatDecimal5(map.get("importo_comm")),
					formatDecimal2(map.get("importo_comm")),
					formatDecimal5(map.get("importo_tefa_netto")),
					formatDecimal2(map.get("importo_tefa_netto"))
			));
		}
		
		return sb.toString().getBytes("utf-8");
	}

	
	private String formatDecimal5(Object val) {
		return formatDecimal(val, 5, RoundingMode.DOWN);
	}
	
	private String formatDecimal2(Object val) {
		return formatDecimal(val, 2, RoundingMode.HALF_UP);
	}
	
	private String formatDecimal(Object val, int scale, RoundingMode roundingMode) {
		if (val == null) {
			return null;
		}
		
		if (val instanceof Number) {
			val = new BigDecimal(((Number) val).doubleValue());
		}
		
		if (val instanceof BigDecimal) {
			return NumberUtil.toDecimal(((BigDecimal) val).setScale(scale, roundingMode), scale);
		}
		
		return val.toString().replace(".", ",");
	}
	
	/**
	 * @deprecated @see it.csi.siac.siaccommon.util.file.CsvExportUtil
	 */
	@Deprecated
	private String formatCsvLine(Object...fields) {
		
		if (fields == null || fields.length == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();

		for (Object f : fields) {
			
			if (f == null) {
				sb.append("\"\"").append(SEP);
				continue;
			}
			
			String s = f.toString(); 
			
			if (StringUtils.isEmpty(s)) {
				sb.append("\"\"").append(SEP);
				continue;
			}
			
			if (s.contains("\"") || s.contains("'") || s.contains(SEP) || s.startsWith("\n") || s.startsWith(" ") || s.endsWith(" ") || s.startsWith("\t")  || s.startsWith("\t")) {
				sb.append(String.format("\"%s\"", s.replace("\"", "\"\""))).append(SEP);
				continue;
			}
			
			sb.append(s).append(SEP);
		}
		
		sb.deleteCharAt(sb.length()-1).append("\n");
		
		return sb.toString();
	}
	
	

}

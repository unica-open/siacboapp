/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.action.report.importi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import it.csi.siac.siacboapp.business.service.report.ImportiReportService;
import it.csi.siac.siacboapp.frontend.ui.action.base.GenericBoAction;
import it.csi.siac.siacboapp.frontend.ui.model.utenti.report.importi.ElencoImportiReportModel;
import it.csi.siac.siacboapp.integration.entity.SiacTReport;
import it.csi.siac.siaccommon.util.number.NumberUtils;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ElencoImportiReportAction extends GenericBoAction<ElencoImportiReportModel> {

	private static final long serialVersionUID = 6582612235509439501L;

	@Autowired
	private ImportiReportService importiReportService;

	private Integer idReport;

	public void prepareExecute() throws Exception {
		super.prepare();

		List<SiacTReport> elencoReport = importiReportService.getElencoReport(sessionHandler
				.getEnte());

		model.setAnnoEsercizio(Integer.parseInt(sessionHandler.getAnnoEsercizio()));

		model.setElencoReport(elencoReport);

		if (idReport != null)
			model.initImporti(importiReportService.getElencoImportiReport(idReport,
					sessionHandler.getEnte(), sessionHandler.getBilancio()));
	}

	@Override
	protected void initModel() {
		super.initModel();

		model.setTitolo(sessionHandler.getAzione().getTitolo());
	}

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	public String update() throws Exception {
		if (model.getImporto() != null && !model.getImporto().isEmpty())
			for (Map.Entry<Integer, String> imp : model.getImporto().entrySet()) {
//				if (StringUtils.isBlank(imp.getValue())) {
//					addErrore(ErroreCore.DATO_OBBLIGATORIO_OMESSO.getErrore("importo"));
//
//					return INPUT;
//				}

				try {
					importiReportService.updateImporto(imp.getKey(),
							NumberUtils.importoToBigDecimal(imp.getValue()));
				} catch (NumberFormatException nfe) {
					addErrore(ErroreCore.FORMATO_NON_VALIDO.getErrore("importo", imp.getValue()));

					return INPUT;
				}
			}

		if (model.getDescrizioneImporti() != null && !model.getDescrizioneImporti().isEmpty())
			for (Map.Entry<String, String> desc : model.getDescrizioneImporti().entrySet())
				importiReportService.updateDescrizione(desc.getKey(), desc.getValue());

		return "elencoImportiReport";
	}

	public Integer getIdReport() {
		return idReport;
	}

	public void setIdReport(Integer idReport) {
		this.idReport = idReport;
	}

}

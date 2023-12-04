/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.report;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.frontend.ui.model.utenti.report.importi.ElencoImportiReportModel.Importi;
import it.csi.siac.siacboapp.integration.dao.report.SiacSReportImportiDao;
import it.csi.siac.siacboapp.integration.dao.report.SiacTReportImportiDao;
import it.csi.siac.siacboapp.integration.entity.SiacSReportImporti;
import it.csi.siac.siacboapp.integration.entity.SiacTReport;
import it.csi.siac.siacboapp.integration.entity.SiacTReportImporti;
import it.csi.siac.siacboapp.integration.repository.SiacTReportImportiRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTReportRepository;
import it.csi.siac.siaccorser.model.Bilancio;
import it.csi.siac.siaccorser.model.Ente;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class ImportiReportService {
	@Autowired
	private SiacTReportRepository siacTReportRepository;

	@Autowired
	private SiacTReportImportiDao siacTReportImportiDao;

	@Autowired
	private SiacSReportImportiDao siacSReportImportiDao;

	@Autowired
	private SiacTReportImportiRepository siacTReportImportiRepository;

	public List<SiacTReport> getElencoReport(Ente ente) {
		return siacTReportRepository.getElencoReport(ente.getUid());
	}

	public List<SiacTReportImporti> getElencoImportiReport(Integer idReport, Ente ente,
			Bilancio bilancio) {
		return siacTReportImportiRepository.getElencoImportiReport(idReport, ente.getUid(),
				bilancio.getUid());

	}

	public void updateImporto(Integer uid, BigDecimal importo) {
		SiacTReportImporti ri = siacTReportImportiRepository.findOne(uid);
		ri.setImporto(importo);

		siacSReportImportiDao.create(new SiacSReportImporti(ri));

		siacTReportImportiDao.update(ri);
	}

	public void updateDescrizione(String elencoIdImporti, String desc) {
		for (String uid : StringUtils.split(elencoIdImporti, Importi.SEPARATOR)) {
			SiacTReportImporti ri = siacTReportImportiRepository.findOne(Integer.parseInt(uid));
			ri.setDescrizione(desc);

			siacTReportImportiDao.update(ri);
		}

	}

}

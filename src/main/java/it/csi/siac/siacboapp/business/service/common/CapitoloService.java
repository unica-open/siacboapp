/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.integration.dao.capitolo.SiacTBilElemDetDao;
import it.csi.siac.siacboapp.integration.entity.SiacDBilElemDetTipo;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElemDet;
import it.csi.siac.siacboapp.integration.repository.SiacDBilElemDetTipoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTBilElemDetRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTBilElemRepository;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class CapitoloService {
	@Autowired
	private SiacTBilElemRepository siacTBilElemRepository;

	@Autowired
	private SiacTBilElemDetDao siacTBilElemDetDao;

	@Autowired
	private SiacTBilElemDetRepository siacTBilElemDetRepository;

	@Autowired
	private SiacDBilElemDetTipoRepository  siacDBilElemDetTipoRepository;

	public Map<String, SiacTBilElemDet> readImportiCapitolo(Integer idCapitolo, List<String> tipiImportoCapitolo,
			List<String> anni) {
		Map<String, SiacTBilElemDet> map = new HashMap<String, SiacTBilElemDet>();

		List<SiacTBilElemDet> list = siacTBilElemDetRepository.readImportiCapitolo(idCapitolo, tipiImportoCapitolo,
				anni);

		for (SiacTBilElemDet importo : list)
			map.put(String.format(SiacTBilElemDetRepository.MAP_IMPORTI_KEY_PATTERN, importo.getTipo().getCodice(),
					importo.getPeriodo().getAnno()), importo);

		return map;
	}

	public SiacTBilElem readCapitolo(Integer uid) {
		return siacTBilElemRepository.findOne(uid);
	}

	public void updateImportoCapitolo(SiacTBilElemDet importoCapitolo) {
		if (importoCapitolo.getUid() == null)
			siacTBilElemDetDao.create(importoCapitolo);
		else
			siacTBilElemDetRepository.updateImporto(importoCapitolo.getUid(), importoCapitolo.getImporto(), importoCapitolo.getLoginOperazione());
	}

	public SiacTBilElemDet readImportoCapitolo(Integer uid) {
		return siacTBilElemDetRepository.findOne(uid);
	}

	public SiacDBilElemDetTipo readTipoImportoCapitolo(Integer enteId, String codiceTipo) {
		return siacDBilElemDetTipoRepository.readTipoImportoCapitolo(enteId, codiceTipo);
	}

	public void updateImportiCapitolo(List<SiacTBilElemDet> elencoImportiCapitolo) {
		for (SiacTBilElemDet siacTBilElemDet : elencoImportiCapitolo)
			updateImportoCapitolo(siacTBilElemDet);
	}

	public void deleteImportoCapitolo(Integer idImportoCapitolo) {
		siacTBilElemDetRepository.delete(idImportoCapitolo);
	}
	
	public Map<Integer, BigDecimal> readImportiMovimentiGestione(Integer idCapitolo, List<Integer> anni) {
		Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();

		List<Object[]> list = siacTBilElemDetRepository.readImportiImpegnoPerCapitoloAndAnni(idCapitolo, anni);
		for(Object[] obj : list) {
			map.put((Integer)obj[0], (BigDecimal)obj[1]);
		}

		return map;
	}
}

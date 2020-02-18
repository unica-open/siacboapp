/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.indicatori;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.integration.dao.indicatori.SiacTConfIndicatoriDao;
import it.csi.siac.siacboapp.integration.entity.SiacDGestioneLivello;
import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriEntrata;
import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriSint;
import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriSpesa;
import it.csi.siac.siacboapp.integration.repository.SiacDGestioneLivelloRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTConfIndicatoriEntrataRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTConfIndicatoriSintRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTConfIndicatoriSpesaRepository;
import it.csi.siac.siaccommon.util.number.NumberUtils;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class IndicatoriService extends BoService {

	@Autowired
	private SiacTConfIndicatoriEntrataRepository siacTConfIndicatoriEntrataRepository;
	
	@Autowired
	private SiacTConfIndicatoriSpesaRepository siacTConfIndicatoriSpesaRepository;
	
	@Autowired
	private SiacTConfIndicatoriSintRepository siacTConfIndicatoriSintRepository;
	
	@Autowired
	private SiacDGestioneLivelloRepository siacDGestioneLivelloRepository;
	
	@Autowired
	private SiacTConfIndicatoriDao siacTConfIndicatoriDao;

	public List<SiacTConfIndicatoriEntrata> readElencoConfIndicatoriEntrata(Integer idEnte, Integer idBilancio) {
		return siacTConfIndicatoriEntrataRepository.readElencoConfIndicatoriEntrata(idEnte, idBilancio);
	}

	public List<SiacTConfIndicatoriSpesa> readElencoConfIndicatoriSpesa(Integer idEnte, Integer idBilancio) {
		return siacTConfIndicatoriSpesaRepository.readElencoConfIndicatoriSpesa(idEnte, idBilancio);
	}

	public List<SiacTConfIndicatoriSint> readElencoConfIndicatoriSintetici(Integer idEnte, Integer idBilancio) {
		return siacTConfIndicatoriSintRepository.readElencoConfIndicatoriSintetici(idEnte, idBilancio);
	}

	public Integer readNumeroAnniBilancioPrevisione(int idEnte, Integer annoBilancio)
	{
		SiacDGestioneLivello siacDGestioneLivello = siacDGestioneLivelloRepository.getGestioneLivello(idEnte,
				String.format("%s_%d", TipologiaGestioneLivelli.CONF_NUM_ANNI_BIL_PREV_INDIC.getCodice(), annoBilancio));
		  
		if (siacDGestioneLivello == null) {
			return null;
		}
		
		return StringUtils.isNotBlank(siacDGestioneLivello.getDescrizione()) ? Integer.parseInt(siacDGestioneLivello.getDescrizione()) : null;
	}
	
	public void updateIndicatori(String entity, String[] valueFields, Map<Integer, List<String>> updateMap)
	{
		for (Map.Entry<Integer, List<String>> ind: updateMap.entrySet()) {
			
			List<String> fields = new ArrayList<String>();
			List<BigDecimal> values = new ArrayList<BigDecimal>();
			
			for (String fv : ind.getValue()) {
				String[] tmp =  StringUtils.split(fv, "=");

				fields.add(valueFields[Integer.parseInt(tmp[0])]);
				
				String value = tmp.length > 1 ? tmp[1] : null;
				values.add(NumberUtils.decimalToBigDecimal(value));
			}
			
			siacTConfIndicatoriDao.updateIndicatori(entity, ind.getKey(), fields, values);
		}
	}
}

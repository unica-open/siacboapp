/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaSoggetti;
import it.csi.siac.siacboapp.integration.dad.mapper.ModalitaPagamentoSoggettoMapper;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTSoggettoStatoMapper;
import it.csi.siac.siacboapp.integration.dao.common.SiacTModpagDao;
import it.csi.siac.siacboapp.integration.repository.SiacTSoggettoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTModpagWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SoggettoDad extends BoBaseDad {

	@Autowired
	private SiacTSoggettoRepository siacTSoggettoRepository;
	
	@Autowired
	private SiacTModpagDao siacTModpagDao;

	public List<SiacTSoggettoWrapper> ricercaSoggetti(Integer idEnte, CriteriRicercaSoggetti criteri) {

		return mapEntityListToWrapperList(
				siacTSoggettoRepository.ricercaSoggetti(
						idEnte, 
						criteri.getCodice(), 
						criteri.getCodiceFiscale(), 
						criteri.getPartitaIva(), 
						criteri.getDenominazione(), 
						criteri.getIdClasse()
				), 
				SiacTSoggettoWrapper.class, 
				SiacTSoggettoStatoMapper.class);

//		
//
//		return mapList(siacTSoggettoRepository.ricercaSoggetti(
//				idEnte, 
//				criteri.getCodice(), 
//				criteri.getCodiceFiscale(), 
//				criteri.getPartitaIva(), 
//				criteri.getDenominazione(), 
//				criteri.getIdClasse()
//		), SiacTSoggettoWrapper.class);
	}

	public List<SiacTModpagWrapper> readElencoModalitaPagamentoSoggetto(int idEnte, String codiceSoggetto) {
		List<Map<String, Object>> mdpMap = siacTModpagDao.findModalitaPagamentoSoggetto(idEnte, codiceSoggetto);

		@SuppressWarnings("unchecked")
		List<SiacTModpagWrapper> elencoModalitaPagamentoSoggetto = mapMapListToWrapperList(mdpMap, SiacTModpagWrapper.class, ModalitaPagamentoSoggettoMapper.class);

		return elencoModalitaPagamentoSoggetto;
	}

	public List<String> readElencoIbanModalitaPagamentoSoggetto(int idEnte, String codiceSoggetto) {
		return siacTSoggettoRepository.findIbanModpagSoggetto(idEnte, codiceSoggetto);
	}

	
}

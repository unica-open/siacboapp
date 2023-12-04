/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.csi.siac.siacbilser.model.movimentogestione.TipoMovimentoGestione;
import it.csi.siac.siacboapp.frontend.ui.model.common.CodiceAttributo;
import it.csi.siac.siacboapp.frontend.ui.model.common.CriteriRicercaMovimentiGestione;
import it.csi.siac.siacboapp.integration.dad.mapper.SiacTMovgestSacMapper;
import it.csi.siac.siacboapp.integration.dao.movimentigestione.SiacTMovgestDao;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgestT;
import it.csi.siac.siacboapp.integration.repository.SiacTClassRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTMovgestRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;
import it.csi.siac.siaccommonser.business.service.base.exception.BusinessException;
import it.csi.siac.siaccorser.model.errore.ErroreCore;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public abstract class MovimentoGestioneDad extends BoBaseDad {

	@Autowired
	protected SiacTMovgestDao siacTMovgestDao;
	
	@Autowired
	protected SiacTMovgestRepository siacTMovgestRepository;
	
	@Autowired
	private SiacTMovgestSacMapper siacTMovgestSacMapper;
	
	@Autowired
	private SiacTClassRepository siacTClassRepository;
	
	protected List<SiacTMovgest> findMovgestByTipoAnnoNumero(int idEnte, int idBilancio, TipoMovimentoGestione tipoMovimentoGestione, CriteriRicercaMovimentiGestione criteri) {
		return siacTMovgestRepository.findByTipoAnnoNumero(idEnte, idBilancio, tipoMovimentoGestione.getCodice(), criteri.getAnno(),
				criteri.getNumero());
	}

	public void aggiornaSoggettoCollegato(int idEnte, Integer idMovgest, Integer idSoggetto, String loginOperazione) {
		siacTMovgestRepository.eliminaSoggettoCollegato(idMovgest, loginOperazione);

		if (idSoggetto != null) {
			siacTMovgestDao.insertSiacRMovgestTsSog(idEnte, idMovgest, idSoggetto, loginOperazione);
		}
	}
	
	public void aggiornaClasseSoggettoCollegata(int idEnte, Integer idMovgest, Integer idClasseSoggetto, String loginOperazione) {
		siacTMovgestRepository.eliminaClasseSoggettoCollegata(idMovgest, loginOperazione);
	
		if (idClasseSoggetto != null) {
			siacTMovgestDao.insertSiacRMovgestTsSogClasse(idEnte, idMovgest, idClasseSoggetto, loginOperazione);
		}
	}
	
	public void modificaStatoOperativo(Integer idMovgest, String codiceStato, String loginOperazione) {
		siacTMovgestRepository.modificaStatoOperativo(idMovgest, codiceStato, loginOperazione);
	}
	
	public void modificaAttributo(int idEnte, Integer idMovgest, CodiceAttributo codiceAttributo, Boolean valoreBoolean, String valoreTesto, String loginOperazione) {
		siacTMovgestRepository.deleteSiacRMovgestTsAttr(idMovgest, codiceAttributo.name());
		
		if (valoreBoolean == null && valoreTesto == null) {
			return;
		}
		
		siacTMovgestDao.insertSiacRMovgestTsAttr(
				idEnte, 
				idMovgest, 
				codiceAttributo.name(), 
				valoreBoolean == null ? null : valoreBoolean ? "S" : "N", 
				valoreTesto, 
				loginOperazione);
	}
	
	public void modificaSac(Integer idMovGest, SiacTClassWrapper sac, String loginOperazione) {
		
		SiacTMovgest siacTMovgest = siacTMovgestRepository.findOne(idMovGest);
		
		SiacTMovgestT testata = siacTMovgest.getTestata();
		
		SiacTClassWrapper siacTClassWrapper = siacTMovgestSacMapper.getSac(testata.getClassi());
		
		if (!siacTClassWrapper.getCodice().equalsIgnoreCase(sac.getCodice())) {

			Integer idEnte = testata.getEnteProprietario().getUid();
			Integer classifId = getSiacTClassUid(idEnte, sac.getCodice());
			
			siacTMovgestRepository.eliminaSac(testata.getUid(), siacTClassWrapper.getUid(), loginOperazione);
			siacTMovgestDao.insertSiacRMovgestClass(idEnte, testata.getUid(), classifId, loginOperazione);
		}
	
	}

	private Integer getSiacTClassUid(Integer idEnte, String codiceSac) {
		Integer classifId = siacTClassRepository.findSiacTClassUidByCodiceCDC(idEnte, codiceSac);
		
		if (classifId == null) {
			throw new BusinessException(ErroreCore.ENTITA_INESISTENTE.getErrore("SAC", codiceSac));
		}
		
		return classifId;
	}
}
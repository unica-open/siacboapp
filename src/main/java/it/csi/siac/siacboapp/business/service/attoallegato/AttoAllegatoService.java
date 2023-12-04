/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.attoallegato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacbilser.model.StatoOperativoMovimentoGestione;
import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.frontend.ui.model.common.CodiceAttributo;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.CriteriRicercaImpegni;
import it.csi.siac.siacboapp.integration.dad.ImpegnoDad;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class AttoAllegatoService extends BoService {

	@Autowired
	private ImpegnoDad impegnoDad;

	public ImpegnoWrapper cercaImpegno(int idEnte, int idBilancio, CriteriRicercaImpegni criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {
		return impegnoDad.cercaImpegno(idEnte, idBilancio, criteri, mapperTypes);
	}

	public void aggiornaSoggettoClasseSoggettoCollegati(int idEnte, ImpegnoWrapper impegno, String codiceInc) {
		Integer idImpegno = impegno.getUid();
		Integer idSoggetto = impegno.getSoggetto().getUid();
		Integer idClasseSoggetto = impegno.getClasseSoggetto() == null ? null : impegno.getClasseSoggetto().getUid();
		
		impegnoDad.aggiornaSoggettoCollegato(idEnte, idImpegno, idSoggetto, codiceInc);
		impegnoDad.aggiornaClasseSoggettoCollegata(idEnte, idImpegno, idClasseSoggetto, codiceInc);
		
		StatoOperativoMovimentoGestione statoOperativo = 
				idSoggetto != null || idClasseSoggetto != null ?  
						StatoOperativoMovimentoGestione.DEFINITIVO : StatoOperativoMovimentoGestione.DEFINITIVO_NON_LIQUIDABILE;
		
		impegnoDad.modificaStatoOperativo(idImpegno, statoOperativo.getCodice(), codiceInc);
	}

	public void aggiornaParereFinanziario(Integer idMovgest, Boolean parereFinanziario, String loginOperazione) {
		impegnoDad.modificaParereFinanziario(idMovgest, parereFinanziario, loginOperazione);
	}

	public void aggiornaFlagSdf(int idEnte, Integer idMovgest, Boolean flagSdf, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idMovgest, CodiceAttributo.flagSDF, flagSdf, null, loginOperazione);
	}

	public void aggiornaFlagSoggettoDurc(int idEnte, Integer idMovgest, Boolean flagSoggettoDurc, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idMovgest, CodiceAttributo.flagSoggettoDurc, flagSoggettoDurc, null, loginOperazione);
	}

	//SIAC-7884
	public void aggiornaFlagPrenotazione(int idEnte, Integer idMovgest, Boolean flagPrenotazione, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idMovgest, CodiceAttributo.flagPrenotazione, flagPrenotazione, null, loginOperazione);
	}

	public void aggiornaFlagPrenotazioneLiquidabile(int idEnte, Integer idMovgest, Boolean flagPrenotazioneLiquidabile, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idMovgest, CodiceAttributo.flagPrenotazioneLiquidabile, flagPrenotazioneLiquidabile, null, loginOperazione);
	}

	public void aggiornaCup(int idEnte, Integer idMovgest, String cup, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idMovgest, CodiceAttributo.cup, null, cup, loginOperazione);
	}

	public void aggiornaImpegnoConBloccoRagioneria(int idEnte, ImpegnoWrapper impegno, String loginOperazione) {
		aggiornaParereFinanziario(impegno.getUid(), impegno.getParereFinanziario(), loginOperazione);		
		aggiornaFlagSdf(idEnte, impegno.getUid(), impegno.getFlagSdf(), loginOperazione);
		aggiornaFlagSoggettoDurc(idEnte, impegno.getUid(), impegno.getFlagSoggettoDurc(), loginOperazione);

		//SIAC-7884
		aggiornaFlagPrenotazione(idEnte, impegno.getUid(), impegno.getFlagPrenotazione(), loginOperazione);
		aggiornaFlagPrenotazioneLiquidabile(idEnte, impegno.getUid(), impegno.getFlagPrenotazioneLiquidabile(), loginOperazione);
		aggiornaCup(idEnte, impegno.getUid(), impegno.getCup(), loginOperazione);
	}
}

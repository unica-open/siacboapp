/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.impegni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.MovimentoGestioneService;
import it.csi.siac.siacboapp.frontend.ui.model.common.CodiceAttributo;
import it.csi.siac.siacboapp.frontend.ui.model.impegni.CriteriRicercaImpegni;
import it.csi.siac.siacboapp.integration.dad.ImpegnoDad;
import it.csi.siac.siacboapp.integration.dad.MovimentoGestioneDad;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.util.entitywrapper.ImpegnoWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTClassWrapper;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class ImpegnoService extends MovimentoGestioneService {

	@Autowired
	private ImpegnoDad impegnoDad;

	@Override
	protected MovimentoGestioneDad getMovimentoGestioneDad() {
		return impegnoDad;
	}
	
	public ImpegnoWrapper cercaImpegno(int idEnte, int idBilancio, CriteriRicercaImpegni criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {
		return impegnoDad.cercaImpegno(idEnte, idBilancio, criteri, mapperTypes);
	}

	public void aggiornaSoggettoClasseSoggettoCollegati(int idEnte, ImpegnoWrapper impegno, String codiceInc) {
		aggiornaSoggettoClasseSoggettoCollegati(
				idEnte, 
				codiceInc, 
				impegno.getUid(), 
				impegno.getSoggetto().getUid(), 
				impegno.getClasseSoggetto() == null ? null : impegno.getClasseSoggetto().getUid()
		);
	}

	public void aggiornaParereFinanziario(Integer idImpegno, Boolean parereFinanziario, String loginOperazione) {
		impegnoDad.modificaParereFinanziario(idImpegno, parereFinanziario, loginOperazione);
	}

	public void aggiornaFlagSdf(int idEnte, Integer idImpegno, Boolean flagSdf, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idImpegno, CodiceAttributo.flagSDF, flagSdf, null, loginOperazione);
	}

	public void aggiornaFlagSoggettoDurc(int idEnte, Integer idImpegno, Boolean flagSoggettoDurc, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idImpegno, CodiceAttributo.flagSoggettoDurc, flagSoggettoDurc, null, loginOperazione);
	}

	//SIAC-7884
	public void aggiornaFlagPrenotazione(int idEnte, Integer idImpegno, Boolean flagPrenotazione, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idImpegno, CodiceAttributo.flagPrenotazione, flagPrenotazione, null, loginOperazione);
	}

	public void aggiornaFlagPrenotazioneLiquidabile(int idEnte, Integer idImpegno, Boolean flagPrenotazioneLiquidabile, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idImpegno, CodiceAttributo.flagPrenotazioneLiquidabile, flagPrenotazioneLiquidabile, null, loginOperazione);
	}

	public void aggiornaCup(int idEnte, Integer idImpegno, String cup, String loginOperazione) {
		impegnoDad.modificaAttributo(idEnte, idImpegno, CodiceAttributo.cup, null, cup, loginOperazione);
	}

	public void aggiornaSac(Integer idImpegno, SiacTClassWrapper sac, String loginOperazione) {
		impegnoDad.modificaSac(idImpegno, sac, loginOperazione);
	}

	public void aggiornaImpegnoConBloccoRagioneria(int idEnte, ImpegnoWrapper impegno, String loginOperazione) {
		aggiornaParereFinanziario(impegno.getUid(), impegno.getParereFinanziario(), loginOperazione);		
		aggiornaFlagSdf(idEnte, impegno.getUid(), impegno.getFlagSdf(), loginOperazione);
		aggiornaFlagSoggettoDurc(idEnte, impegno.getUid(), impegno.getFlagSoggettoDurc(), loginOperazione);

		//SIAC-7884
		aggiornaFlagPrenotazione(idEnte, impegno.getUid(), impegno.getFlagPrenotazione(), loginOperazione);
		aggiornaFlagPrenotazioneLiquidabile(idEnte, impegno.getUid(), impegno.getFlagPrenotazioneLiquidabile(), loginOperazione);
		aggiornaCup(idEnte, impegno.getUid(), impegno.getCup(), loginOperazione);

		// SIAC-8566
		aggiornaSac(impegno.getUid(), impegno.getStrutturaAmministrativoContabile(), loginOperazione);
	}

}

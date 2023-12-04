/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.documenti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.frontend.ui.model.documenti.CriteriRicercaDocumenti;
import it.csi.siac.siacboapp.integration.dad.DocumentoDad;
import it.csi.siac.siacboapp.integration.entity.SiacDDocTipo;
import it.csi.siac.siacboapp.integration.repository.SiacDDocTipoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTDocWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSubdocWrapper;
import it.csi.siac.siaccommonser.util.misc.TimeoutValue;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class DocumentoService extends BoService {

	@Autowired
	private SiacDDocTipoRepository siacDDocTipoRepository;

	@Autowired
	private DocumentoDad documentoDad;

	public List<SiacDDocTipo> readElencoTipiDocumento(int idEnte, String codiceTipoFamigliaDocumenti) {
		return siacDDocTipoRepository.getElencoTipiDocumento(idEnte, codiceTipoFamigliaDocumenti);
	}

	public SiacTDocWrapper cercaDocumento(int idEnte, CriteriRicercaDocumenti criteri) {
		return documentoDad.cercaDocumento(idEnte, criteri);
	}

	@Transactional(timeout = TimeoutValue.INTERVAL_5_MIN)
	public void annullaAttivazioniContabili(int idEnte, SiacTDocWrapper documento, String codiceInc) {
		documentoDad.annullaAttivazioniContabili(idEnte, documento, codiceInc);
	}

	public List<SiacTSubdocWrapper> cercaQuoteAttoAmministrativoSoggetto(int idEnte, Integer idAttoAmministrativo, Integer idSac, String codiceSoggetto) {
		return documentoDad.cercaQuoteAttoAmministrativoSoggetto(idEnte, idAttoAmministrativo, idSac, codiceSoggetto);
	}

	public void modificaModalitaPagamentoAttoAmministrativo(int idEnte, int idBilancio, Integer idAttoAmministrativo, String codiceSoggetto, Integer idModalitaPagamentoSoggetto, String codiceInc) {
		documentoDad.modificaModalitaPagamentoAttoAmministrativo(idEnte, idBilancio, idAttoAmministrativo, codiceSoggetto, idModalitaPagamentoSoggetto, codiceInc);
	}
}

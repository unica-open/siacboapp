/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.limiteimpegnabile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.frontend.ui.model.limiteimpegnabile.CriteriRicercaLimiteImpegnabile;
import it.csi.siac.siacboapp.integration.dao.limiteimpegnabile.SiacTBilElemDao;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class LimiteImpegnabileService extends BoService {

	@Autowired
	private SiacTBilElemDao siacTBilElemDao;

	public List<SiacTBilElem> ricercaCapitoliLimiteImpegnabile(int idEnte, CriteriRicercaLimiteImpegnabile criteri) {
		return siacTBilElemDao.readElencoCapitoliLimiteImpegnabile(idEnte,
				criteri.getAnno(),
				criteri.getNumeroCapitolo(),
				criteri.getNumeroArticolo(),
				criteri.getNumeroUeb(),
				criteri.getIdStruttureAmministrativoContabili());

	}

}

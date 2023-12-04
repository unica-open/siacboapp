/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.test.business.service.common;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.ordinativi.OrdinativoService;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.test.BaseJunit4TestCase;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;

public class OrdinativoServiceTest extends BaseJunit4TestCase {

	@Autowired OrdinativoService ordinativoService;
	
	@Test
	public void testRicercaOrdinativo() {
		
		int idEnte = 2;
		int numero = 6253;
		
		CriteriRicercaOrdinativi criteri = new CriteriRicercaOrdinativi();
		criteri.setAnno(2020);
		criteri.setCodiceTipo("I");
		criteri.setNumeroDa(numero);
		criteri.setNumeroA(numero);
		
		List<SiacTOrdinativoWrapper> listaOrdinativiResponse = ordinativoService.ricercaOrdinativi(idEnte, criteri);
		
		assertNotNull(listaOrdinativiResponse);
		assertFalse(listaOrdinativiResponse.isEmpty());
	}
	
}

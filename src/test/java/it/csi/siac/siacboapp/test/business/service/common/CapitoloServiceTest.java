/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.test.business.service.common;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.siac.siacboapp.business.service.common.CapitoloService;
import it.csi.siac.siacboapp.test.BaseJunit4TestCase;

/**
 * The Class CassaEconomaleVTTest.
 */
public class CapitoloServiceTest extends BaseJunit4TestCase {

	/** The documento entrata service. */
	@Autowired
	private CapitoloService capitoloService;
	
	@Test
	public void readImportiMovimentiGestione() {
		final String methodName = "readImportiMovimentiGestione";
		Map<Integer, BigDecimal> res = capitoloService.readImportiMovimentiGestione(94889, Arrays.asList(2016, 2017, 2018));
		log.info(methodName, res);
	}
}

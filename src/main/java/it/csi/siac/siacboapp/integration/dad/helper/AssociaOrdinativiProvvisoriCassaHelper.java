/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacROrdinativoProvCassa;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.integration.entity.SiacTProvCassa;
import it.csi.siac.siacboapp.integration.repository.SiacROrdinativoProvCassaRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTOrdinativoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTProvCassaRepository;

@Component
public class AssociaOrdinativiProvvisoriCassaHelper {

	@Autowired
	protected SiacTOrdinativoRepository siacTOrdinativoRepository;
	
	@Autowired
	protected SiacTProvCassaRepository siacTProvCassaRepository;

	@Autowired
	protected SiacROrdinativoProvCassaRepository siacROrdinativoProvCassaRepository;
	
	public void associaOrdinativiProvvisoriCassa(Integer[] idOrdinativi, Integer[] idProvvisoriCassa, String loginOperazione) {
		
		Date now = new Date();

		List<SiacTOrdinativo> elencoSiacTOrdinativo = getElencoSiacTOrdinativo(idOrdinativi);
		List<SiacTProvCassa> elencoSiacTProvCassa = getElencoSiacTProvCassa(idProvvisoriCassa);
		
		SiacTOrdinativo siacTOrdinativo = null;
		BigDecimal importoOrdinativo = null;
		boolean getNextSiacTOrdinativo = true;
		
		SiacTProvCassa siacTProvCassa = null;
		BigDecimal importoProvvisorioCassa = null;
		boolean getNextSiacTProvCassa = true;
		
		while (getNextSiacTOrdinativo || getNextSiacTProvCassa) {
			SiacROrdinativoProvCassa siacROrdinativoProvCassa;
			
			if (getNextSiacTOrdinativo) {
				siacTOrdinativo = elencoSiacTOrdinativo.remove(0);
				importoOrdinativo = siacTOrdinativoRepository.readImportoOrdinativo(siacTOrdinativo.getUid());
			}
			
			if (getNextSiacTProvCassa) {
				siacTProvCassa = elencoSiacTProvCassa.remove(0); 
				importoProvvisorioCassa = siacTProvCassaRepository.calcolaImportoDaRegolarizzare(siacTProvCassa.getUid());
			}
			
			if (importoOrdinativo.compareTo(importoProvvisorioCassa) > 0) {
				siacROrdinativoProvCassa = buildSiacROrdinativoProvCassa(siacTOrdinativo, siacTProvCassa, importoProvvisorioCassa, now, loginOperazione);
				importoOrdinativo = importoOrdinativo.subtract(importoProvvisorioCassa);
				
				getNextSiacTOrdinativo = false;
				getNextSiacTProvCassa = !elencoSiacTProvCassa.isEmpty();
				
			} else {
				siacROrdinativoProvCassa = buildSiacROrdinativoProvCassa(siacTOrdinativo, siacTProvCassa, importoOrdinativo, now, loginOperazione);
				
				if (importoOrdinativo.compareTo(importoProvvisorioCassa) < 0) {
					importoProvvisorioCassa = importoProvvisorioCassa.subtract(importoOrdinativo);
				}
				
				getNextSiacTOrdinativo = !elencoSiacTOrdinativo.isEmpty();
				getNextSiacTProvCassa = importoProvvisorioCassa.compareTo(BigDecimal.ZERO) == 0 && !elencoSiacTProvCassa.isEmpty();
			} 
			
			siacROrdinativoProvCassaRepository.saveAndFlush(siacROrdinativoProvCassa);
		}
		
		siacTOrdinativoRepository.setDaTrasmettere(Arrays.asList(idOrdinativi), true);
	}
	
		

	private SiacROrdinativoProvCassa buildSiacROrdinativoProvCassa(SiacTOrdinativo siacTOrdinativo,
			SiacTProvCassa siacTProvCassa, BigDecimal importo, Date date, String loginOperazione) {
		SiacROrdinativoProvCassa opc = new SiacROrdinativoProvCassa();

		opc.setOrdinativo(siacTOrdinativo);
		opc.setProvvisorioCassa(siacTProvCassa);
		opc.setImporto(importo);
		
		opc.setEnteProprietario(siacTOrdinativo.getEnteProprietario());
		opc.setDataCreazione(date);
		opc.setDataModifica(date);
		opc.setDataInizioValidita(date);
		opc.setLoginOperazione(loginOperazione);

		return opc;
	}


	private List<SiacTOrdinativo> getElencoSiacTOrdinativo(Integer[] idOrdinativi) {
		return siacTOrdinativoRepository.findByIds(Arrays.asList(idOrdinativi));
	}

	private List<SiacTProvCassa> getElencoSiacTProvCassa(Integer[] idProvvisoriCassa) {
		return siacTProvCassaRepository.findByIds(Arrays.asList(idProvvisoriCassa));
	}
}

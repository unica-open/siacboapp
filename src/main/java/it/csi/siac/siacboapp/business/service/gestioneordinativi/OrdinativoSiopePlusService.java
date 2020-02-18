/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.gestioneordinativi;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.frontend.ui.model.gestioneordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapping;
import it.csi.siac.siacboapp.util.ParamStringMapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siacfin2ser.model.TipoOrdinativo;
import it.csi.siac.siacfinser.frontend.webservice.msg.CountOrdinativiMif;
import it.csi.siac.siacfinser.frontend.webservice.msg.CountOrdinativiMifSiopePlusResponse;
import it.csi.siac.siacfinser.frontend.webservice.msg.GeneraXmlOrdinativiMif;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class OrdinativoSiopePlusService extends BaseOrdinativoService {

	@Override
	public List<SiacTOrdinativoWrapper> ricercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri, EntityWrapperMapping... entityWrapperMappings) {
		 return ordinativoDad.ricercaOrdinativiSiopePlus(idEnte, criteri, entityWrapperMappings);
	}

	@Override
	protected Map<String, String> trasmettiOrdinativiMif(int idEnte, String annoBilancio, String elencoIdOrdinativi,
			TipoOrdinativo tipoOrdinativo, String loginOperazione) throws Exception {
		log.debug("trasmettiOrdinativiMif", "elencoIdOrdinativi: " + elencoIdOrdinativi);

		String retVal;

		switch (tipoOrdinativo) {
		case INCASSO:
			retVal = siacTOrdinativoDao.trasmettiOrdinativiMifEntrataSiopePlus(idEnte, annoBilancio, elencoIdOrdinativi,
					loginOperazione);
			break;
		case PAGAMENTO:
			retVal = siacTOrdinativoDao.trasmettiOrdinativiMifSpesaSiopePlus(idEnte, annoBilancio, elencoIdOrdinativi,
					loginOperazione);
			break;
		default:
			throw new IllegalArgumentException("Tipo ordinativo non valido: " + tipoOrdinativo.name());
		}

		Map<String, String> retValMap = ParamStringMapper.mapParamString(retVal);

		if (Integer.parseInt(retValMap.get("codiceRes")) == -1)
			throw new Exception(retValMap.get("messaggioRes"));

		return retValMap;
	}
	
	@Override
	protected void generaXml(Ente ente, int idElaborazione, String nomeFile, String codiceFile, String codiceTipoOrdinativo,
			int limitOrdinativi, Richiedente richiedente) throws Exception {
		int idEnte = ente.getUid();
		
		Map<Integer, Integer> countOrdinativiPerAnno = readCountOrdinativiMifPerAnnoEsercizio(idElaborazione, richiedente);
		
		for (Entry<Integer, Integer> entry : countOrdinativiPerAnno.entrySet())
		{
			Integer annoEsercizio = entry.getKey();
			Integer countOrdinativi = entry.getValue();
			
			int steps = (countOrdinativi - 1) / limitOrdinativi + 1;

			GeneraXmlOrdinativiMif generaXmlOrdinativiMif = buildRequestGeneraXmlOrdinativiMif(idEnte, idElaborazione, annoEsercizio,
					limitOrdinativi, richiedente);

			for (int i = 0; i < steps; i++) {
				String xml = generaXmlOrdinativiMif(generaXmlOrdinativiMif, i * limitOrdinativi);

				uploadFile(xml, String.format("%s.%d.%s.xml", nomeFile, annoEsercizio, StringUtils.leftPad(String.valueOf(i + 1), 5, '0')),
						codiceFile, codiceTipoOrdinativo, ente, richiedente);
			}
		}
	}

	@Override
	protected void chiudiElaborazione(int idEnte, String annoBilancio, int idElaborazione, TipoOrdinativo tipoOrdinativo,
			String loginOperazione) throws Exception {
		String retVal;
		
		switch (tipoOrdinativo) {
		case INCASSO:
			retVal = siacTOrdinativoDao.chiudiElaborazioneOrdinativiMifEntrataSiopePlus(idEnte, annoBilancio, idElaborazione,
					loginOperazione);
			break;
		case PAGAMENTO:
			retVal = siacTOrdinativoDao.chiudiElaborazioneOrdinativiMifSpesaSiopePlus(idEnte, annoBilancio, idElaborazione,
					loginOperazione);
			break;
		default:
			throw new IllegalArgumentException("Tipo ordinativo non valido: " + tipoOrdinativo.name());
		}
	
		Map<String, String> retValMap = ParamStringMapper.mapParamString(retVal);

		if (Integer.parseInt(retValMap.get("codiceRes")) == -1)
			throw new Exception(retValMap.get("messaggioRes"));
	}
	
	private GeneraXmlOrdinativiMif buildRequestGeneraXmlOrdinativiMif(int idEnte, int idElaborazione, int annoEsercizio,
			int limitOrdinativi, Richiedente richiedente) {
		GeneraXmlOrdinativiMif generaXmlOrdinativiMif = new GeneraXmlOrdinativiMif();

		generaXmlOrdinativiMif.setIdEnte(idEnte);
		generaXmlOrdinativiMif.setIdElaborazione(idElaborazione);
		generaXmlOrdinativiMif.setAnnoEsercizio(annoEsercizio);
		generaXmlOrdinativiMif.setLimitOrdinativi(limitOrdinativi);
		generaXmlOrdinativiMif.setRichiedente(richiedente);

		return generaXmlOrdinativiMif;
	}

	
	private Map<Integer, Integer> readCountOrdinativiMifPerAnnoEsercizio(int idElaborazione, Richiedente richiedente) throws Exception {
		CountOrdinativiMif countOrdinativiMif = new CountOrdinativiMif();

		countOrdinativiMif.setIdElaborazione(idElaborazione);
		countOrdinativiMif.setRichiedente(richiedente);

		CountOrdinativiMifSiopePlusResponse countOrdinativiMifResponse = oilService.countOrdinativiMifSiopePlus(countOrdinativiMif);

		checkServiceResponse(countOrdinativiMifResponse);

		return countOrdinativiMifResponse.getNumeroOrdinativiPerAnnoEsercizio();
	}

}

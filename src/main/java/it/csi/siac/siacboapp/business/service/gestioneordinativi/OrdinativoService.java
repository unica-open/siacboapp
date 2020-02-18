/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.gestioneordinativi;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.util.ParamStringMapper;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siacfin2ser.model.TipoOrdinativo;
import it.csi.siac.siacfinser.frontend.webservice.msg.CountOrdinativiMif;
import it.csi.siac.siacfinser.frontend.webservice.msg.CountOrdinativiMifResponse;
import it.csi.siac.siacfinser.frontend.webservice.msg.GeneraXmlOrdinativiMif;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class OrdinativoService extends BaseOrdinativoService {

	@Override
	protected Map<String, String> trasmettiOrdinativiMif(int idEnte, String annoBilancio, String elencoIdOrdinativi,
			TipoOrdinativo tipoOrdinativo, String loginOperazione) throws Exception {
		log.debug("trasmettiOrdinativiMif", "elencoIdOrdinativi: " + elencoIdOrdinativi);

		String retVal;

		switch (tipoOrdinativo) {
		case INCASSO: 
			retVal = siacTOrdinativoDao.trasmettiOrdinativiMifEntrata(idEnte, annoBilancio, elencoIdOrdinativi,
					loginOperazione);
			break;
		case PAGAMENTO:
			retVal = siacTOrdinativoDao.trasmettiOrdinativiMifSpesa(idEnte, annoBilancio, elencoIdOrdinativi,
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

		int countOrdinativi = readCountOrdinativiMif(idElaborazione, richiedente);

		int steps = (countOrdinativi - 1) / limitOrdinativi + 1;

		GeneraXmlOrdinativiMif generaXmlOrdinativiMif = buildRequestGeneraXmlOrdinativiMif(idEnte, idElaborazione,
				limitOrdinativi, richiedente);

		for (int i = 0; i < steps; i++)
		{
			String xml = generaXmlOrdinativiMif(generaXmlOrdinativiMif, i * limitOrdinativi);

			uploadFile(xml, String.format("%s.%s.xml", nomeFile, StringUtils.leftPad(String.valueOf(i + 1), 5, '0')),
					codiceFile, codiceTipoOrdinativo, ente, richiedente);
		}
	}

	@Override
	protected void chiudiElaborazione(int idEnte, String annoBilancio, int idElaborazione, TipoOrdinativo tipoOrdinativo,
			String loginOperazione) throws Exception {
		String retVal;
		
		switch (tipoOrdinativo) {
		case INCASSO:
			retVal = siacTOrdinativoDao.chiudiElaborazioneOrdinativiMifEntrata(idEnte, annoBilancio, idElaborazione,
					loginOperazione);
			break;
		case PAGAMENTO:
			retVal = siacTOrdinativoDao.chiudiElaborazioneOrdinativiMifSpesa(idEnte, annoBilancio, idElaborazione,
					loginOperazione);
			break;
		default:
			throw new IllegalArgumentException("Tipo ordinativo non valido: " + tipoOrdinativo.name());
		}
	
		Map<String, String> retValMap = ParamStringMapper.mapParamString(retVal);

		if (Integer.parseInt(retValMap.get("codiceRes")) == -1)
			throw new Exception(retValMap.get("messaggioRes"));
	}

	
	private GeneraXmlOrdinativiMif buildRequestGeneraXmlOrdinativiMif(int idEnte, int idElaborazione,
			int limitOrdinativi, Richiedente richiedente)
	{
		GeneraXmlOrdinativiMif generaXmlOrdinativiMif = new GeneraXmlOrdinativiMif();

		generaXmlOrdinativiMif.setIdEnte(idEnte);
		generaXmlOrdinativiMif.setIdElaborazione(idElaborazione);
		generaXmlOrdinativiMif.setLimitOrdinativi(limitOrdinativi);
		generaXmlOrdinativiMif.setRichiedente(richiedente);

		return generaXmlOrdinativiMif;
	}
	
	private int readCountOrdinativiMif(int idElaborazione, Richiedente richiedente) throws Exception
	{
		CountOrdinativiMif countOrdinativiMif = new CountOrdinativiMif();

		countOrdinativiMif.setIdElaborazione(idElaborazione);
		countOrdinativiMif.setRichiedente(richiedente);

		CountOrdinativiMifResponse countOrdinativiMifResponse = oilService.countOrdinativiMif(countOrdinativiMif);

		checkServiceResponse(countOrdinativiMifResponse);

		return countOrdinativiMifResponse.getNumeroOrdinativi();
	}
}

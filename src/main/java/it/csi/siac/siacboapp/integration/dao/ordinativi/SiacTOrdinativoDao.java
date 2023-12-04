/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.ordinativi;

import java.util.Date;
import java.util.List;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDao;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;

public interface SiacTOrdinativoDao extends BoBaseDao<SiacTOrdinativo> { 
	
	List<SiacTOrdinativo> ricercaOrdinativi(int idEnte, String codiceTipo, Integer annoEsercizio,
			List<String> codiciStato, List<String> codiciStatoEsclusi, String codiceFlusso, Integer idCodiceDistinta, Integer numeroDa,
			Integer numeroA, Date dataEmissioneDa, Date dataEmissioneA, Date dataTrasmissioneOilDa,
			Date dataTrasmissioneOilA, Integer dataTrasmissioneOilPresente, 
			//Evolutiva BackofficeGestioneOrdinativi
			Date dataOrdSpostamentoDa, Date dataOrdSpostamentoA, 
			Integer daTrasmettere, 
			SiacTAttoAmmWrapper attoAmministrativo, SiacTSoggettoWrapper soggetto, boolean escludiCollegatiProvvisoriCassa);
	
	List<SiacTOrdinativo> internalRicercaOrdinativo(int idEnte, String codiceTipo, Integer annoEsercizio,Integer numeroDa,
			Integer numeroA, Date dataEmissioneDa, Date dataEmissioneA, Date dataTrasmissioneOilDa,
			Date dataTrasmissioneOilA);
	
	List<SiacTOrdinativo> ricercaOrdinativiSiopePlus(int idEnte, String codiceTipo, Integer annoEsercizio, 
			Integer dataTrasmissioneOilPresente, Integer daTrasmettere, SiacTAttoAmmWrapper attoAmministrativo, 
			SiacTSoggettoWrapper soggetto, boolean escludiCollegatiProvvisoriCassa, 
			Boolean includiVBDaTrasmettere, Boolean includiAnnulliDaTrasmettere);

	List<SiacTOrdinativo> ricercaOrdinativiSiopePlus(int idEnte, String codiceTipo, Integer annoEsercizio,
			List<String> codiciStato, String codiceFlusso, Integer idCodiceDistinta, Integer numeroDa,
			Integer numeroA, Date dataEmissioneDa, Date dataEmissioneA, Date dataTrasmissioneOilDa,
			Date dataTrasmissioneOilA, Integer dataTrasmissioneOilPresente, 
			//Evolutiva BackofficeGestioneOrdinativi
			Date dataOrdSpostamentoDa, Date dataOrdSpostamentoA, 
			Integer daTrasmettere,
			SiacTAttoAmmWrapper attoAmministrativo, SiacTSoggettoWrapper soggetto, boolean escludiCollegatiProvvisoriCassa,  
			Boolean includiVBDaTrasmettere, Boolean includiAnnulliDaTrasmettere);

	Date readDataAnnullamento(Integer idOrdinativo);
	
	String sbloccaOrdinativiMif(int idEnte, String loginOperazione, String codiceTipoOrdinativo,
			String elencoIdOrdinativi);

	String trasmettiOrdinativiMifSpesa(int idEnte, String annoBilancio, String elencoIdOrdinativi,
			String loginOperazione);

	String trasmettiOrdinativiMifEntrata(int idEnte, String annoBilancio, String elencoIdOrdinativi,
			String loginOperazione);

	String chiudiElaborazioneOrdinativiMifEntrata(int idEnte, String annoBilancio, int idElaborazione,
			String loginOperazione);

	String chiudiElaborazioneOrdinativiMifSpesa(int idEnte, String annoBilancio, int idElaborazione,
			String loginOperazione);

	String trasmettiOrdinativiMifSpesaSiopePlus(int idEnte, String annoBilancio, String elencoIdOrdinativi,
			String loginOperazione);

	String trasmettiOrdinativiMifEntrataSiopePlus(int idEnte, String annoBilancio, String elencoIdOrdinativi,
			String loginOperazione);

	String chiudiElaborazioneOrdinativiMifEntrataSiopePlus(int idEnte, String annoBilancio, int idElaborazione,
			String loginOperazione);

	String chiudiElaborazioneOrdinativiMifSpesaSiopePlus(int idEnte, String annoBilancio, int idElaborazione,
			String loginOperazione);

	List<SiacTOrdinativo> ricercaOrdinativiDaFlusso(Integer uidFlusso, String codiceTipoOrdinativo);

	List<SiacTOrdinativo> ricercaOrdinativiDaFlusso(Integer uidFlusso, String anno, String codiceTipoOrdinativo);

	List<String> leggiCodiciFlussoOrdinativi(int idEnte, int idBilancio, String codiceTipoOrdinativo);
	
	void updateDataSpostamento(String elencoIdOrdinativi, String loginOperazione);
	
	String modificaPianoDeiContiOrdinativoBackoffice(String numeroRemedy, Integer idEnte, String tipoOrdinativo,
			String annoBilancio, Integer numeroOrdinativo, String eventoCode, String tipoEventoCode, String pdcCodice, 
			int aggiornaAccertamento, int aggiornaGenerale, int aggiornaGeneraleGSA, 
			int inserisciGenerale, int inserisciGeneraleGSA);

//	Integer aggiornaContabilitaGenerale(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
//	
//	Integer inserisiciContabilitaGenerale(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
//	
//	Integer annullaPrimaNotaValida(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
//
//	Integer inserisciPrimaNotaAnnullata(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
//
//	Integer invalidaRegistriDiversiDaStato(ModificaPianoDeiContiOrdinativo request, boolean isGSA, String codiceStato);
//
//	Integer associaRegistroAStatoCode(ModificaPianoDeiContiOrdinativo request, boolean isGSA, String codiceStato);
//
//	Integer inserisciRegistro(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
//
//	Integer associaRegistroAStatoNotificato(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
//	
//	Integer associaRegistroNotificatoAdEvento(ModificaPianoDeiContiOrdinativo request, boolean isGSA);
}

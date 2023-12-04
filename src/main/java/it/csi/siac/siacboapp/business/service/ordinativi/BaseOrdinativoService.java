/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.ordinativi;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.business.service.base.BoService;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaFlussi;
import it.csi.siac.siacboapp.frontend.ui.model.ordinativi.CriteriRicercaOrdinativi;
import it.csi.siac.siacboapp.integration.dad.OrdinativoDad;
import it.csi.siac.siacboapp.integration.dad.mapper.base.EntityWrapperMapper;
import it.csi.siac.siacboapp.integration.dao.ordinativi.SiacTOrdinativoDao;
import it.csi.siac.siacboapp.integration.entity.MifTFlussoElaborato;
import it.csi.siac.siacboapp.integration.entity.SiacDDistinta;
import it.csi.siac.siacboapp.integration.entity.SiacDGestioneLivello;
import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoStato;
import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoTipo;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.integration.repository.MifTFlussoElaboratoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacDDistintaRepository;
import it.csi.siac.siacboapp.integration.repository.SiacDGestioneLivelloRepository;
import it.csi.siac.siacboapp.integration.repository.SiacDOrdinativoStatoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacDOrdinativoTipoRepository;
import it.csi.siac.siacboapp.integration.repository.SiacTOrdinativoRepository;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTOrdinativoWrapper;
import it.csi.siac.siaccommon.util.MimeType;
import it.csi.siac.siaccorser.frontend.webservice.FileService;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFile;
import it.csi.siac.siaccorser.frontend.webservice.msg.file.UploadFileResponse;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Richiedente;
import it.csi.siac.siaccorser.model.TipologiaGestioneLivelli;
import it.csi.siac.siaccorser.model.file.File;
import it.csi.siac.siaccorser.model.file.StatoFile.CodiceStatoFile;
import it.csi.siac.siacfin2ser.model.TipoOrdinativo;
import it.csi.siac.siacfinser.frontend.webservice.OilService;
import it.csi.siac.siacfinser.frontend.webservice.msg.GeneraXmlOrdinativiMif;
import it.csi.siac.siacfinser.frontend.webservice.msg.GeneraXmlOrdinativiMifResponse;

public abstract class BaseOrdinativoService extends BoService {
	
	@Autowired
	private SiacDOrdinativoStatoRepository siacDOrdinativoStatoRepository;

	@Autowired
	private MifTFlussoElaboratoRepository mifTFlussoElaboratoRepository;

	@Autowired
	private SiacDOrdinativoTipoRepository siacDOrdinativoTipoRepository;

	@Autowired
	private SiacDGestioneLivelloRepository siacDGestioneLivelloRepository;

	@Autowired
	private SiacTOrdinativoRepository siacTOrdinativoRepository;

	@Autowired
	private SiacDDistintaRepository siacDDistintaRepository;

	@Autowired
	protected OilService oilService;

	@Autowired
	private FileService fileService;

	@Autowired
	protected SiacTOrdinativoDao siacTOrdinativoDao;
	
	@Autowired
	protected OrdinativoDad ordinativoDad;
	
	private static final Map<String, String> TIPO_FILE_BY_TIPO_ORDINATIVO = new HashMap<String, String>();
	protected static final int LIMIT_ORDINATIVI = 500;
	
	static {
		TIPO_FILE_BY_TIPO_ORDINATIVO.put("I", "FLUSSO_OIL_ENTRATE");
		TIPO_FILE_BY_TIPO_ORDINATIVO.put("P", "FLUSSO_OIL_SPESE");
	}

	public List<SiacDOrdinativoStato> readElencoStatiOrdinativo(int idEnte) {
		return siacDOrdinativoStatoRepository.getElencoStatiOrdinativo(idEnte);
	}

	public List<SiacDOrdinativoTipo> readElencoTipiOrdinativo(int idEnte) {
		return siacDOrdinativoTipoRepository.getElencoTipiOrdinativo(idEnte);
	}

	public List<SiacTOrdinativoWrapper> ricercaOrdinativi(int idEnte, CriteriRicercaOrdinativi criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {
		 return ordinativoDad.ricercaOrdinativi(idEnte, criteri, mapperTypes);
	}

	//SIAC-7639
	public List<SiacTOrdinativoWrapper> ricercaOrdinativo(int idEnte, CriteriRicercaOrdinativi criteri, Class<? extends EntityWrapperMapper>... mapperTypes) {
		return ordinativoDad.ricercaOrdinativo(idEnte, criteri, mapperTypes);
	}

	public void trasmettiOrdinativiEGeneraXml(Ente ente, int annoBilancio, Integer[] elencoIdOrdinativi,
			String codiceTipoOrdinativo, Richiedente richiedente) throws Exception {

		TipoOrdinativo tipoOrdinativo = TipoOrdinativo.fromCodice(codiceTipoOrdinativo);

		Map<String, String> retValMap = trasmettiOrdinativiMif(ente.getUid(), String.valueOf(annoBilancio),
				joinElencoIdOrdinativiStr(elencoIdOrdinativi), tipoOrdinativo,
				richiedente.getOperatore().getCodiceFiscale());

		int idElaborazione = Integer.parseInt(retValMap.get("flussoElabMifId"));

		if (Boolean.parseBoolean(retValMap.get("generaXml")))
			generaXml(ente, idElaborazione, retValMap.get("nomeFileMif"), retValMap.get("flussoElabMifDistOilId"),
					codiceTipoOrdinativo, LIMIT_ORDINATIVI, richiedente);

		if (Integer.parseInt(retValMap.get("numOrdTrasm")) > 0)
			chiudiElaborazione(ente.getUid(), String.valueOf(annoBilancio), idElaborazione, tipoOrdinativo,
					richiedente.getOperatore().getCodiceFiscale());
	}

	protected abstract Map<String, String> trasmettiOrdinativiMif(int uid, String valueOf, String joinElencoIdOrdinativiStr,
			TipoOrdinativo tipoOrdinativo, String codiceFiscale) throws Exception;

	protected abstract void generaXml(Ente ente, int idElaborazione, String nomeFile, String codiceFile, String codiceTipoOrdinativo,
			int limitOrdinativi, Richiedente richiedente) throws Exception;
	
	protected abstract void chiudiElaborazione(int idEnte, String annoBilancio, int idElaborazione, TipoOrdinativo tipoOrdinativo,
			String loginOperazione) throws Exception;
	
	public void sbloccaOrdinativi(int idEnte, Integer[] elencoIdOrdinativi, String codiceTipoOrdinativo,
			String loginOperazione) throws Exception {
		String elencoIdOrdinativiStr = joinElencoIdOrdinativiStr(elencoIdOrdinativi);
		log.debug("sbloccaOrdinativi", "elencoIdOrdinativi: " + Arrays.toString(elencoIdOrdinativi));

		String ret = siacTOrdinativoDao.sbloccaOrdinativiMif(idEnte, loginOperazione, codiceTipoOrdinativo,
				elencoIdOrdinativiStr);
		
		if (ret != null)
			throw new Exception(ret);
	}

	public void preparaVB(int uid, Integer[] elencoIdOrdinativi, String codiceFiscale) throws Exception {
		siacTOrdinativoDao.updateDataSpostamento(joinElencoIdOrdinativiStr(elencoIdOrdinativi), codiceFiscale);
	}

	protected String joinElencoIdOrdinativiStr(Integer[] elencoIdOrdinativi) {
		return StringUtils.join(elencoIdOrdinativi, ", ");
	}

	protected void uploadFile(String xml, String nomeFile, String codiceFile, String codiceTipoOrdinativo, Ente ente,
			Richiedente richiedente) throws Exception {
		UploadFile uploadFile = new UploadFile();

		File file = new File();

		file.setCodice(codiceFile);
		file.setNome(nomeFile);
		file.setCodiceTipo(TIPO_FILE_BY_TIPO_ORDINATIVO.get(codiceTipoOrdinativo));
		file.setMimeType(MimeType.XML);
		file.setContenuto(xml.getBytes());
		file.setStatoFile(CodiceStatoFile.ELABORATO);

		uploadFile.setFile(file);

		uploadFile.setRichiedente(richiedente);
		uploadFile.setEnte(ente);

		UploadFileResponse uploadFileResponse = fileService.uploadFile(uploadFile);

		checkServiceResponse(uploadFileResponse);
	}

	protected String generaXmlOrdinativiMif(GeneraXmlOrdinativiMif generaXmlOrdinativiMif, Integer offsetOrdinativi)
			throws Exception {
		generaXmlOrdinativiMif.setOffsetOrdinativi(offsetOrdinativi);

		GeneraXmlOrdinativiMifResponse generaXmlOrdinativiMifResponse = oilService
				.generaXmlOrdinativiMif(generaXmlOrdinativiMif);

		checkServiceResponse(generaXmlOrdinativiMifResponse);

		return generaXmlOrdinativiMifResponse.getXml();
	}

	public List<MifTFlussoElaborato> ricercaFlussi(int idEnte, CriteriRicercaFlussi criteri) {
		return mifTFlussoElaboratoRepository.getElencoFlussiElaborati(
				idEnte, 
				getCodiceTipoFlussoElaborato(criteri.getCodiceTipo()), 
				criteri.getAnno(), 
				criteri.getDataInserimentoDa(), 
				DateUtils.ceiling(criteri.getDataInserimentoA(), Calendar.DAY_OF_MONTH), 
				criteri.getNumeroDa(), 
				criteri.getNumeroA());
	}

	private String getCodiceTipoFlussoElaborato(String codiceTipoFlussoElaborato) {
		switch (TipoOrdinativo.fromCodice(codiceTipoFlussoElaborato)) {
		case INCASSO:
			return "REVMIF";
		case PAGAMENTO:
			return "MANDMIF";
		default:
			throw new IllegalArgumentException("Tipo ordinativo non valido: " + codiceTipoFlussoElaborato);
		}
	}

	public List<SiacTOrdinativo> ricercaOrdinativiDaFlusso(Integer uid, String codiceTipoOrdinativi) {
		return siacTOrdinativoDao.ricercaOrdinativiDaFlusso(
				uid != null ? uid : 0, 
				codiceTipoOrdinativi != null ? codiceTipoOrdinativi : "");
	}
	
	//SIAC-7615
	@SuppressWarnings("unchecked")
	public List<SiacTOrdinativoWrapper> ricercaOrdinativiDaFlusso(Integer uid, String anno, String codiceTipoOrdinativi) {
		return ordinativoDad.ricercaOrdinativiDaFlusso(uid, anno, codiceTipoOrdinativi);
	}

	public boolean readSiopePlus(int idEnte)
	{
		SiacDGestioneLivello siacDGestioneLivello = siacDGestioneLivelloRepository.getGestioneLivelloGestioneEnte(idEnte,
				TipologiaGestioneLivelli.ORDINATIVI_MIF_TRASMETTI_SIOPE_PLUS.getCodice());
		  
		return siacDGestioneLivello != null;
	}
	
	public List<String> readElencoCodiciFlussoOrdinativi(int idEnte, int idBilancio, TipoOrdinativo tipoOrdinativo) {
		return siacTOrdinativoDao.leggiCodiciFlussoOrdinativi(idEnte, idBilancio, tipoOrdinativo.getCodice());
	}
	
	public List<SiacDDistinta> readElencoCodiciDistintaOrdinativi(int idEnte, String codiceTipoDistinta) {
		return siacDDistintaRepository.leggiCodiciDistintaOrdinativi(idEnte, codiceTipoDistinta);
	}
	
	@Transactional  
	public void setDaTrasmettere(List<Integer> idOrdinativi, Boolean daTrasmettere) {
		siacTOrdinativoRepository.setDaTrasmettere(idOrdinativi, daTrasmettere);
	}
	
	@Transactional
	public void associaOrdinativiProvvisoriCassa(Integer[] idOrdinativi, Integer[] idProvvisoriCassa, String loginOperazione) {
		ordinativoDad.associaOrdinativiProvvisoriCassa(idOrdinativi, idProvvisoriCassa, loginOperazione);
	}

}

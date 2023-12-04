/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.ordinativi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacROrdinativoStato;
import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTAttoAmmWrapper;
import it.csi.siac.siacboapp.util.entitywrapper.SiacTSoggettoWrapper;
import it.csi.siac.siacfin2ser.model.TipoOrdinativo;

@Component
public  class SiacTOrdinativoDaoImpl extends BoBaseDaoImpl<SiacTOrdinativo> implements SiacTOrdinativoDao {

	@Override
	public List<SiacTOrdinativo> ricercaOrdinativi(int idEnte, String codiceTipo, Integer annoEsercizio,
			List<String> codiciStato, List<String> codiciStatoEsclusi, String codiceFlusso, Integer idCodiceDistinta, Integer numeroDa,
			Integer numeroA, Date dataEmissioneDa, Date dataEmissioneA, Date dataTrasmissioneOilDa,
			Date dataTrasmissioneOilA, Integer dataTrasmissioneOilPresente, 
			Date dataOrdSpostamentoDa, Date dataOrdSpostamentoA, 
			Integer daTrasmettere,
			SiacTAttoAmmWrapper attoAmministrativo, SiacTSoggettoWrapper soggetto, boolean escludiCollegatiProvvisoriCassa) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("idEnte", idEnte);
		params.put("codiceTipo", codiceTipo);
		params.put("annoEsercizio", annoEsercizio);
		
		String tipoOrdinativo = getDescrTipoOrdinativo(codiceTipo);
		
		StringBuilder sql = new StringBuilder("SELECT DISTINCT o FROM SiacTOrdinativo o ")
			.append(" JOIN FETCH o.stati os ")
			.append(" LEFT OUTER JOIN FETCH o.ordinativi").append(tipoOrdinativo).append("Mif om ")
			.append(" WHERE o.enteProprietario.uid=:idEnte ")
			.append(" AND o.tipo.codice=:codiceTipo ")
			.append(" AND o.anno=:annoEsercizio ");

		params.put("dataEmissioneDa", dataEmissioneDa);
		params.put("dataEmissioneA", dataEmissioneA);
		
		sql.append(" AND o.dataEmissione>=:dataEmissioneDa ")
			.append(" AND o.dataEmissione<:dataEmissioneA ");
		
		params.put("dataTrasmissioneOilDa", dataTrasmissioneOilDa);
		params.put("dataTrasmissioneOilA", dataTrasmissioneOilA);
		
		sql.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil>=:dataTrasmissioneOilDa) ")
			.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil<:dataTrasmissioneOilA) ");

		//Evolutiva BackofficeGestioneOrdinativi
		if(dataOrdSpostamentoDa != null) { 
			params.put("dataOrdSpostamentoDa", dataOrdSpostamentoDa);
			sql.append(" AND o.dataSpostamento>=:dataOrdSpostamentoDa ");
		}

		if(dataOrdSpostamentoA != null) {
			params.put("dataOrdSpostamentoA", dataOrdSpostamentoA);
			sql.append(" AND o.dataSpostamento<:dataOrdSpostamentoA ");			
		}
		//
		
		if(CollectionUtils.isNotEmpty(codiciStato)) {
			params.put("codiciStato", codiciStato);
			sql.append(" AND os.stato.codice IN (:codiciStato) ");
		}
		
		if(CollectionUtils.isNotEmpty(codiciStatoEsclusi)) {
			params.put("codiciStatoEsclusi", codiciStatoEsclusi);
			sql.append(" AND os.stato.codice NOT IN (:codiciStatoEsclusi) ");
		}

		if (StringUtils.isNotBlank(codiceFlusso)) {
			params.put("codiceFlusso", codiceFlusso);
			sql.append(" AND om.uid=( ")
				.append("		SELECT MAX(om2.uid) FROM MifTOrdinativo").append(tipoOrdinativo).append(" om2 ")
				.append("		WHERE om2.ordinativo=o AND om2.dataCancellazione IS NULL ")
				.append(" 			AND om2.dataInizioValidita <= CURRENT_TIMESTAMP ")
				.append(" 			AND (om2.dataFineValidita IS NULL OR om2.dataFineValidita > CURRENT_TIMESTAMP)")
				.append("			AND om2.codiceFlusso=:codiceFlusso ")
				.append(" ) ");
		}
		
		if (idCodiceDistinta != null) {
			params.put("idCodiceDistinta", idCodiceDistinta);
			sql.append(" AND o.distinta.uid=CAST(CAST(:idCodiceDistinta AS string) AS integer) "); 
		}
		
		if (numeroDa != null) {
			params.put("numeroDa", numeroDa);
			sql.append(" AND o.numero>=CAST(CAST(:numeroDa AS string) AS integer) "); 
		}

		if (numeroA != null) {
			params.put("numeroA", numeroA);
			sql.append(" AND o.numero<=CAST(CAST(:numeroA AS string) AS integer) ");
		}

		appendDataTrasmissioneOilPresenteClauses(dataTrasmissioneOilPresente, sql);
		appendDaTrasmettereClauses(daTrasmettere, sql);
		appendAttoAmministrativoClauses(attoAmministrativo, sql, params); 
		appendSoggettoClauses(soggetto, sql, params); 
		appendProvvisoriCassaClauses(escludiCollegatiProvvisoriCassa, sql); 
		
		return ricercaOrdinativi(sql, params);
	}

	
	public List<SiacTOrdinativo> internalRicercaOrdinativo(int idEnte, String codiceTipo, Integer annoEsercizio,
			Integer numeroDa, Integer numeroA, Date dataEmissioneDa, Date dataEmissioneA, Date dataTrasmissioneOilDa,
			Date dataTrasmissioneOilA) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("idEnte", idEnte);
		params.put("codiceTipo", codiceTipo);
		params.put("annoEsercizio", annoEsercizio);
		
		String tipoOrdinativo = getDescrTipoOrdinativo(codiceTipo);
		
		StringBuilder sql = new StringBuilder("SELECT DISTINCT o FROM SiacTOrdinativo o ")
			.append(" JOIN FETCH o.stati os ")
			.append(" LEFT OUTER JOIN FETCH o.ordinativi").append(tipoOrdinativo).append("Mif om ")
			.append(" WHERE o.enteProprietario.uid=:idEnte ")
			.append(" AND o.tipo.codice=:codiceTipo ")
			.append(" AND o.anno=:annoEsercizio ");

		params.put("dataEmissioneDa", dataEmissioneDa);
		params.put("dataEmissioneA", dataEmissioneA);
		
		sql.append(" AND o.dataEmissione>=:dataEmissioneDa ")
			.append(" AND o.dataEmissione<:dataEmissioneA ");
		
		params.put("dataTrasmissioneOilDa", dataTrasmissioneOilDa);
		params.put("dataTrasmissioneOilA", dataTrasmissioneOilA);
		
		sql.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil>=:dataTrasmissioneOilDa) ")
			.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil<:dataTrasmissioneOilA) ");

		
		if (numeroDa != null) {
			params.put("numeroDa", numeroDa);
			sql.append(" AND o.numero>=CAST(CAST(:numeroDa AS string) AS integer) "); 
		}

		if (numeroA != null) {
			params.put("numeroA", numeroA);
			sql.append(" AND o.numero<=CAST(CAST(:numeroA AS string) AS integer) ");
		}
		
		return ricercaOrdinativi(sql, params);
	}
	

	@Override
	public List<SiacTOrdinativo> ricercaOrdinativiSiopePlus(int idEnte, String codiceTipo, Integer annoEsercizio, 
			Integer dataTrasmissioneOilPresente, Integer daTrasmettere, SiacTAttoAmmWrapper attoAmministrativo,
			SiacTSoggettoWrapper soggetto, boolean escludiCollegatiProvvisoriCassa, 
			Boolean includiVBDaTrasmettere, Boolean includiAnnulliDaTrasmettere) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		String tipoOrdinativo = getDescrTipoOrdinativo(codiceTipo);

		params.put("idEnte", idEnte);
		params.put("codiceTipo", codiceTipo);
		params.put("annoEsercizio", annoEsercizio);
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT DISTINCT o FROM SiacTOrdinativo o ")
			.append(" JOIN FETCH o.stati os ")
			.append(" LEFT OUTER JOIN FETCH o.ordinativi").append(tipoOrdinativo).append("Mif om ")

			.append(" WHERE o.enteProprietario.uid=:idEnte ")
			.append(" AND o.tipo.codice=:codiceTipo ")
			.append(" AND o.anno=:annoEsercizio ");
		
		appendDataTrasmissioneOilPresenteClauses(dataTrasmissioneOilPresente, sql);
		appendDaTrasmettereClauses(daTrasmettere, sql);
		appendAttoAmministrativoClauses(attoAmministrativo, sql, params); 
		appendSoggettoClauses(soggetto, sql, params); 
		appendProvvisoriCassaClauses(escludiCollegatiProvvisoriCassa, sql); 

		sql.append(" AND (o.daTrasmettere=TRUE) ");
		
		if (Boolean.TRUE.equals(includiVBDaTrasmettere)) {
			sql.append(" AND o.dataSpostamento IS NOT NULL ")
				.append(" AND o.dataTrasmissioneOil<o.dataSpostamento ");
		}

		if (Boolean.TRUE.equals(includiAnnulliDaTrasmettere)) {
			sql.append(" AND os.stato.codice='A' ")
				.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil<o.dataModifica) ");
		}

		return ricercaOrdinativi(sql, params);
	}

	@Override
	public List<SiacTOrdinativo> ricercaOrdinativiSiopePlus(int idEnte, String codiceTipo, Integer annoEsercizio,
			List<String> codiciStato, String codiceFlusso, Integer idCodiceDistinta, Integer numeroDa,
			Integer numeroA, Date dataEmissioneDa, Date dataEmissioneA, Date dataTrasmissioneOilDa,
			Date dataTrasmissioneOilA, Integer dataTrasmissioneOilPresente, 
			Date dataOrdSpostamentoDa, Date dataOrdSpostamentoA, 
			Integer daTrasmettere,
			SiacTAttoAmmWrapper attoAmministrativo, SiacTSoggettoWrapper soggetto, boolean escludiCollegatiProvvisoriCassa,  
			Boolean includiVBDaTrasmettere, Boolean includiAnnulliDaTrasmettere) {

		Map<String, Object> params = new HashMap<String, Object>();

		String tipoOrdinativo = getDescrTipoOrdinativo(codiceTipo);

		params.put("idEnte", idEnte);
		params.put("codiceTipo", codiceTipo);
		params.put("annoEsercizio", annoEsercizio);
		
		StringBuilder sql = new StringBuilder("SELECT DISTINCT o FROM SiacTOrdinativo o ")
			.append(" JOIN FETCH o.stati os ")
			.append(" LEFT OUTER JOIN FETCH o.ordinativi").append(tipoOrdinativo).append("Mif om ");

		sql.append(" WHERE o.enteProprietario.uid=:idEnte ")
			.append(" AND o.tipo.codice=:codiceTipo ")
			.append(" AND o.anno=:annoEsercizio ")
			.append(" AND ( ");
		
		params.put("dataEmissioneDa", dataEmissioneDa);
		params.put("dataEmissioneA", dataEmissioneA);
		
		sql.append(" o.dataEmissione>=:dataEmissioneDa ")
			.append(" AND o.dataEmissione<:dataEmissioneA ");
		
		params.put("dataTrasmissioneOilDa", dataTrasmissioneOilDa);
		params.put("dataTrasmissioneOilA", dataTrasmissioneOilA);
		
		sql.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil>=:dataTrasmissioneOilDa) ")
			.append(" AND (o.dataTrasmissioneOil IS NULL OR o.dataTrasmissioneOil<:dataTrasmissioneOilA) ");
		
		// Evolutive BackofficeGestioneOrdinativi
		if(dataOrdSpostamentoDa != null 
//				&& dataOrdSpostamentoDa.compareTo(CriteriRicercaOrdinativi.MIN_DATE) > 0
				) { 
			params.put("dataOrdSpostamentoDa", dataOrdSpostamentoDa);
			sql.append(" AND o.dataSpostamento>=:dataOrdSpostamentoDa ");
		}

		if(dataOrdSpostamentoA != null 
//				&& dataOrdSpostamentoA.compareTo(CriteriRicercaOrdinativi.MAX_DATE) < 0
				) {
			params.put("dataOrdSpostamentoA", dataOrdSpostamentoA);
			sql.append(" AND o.dataSpostamento<:dataOrdSpostamentoA ");			
		}
		//

		if(CollectionUtils.isNotEmpty(codiciStato)) {
			params.put("codiciStato", codiciStato);
			sql.append(" AND os.stato.codice IN (:codiciStato) ")
				.append(" AND os.dataCancellazione IS NULL ")
				.append(" AND os.dataInizioValidita <= CURRENT_TIMESTAMP ")
				.append(" AND (os.dataFineValidita IS NULL OR os.dataFineValidita > CURRENT_TIMESTAMP) ");
		}

		if (StringUtils.isNotBlank(codiceFlusso)) {
			params.put("codiceFlusso", codiceFlusso);
			sql.append(" AND om.uid=( ")
				.append("		SELECT MAX(om2.uid) FROM MifTOrdinativo").append(tipoOrdinativo).append(" om2 ")
				.append("		WHERE om2.ordinativo=o AND om2.dataCancellazione IS NULL ")
				.append(" 			AND om2.dataInizioValidita <= CURRENT_TIMESTAMP ")
				.append(" 			AND (om2.dataFineValidita IS NULL OR om2.dataFineValidita > CURRENT_TIMESTAMP)")
				.append("			AND om2.codiceFlusso=:codiceFlusso ")
				.append(" ) ");
		}
		
		if (idCodiceDistinta != null) {
			params.put("idCodiceDistinta", idCodiceDistinta);
			sql.append(" AND o.distinta.uid=CAST(CAST(:idCodiceDistinta AS string) AS integer) "); 
		}
		
		if (numeroDa != null) {
			params.put("numeroDa", numeroDa);
			sql.append(" AND o.numero>=CAST(CAST(:numeroDa AS string) AS integer) "); 
		}

		if (numeroA != null) {
			params.put("numeroA", numeroA);
			sql.append(" AND o.numero<=CAST(CAST(:numeroA AS string) AS integer) ");
		}

		appendDataTrasmissioneOilPresenteClauses(dataTrasmissioneOilPresente, sql);
		appendDaTrasmettereClauses(daTrasmettere, sql);
		appendAttoAmministrativoClauses(attoAmministrativo, sql, params); 
		appendSoggettoClauses(soggetto, sql, params); 
		appendProvvisoriCassaClauses(escludiCollegatiProvvisoriCassa, sql); 

		sql.append(" OR ");
				
		if (Boolean.TRUE.equals(includiVBDaTrasmettere)) {
			sql.append(" o.dataSpostamento IS NOT NULL ")
				.append(" AND DATE_TRUNC('day', o.dataTrasmissioneOil)<DATE_TRUNC('day', o.dataSpostamento) ");
		}

		if (Boolean.TRUE.equals(includiVBDaTrasmettere) && Boolean.TRUE.equals(includiAnnulliDaTrasmettere)) {
			sql.append(" AND ");
		}
		
		if (Boolean.TRUE.equals(includiAnnulliDaTrasmettere)) {
			sql.append(" os.stato.codice='A' ")
				.append(" AND (o.dataTrasmissioneOil IS NULL OR DATE_TRUNC('day', o.dataTrasmissioneOil)<DATE_TRUNC('day', o.dataModifica)) ");
		}
			
		sql.append(" ) ");

		
		return ricercaOrdinativi(sql, params);
	}
	
	@SuppressWarnings("unchecked")
	protected List<SiacTOrdinativo> ricercaOrdinativi(StringBuilder sql, Map<String, Object> params) {

		sql.append(getDateValiditaCancellazioneClauses("o"))
			.append(getDateValiditaCancellazioneClauses("os"));

		appendSiopePlusClauses(sql);
			
		sql.append(" ORDER BY o.numero");
		
		return createQuery(sql.toString(), params).getResultList();
	}
	
	protected void appendSiopePlusClauses(StringBuilder sql) {
		
		sql.append(" AND NOT EXISTS (")
		.append(" 		SELECT 1 FROM SiacROrdinativo ro ")
		.append(" 			WHERE ro.ordinativoDa=o ")
		.append("			AND ro.tipoRelazione.codice='SOS_ORD'")
		.append(getDateValiditaCancellazioneClauses("ro"))
		.append(" ) ")
		
		.append(" AND (EXISTS (")
		.append(" 		SELECT 1 FROM SiacTEnteOil eo ")
		.append(" 			WHERE eo.escludiAnnulli IS FALSE ")
		.append("			AND eo.enteProprietario=o.enteProprietario")
		.append(" ) OR o.dataTrasmissioneOil IS NOT NULL ")
		.append(" OR DATE_TRUNC('day', o.dataEmissione) > ")
		.append("   (SELECT COALESCE(MAX(os2.dataInizioValidita), CAST('01-01-1900' AS date)) ")
		.append("    FROM SiacROrdinativoStato os2 WHERE os2.ordinativo=o AND os2.stato.codice='A' ") // SIAC-8091
		.append(getDateValiditaCancellazioneClauses("os2")) // SIAC-8091
		.append("   ) ") // SIAC-8091
		.append(" ) ");
	}

		
	
	protected void appendDataTrasmissioneOilPresenteClauses(Integer dataTrasmissioneOilPresente, StringBuilder sql) {
		if (dataTrasmissioneOilPresente != null) {
			if (dataTrasmissioneOilPresente == 1) {
				sql.append(" AND o.dataTrasmissioneOil IS NOT NULL ")
					.append(" AND (o.dataSpostamento IS NULL OR o.dataTrasmissioneOil>o.dataSpostamento) ");
			} else {
				sql.append(" AND o.dataTrasmissioneOil IS NULL ");
			}
		}
	}	
	
	protected void appendDaTrasmettereClauses(Integer daTrasmettere, StringBuilder sql) {
		if (daTrasmettere != null) {
			sql.append(" AND o.daTrasmettere=" + (daTrasmettere == 1 ? "TRUE" : "FALSE") + " ");
		}
	}	
	
	protected void appendAttoAmministrativoClauses(SiacTAttoAmmWrapper attoAmministrativo, StringBuilder sql, Map<String, Object> params) {
		
		if (attoAmministrativo.getUid() != null) {
			params.put("idAttoAmministrativo", attoAmministrativo.getUid());
			sql.append(" AND EXISTS ("
					+ "		SELECT 1 FROM o.attiAmministrativi raa "
					+ "		WHERE raa.attoAmministrativo.uid=:idAttoAmministrativo "
					+ " ) ");
			
			return;
		}
		
		if (attoAmministrativo.getAnno() != null || 
			attoAmministrativo.getNumero() != null || 
			attoAmministrativo.getTipo() != null &&	attoAmministrativo.getTipo().getUid() != null || 
			attoAmministrativo.getSac() != null && attoAmministrativo.getSac().getUid() != null) {
			
			params.put("annoAttoAmministrativo", attoAmministrativo.getAnno());
			params.put("numeroAttoAmministrativo", attoAmministrativo.getNumero());
			params.put("idTipoAttoAmministrativo", attoAmministrativo.getTipo().getUid());
			params.put("idSacAttoAmministrativo", attoAmministrativo.getSac() != null ? attoAmministrativo.getSac().getUid() : null);

			sql.append(" AND EXISTS ("
					+ "		SELECT 1 FROM o.attiAmministrativi raa "
					+ "		WHERE (:annoAttoAmministrativo IS NULL OR raa.attoAmministrativo.anno = CAST(:annoAttoAmministrativo AS string)) "
					+ "		AND (:numeroAttoAmministrativo IS NULL OR raa.attoAmministrativo.numero = CAST(CAST(:numeroAttoAmministrativo AS string) AS integer)) "
					+ "		AND (:idTipoAttoAmministrativo IS NULL OR raa.attoAmministrativo.tipo.uid = CAST(CAST(:idTipoAttoAmministrativo AS string) AS integer)) "
					+ "		AND (:idSacAttoAmministrativo IS NULL "
					+ "			OR EXISTS ( "
					+ "				SELECT 1 FROM raa.attoAmministrativo aa JOIN aa.elencoSac rs "
					+ "				WHERE rs.sac.uid = CAST(CAST(:idSacAttoAmministrativo AS string) AS integer) "
					+ "			) "
					+ "		) "
					+ " ) ");
		}
	}
	
	protected void appendSoggettoClauses(SiacTSoggettoWrapper soggetto, StringBuilder sql, Map<String, Object> params) {
		
		if (soggetto.getUid() != null) {
			params.put("idSoggetto", soggetto.getUid());
			sql.append(" AND EXISTS ("
					+ "		SELECT 1 FROM o.soggetti rs "
					+ "		WHERE rs.soggetto.uid=:idSoggetto "
					+ " ) ");
			
			return;
		}
		
		if (StringUtils.isNotBlank(soggetto.getCodice())) {
			
			params.put("codiceSoggetto", soggetto.getCodice());

			sql.append(" AND EXISTS (")
				.append("		SELECT 1 FROM o.soggetti rs ")
				.append("		WHERE rs.soggetto.codice = CAST(:codiceSoggetto AS string) ")
				.append(" ) ");
		}
	}
	
	
	protected void appendProvvisoriCassaClauses(boolean escludiCollegatiProvvisoriCassa, StringBuilder sql) {
		if (escludiCollegatiProvvisoriCassa) {
			sql.append(" AND NOT EXISTS ")
				.append(" (SELECT 1 FROM o.provvisoriCassa pc ")
				.append(" WHERE 1=1 ")
				.append(getDateValiditaCancellazioneClauses("pc"))
				.append(") ");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> leggiCodiciFlussoOrdinativi(int idEnte, int idBilancio, String codiceTipoOrdinativo) {
		Map<String, Object> params = new HashMap<String, Object>();
			
		params.put("idEnte", idEnte);
		params.put("idBilancio", idBilancio);
		
		StringBuilder sql = new StringBuilder("SELECT DISTINCT om.codiceFlusso FROM MifTOrdinativo")
											.append(getDescrTipoOrdinativo(codiceTipoOrdinativo)).append(" om ")
				.append(" WHERE om.enteProprietario.uid=:idEnte ")
				.append(" AND om.bilancio.uid=:idBilancio ")
				.append(getDateValiditaCancellazioneClauses("om"))
				.append(" ORDER BY om.codiceFlusso");        

		return createQuery(sql.toString(), params).getResultList();
	}
	

	@Override
	public Date readDataAnnullamento(Integer idOrdinativo) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("idOrdinativo", idOrdinativo);
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT os FROM SiacROrdinativoStato os ");

		sql.append(" WHERE os.ordinativo.uid=:idOrdinativo ")
			.append(" AND os.stato.codice='A' ")
			.append(" ORDER BY os.uid DESC");
		
		List<SiacROrdinativoStato> x = createTypedQuery(sql.toString(), params, SiacROrdinativoStato.class).getResultList();
		
		if (CollectionUtils.isEmpty(x)) {
			return null;
		}
		
		return x.get(0).getDataInizioValidita();
	}
	
	protected String getDescrTipoOrdinativo(String codiceTipo) { 
		TipoOrdinativo tipoOrdinativo = TipoOrdinativo.fromCodice(codiceTipo);
		
		if (tipoOrdinativo == null) {
			throw new IllegalArgumentException("Codice tipo ordinativo non valido: " + codiceTipo);
		}
		
		return getDescrTipoOrdinativo(tipoOrdinativo);
	}

	protected String getDescrTipoOrdinativo(TipoOrdinativo tipoOrdinativo) { 
		switch (tipoOrdinativo) {
		case INCASSO:
			return "Entrata";
		case PAGAMENTO:
			return "Spesa";
		}
		
		throw new IllegalArgumentException("Tipo ordinativo non valido: " + tipoOrdinativo.getCodice());
	}

	@Override
	public String sbloccaOrdinativiMif(int idEnte, String loginOperazione, String codiceTipoOrdinativo, String elencoIdOrdinativi) {
		Query q = entityManager.createNativeQuery("SELECT fnc_mif_ordinativo_sblocca(?, ?, ?, ?)");

		q.setParameter(1, idEnte)
		.setParameter(2, loginOperazione)
		.setParameter(3, codiceTipoOrdinativo)
		.setParameter(4, elencoIdOrdinativi);

		return (String) q.getSingleResult();
	}
		
	protected String trasmettiOrdinativiMif(String fnName, int idEnte, String annoBilancio, String elencoIdOrdinativi, String loginOperazione) {
		Query q = entityManager.createNativeQuery(String.format("SELECT %s(?, NULL, ?, ?, ?, LOCALTIMESTAMP)", fnName));

		q.setParameter(1, idEnte)
		.setParameter(2, annoBilancio)
		.setParameter(3, elencoIdOrdinativi)
		.setParameter(4, loginOperazione);

		return (String) q.getSingleResult();        
	}

	protected String chiudiElaborazioneOrdinativiMif(String fnName, int idEnte, String annoBilancio, int idElaborazione, String loginOperazione) {
		Query q = entityManager.createNativeQuery(String.format("SELECT %s(?, NULL, ?, ?, LOCALTIMESTAMP, ?)", fnName));

		q.setParameter(1, idEnte)
		.setParameter(2, annoBilancio)
		.setParameter(3, loginOperazione)
		.setParameter(4, idElaborazione);

		return (String) q.getSingleResult();
	}

	
	@Override
	public String chiudiElaborazioneOrdinativiMifEntrata(int idEnte, String annoBilancio, int idElaborazione, String loginOperazione) {
		return chiudiElaborazioneOrdinativiMif("fnc_mif_ordinativo_entrata_chiu_elab_text", 
				idEnte, annoBilancio, idElaborazione, loginOperazione);
	}
	
	@Override
	public String chiudiElaborazioneOrdinativiMifSpesa(int idEnte, String annoBilancio, int idElaborazione, String loginOperazione) {
		return chiudiElaborazioneOrdinativiMif("fnc_mif_ordinativo_spesa_chiu_elab_text", 
				idEnte, annoBilancio, idElaborazione, loginOperazione);
	}
		

	@Override
	public String trasmettiOrdinativiMifEntrata(int idEnte, String annoBilancio, String elencoIdOrdinativi,	String loginOperazione) {
		return trasmettiOrdinativiMif("fnc_mif_ordinativo_entrata_ritrasm", 
				idEnte, annoBilancio, elencoIdOrdinativi, loginOperazione);
	}
	
	
	@Override
	public String trasmettiOrdinativiMifSpesa(int idEnte, String annoBilancio, String elencoIdOrdinativi, String loginOperazione) {
		return trasmettiOrdinativiMif("fnc_mif_ordinativo_spesa_ritrasm", 
				idEnte, annoBilancio, elencoIdOrdinativi, loginOperazione);
	}

	
	@Override
	public String chiudiElaborazioneOrdinativiMifEntrataSiopePlus(int idEnte, String annoBilancio, int idElaborazione, String loginOperazione) {
		return chiudiElaborazioneOrdinativiMifEntrata(idEnte, annoBilancio, idElaborazione, loginOperazione);
	}
	
	@Override
	public String chiudiElaborazioneOrdinativiMifSpesaSiopePlus(int idEnte, String annoBilancio, int idElaborazione, String loginOperazione) {
		return chiudiElaborazioneOrdinativiMifSpesa(idEnte, annoBilancio, idElaborazione, loginOperazione);
	}
	
	@Override
	public String trasmettiOrdinativiMifEntrataSiopePlus(int idEnte, String annoBilancio, String elencoIdOrdinativi,	String loginOperazione) {
		return trasmettiOrdinativiMif("fnc_mif_ordinativo_entrata_splus_ritrasm", 
				idEnte, annoBilancio, elencoIdOrdinativi, loginOperazione);
	}
	
	@Override
	public String trasmettiOrdinativiMifSpesaSiopePlus(int idEnte, String annoBilancio, String elencoIdOrdinativi, String loginOperazione) {
		return trasmettiOrdinativiMif("fnc_mif_ordinativo_spesa_splus_ritrasm", 
				idEnte, annoBilancio, elencoIdOrdinativi, loginOperazione);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SiacTOrdinativo> ricercaOrdinativiDaFlusso(Integer uidFlusso, String codiceTipoOrdinativo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("uidFlusso", uidFlusso);
		params.put("codiceTipoOrdinativo", codiceTipoOrdinativo);
		
		String tipoOrdinativo = getDescrTipoOrdinativo(codiceTipoOrdinativo);
			
		StringBuilder sql = new StringBuilder("SELECT o FROM SiacTOrdinativo o ")
				.append(" JOIN FETCH o.stati os ")
				.append(" JOIN o.ordinativi").append(tipoOrdinativo).append("Mif om ")
				.append(" LEFT OUTER JOIN FETCH o.ordinativi").append(tipoOrdinativo).append("Mif om2 ") 
				.append(" WHERE om2.flussoElaborato.uid = :uidFlusso ")
				.append(" AND om2.uid = (")
				.append("		SELECT MAX(om3.uid) FROM MifTOrdinativo").append(tipoOrdinativo).append(" om3 ")
				.append("		WHERE om3.ordinativo = o ")
				.append(getDateValiditaCancellazioneClauses("om3"))
				.append(" ) ")
				.append(" AND o.tipo.codice=:codiceTipoOrdinativo ")
				.append(getDateValiditaCancellazioneClauses("om2"))
				.append(getDateValiditaCancellazioneClauses("om"))
				.append(getDateValiditaCancellazioneClauses("os"))
				.append(" ORDER BY om2.ordinativo.numero");        

		return (List<SiacTOrdinativo>) createQuery(sql.toString(), params).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiacTOrdinativo> ricercaOrdinativiDaFlusso(Integer uidFlusso, String anno, String codiceTipoOrdinativo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("uidFlusso", uidFlusso);
		params.put("codiceTipoOrdinativo", codiceTipoOrdinativo);
		params.put("anno", anno);
		
		String tipoOrdinativo = getDescrTipoOrdinativo(codiceTipoOrdinativo);
		
		StringBuilder sql = new StringBuilder("SELECT o FROM SiacTOrdinativo o ")
				.append(" JOIN FETCH o.stati os ")
				.append(" LEFT OUTER JOIN FETCH o.ordinativi").append(tipoOrdinativo).append("Mif om2 ") 
				.append(" WHERE om2.bilancio.periodo.anno = :anno ")
				.append(" AND om2.flussoElaborato.uid = :uidFlusso ")
				.append(" AND o.tipo.codice=:codiceTipoOrdinativo ")
				.append(getDateValiditaCancellazioneClauses("om2"))
				.append(getDateValiditaCancellazioneClauses("os"))
				.append(" ORDER BY om2.ordinativo.numero");        
		
		return (List<SiacTOrdinativo>) createQuery(sql.toString(), params).getResultList();
	}

	@Override
	public void updateDataSpostamento(String elencoIdOrdinativi, String loginOperazione) {
		Query q = entityManager.createNativeQuery( String.format(
				" UPDATE siac_t_ordinativo "
				+ " SET ord_spostamento_data = CURRENT_DATE, "
				+ " data_modifica = CURRENT_TIMESTAMP, "
				+ " login_operazione = '%s', "
				+ " login_modifica = '%s' "
				+ " WHERE ord_id IN (%s)", StringEscapeUtils.escapeSql(loginOperazione), 
				StringEscapeUtils.escapeSql(loginOperazione), elencoIdOrdinativi));
		
		q.executeUpdate();
	}

	//BACKOFFICE MODIFICA PIANO DEI CONTI ORDINATIVO 
	@SuppressWarnings("unchecked")
	@Override
	public String modificaPianoDeiContiOrdinativoBackoffice(String numeroRemedy, Integer idEnte, String tipoOrdinativo,
			String annoBilancio, Integer numeroOrdinativo, String eventoCode, String tipoEventoCode, String pdcCode,
			int aggiornaAccertamento, int aggiornaGenerale, int aggiornaGeneraleGSA, int inserisciGenerale, int inserisciGeneraleGSA) {
		
		String methodName = "modificaPianoDeiContiOrdinativoBackoffice"; 
		
		StringBuilder sql = new StringBuilder(" SELECT siac.fnc_siac_bko_modifica_pdc( ");
		sql.append("  :loginOperazione, ");
		sql.append("  :enteProprietarioId, "); 
		sql.append("  :tipoOrdinativo, " );
		sql.append("  :annoBilancio, " );
		sql.append("  :numeroOrdinativo, " );
		sql.append("  :eventoCode, " );
		sql.append("  :eventoTipoCode, " );
		sql.append("  :pdcOrdinativoCode, " );
		sql.append("  :modificaAccertamento, " );
		sql.append("  :aggiornaGenerale, " );
		sql.append("  :aggiornaGeneraleGSA, " );
		sql.append("  :inserisciGenerale, " );
		sql.append("  :inserisciGeneraleGSA) " );

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("loginOperazione", numeroRemedy);
		parameters.put("enteProprietarioId", idEnte);
		parameters.put("tipoOrdinativo", tipoOrdinativo);
		parameters.put("annoBilancio", annoBilancio);
		parameters.put("numeroOrdinativo", numeroOrdinativo);
		parameters.put("eventoCode", eventoCode);
		parameters.put("eventoTipoCode", tipoEventoCode);
		parameters.put("pdcOrdinativoCode", pdcCode);
		parameters.put("modificaAccertamento", aggiornaAccertamento);
		parameters.put("aggiornaGenerale", aggiornaGenerale);
		parameters.put("aggiornaGeneraleGSA", aggiornaGeneraleGSA);
		parameters.put("inserisciGenerale", inserisciGenerale);
		parameters.put("inserisciGeneraleGSA", inserisciGeneraleGSA);
		
		Query q = createNativeQuery(sql.toString(), parameters);
		
		List<String> result = (List<String>) q.getResultList();
		
		if(result != null) {
			log.debug(methodName, "Returning result: "+ result.get(0) + " for functionName: fnc_siac_bko_modifica_pdc");
			return result.get(0);
		}
		
		return null;
	}
	//BACKOFFICE MODIFICA PIANO DEI CONTI ORDINATIVO - END
	
	
}

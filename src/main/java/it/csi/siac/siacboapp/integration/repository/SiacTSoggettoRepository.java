/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTModpag;
import it.csi.siac.siacboapp.integration.entity.SiacTSoggetto;



public interface SiacTSoggettoRepository extends JpaRepository<SiacTSoggetto, Integer> {

	@Query("SELECT s FROM SiacTSoggetto s " 
			+ " LEFT OUTER JOIN s.ruoli sr "
			+ " LEFT OUTER JOIN sr.ruolo r "
			+ " WHERE s.enteProprietario.uid=:enteId AND s.dataCancellazione IS NULL "
			+ " AND sr.dataCancellazione IS NULL" 
			+ " AND r.dataCancellazione IS NULL"
			+ " AND sr.enteProprietario.uid=:enteId " 
			+ " AND r.enteProprietario.uid=:enteId")
	List<SiacTSoggetto> getElencoSoggetti(@Param("enteId") int enteId);

	@Query("FROM SiacTSoggetto s WHERE s.enteProprietario.uid = :enteId AND s.dataCancellazione IS NULL "
			+ " AND s.uid = :uid")
	SiacTSoggetto findOne(@Param("uid") Integer uid, @Param("enteId") int enteId);

	@Query("SELECT s FROM SiacTSoggetto s " 
			+ " WHERE s.enteProprietario.uid=:idEnte "
			+ " AND (COALESCE(:codice, '')='' OR s.codice = CAST(:codice AS string)) "
			+ " AND (COALESCE(:codiceFiscale, '')='' AND COALESCE(:partitaIva, '')='' "
			+ "		OR s.codiceFiscale = CAST(:codiceFiscale AS string) "
			+ "		OR s.codiceFiscale = CAST(:partitaIva AS string)) "
			+ " AND (COALESCE(:descrizione ,'')='' OR UPPER(s.descrizione) LIKE UPPER('%' || CAST(:descrizione AS text) || '%')) "
			+ " AND (:idClasse IS NULL OR EXISTS ( "
			+ "								SELECT 1 FROM s.classi c WHERE c.classe.uid = CAST(CAST(:idClasse AS string) AS integer)) "
			+ "	) "
			+ " AND s.ambito.codice = 'AMBITO_FIN' "
			+ " AND NOT EXISTS ( "
			+ "           SELECT 1 FROM s.relazioniSoggettoA sra WHERE sra.tipoRelazione.codice='SEDE_SECONDARIA' "
			+ " 				AND sra.dataCancellazione IS NULL "
			+ " 				AND sra.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " 				AND (sra.dataFineValidita IS NULL OR sra.dataFineValidita > CURRENT_TIMESTAMP) "
			+ " ) "
			+ " AND s.ambito.enteProprietario=s.enteProprietario "
			+ " AND s.dataCancellazione IS NULL "
			+ " AND s.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (s.dataFineValidita IS NULL OR s.dataFineValidita > CURRENT_TIMESTAMP) "
			+ " ORDER BY s.codice")
	List<SiacTSoggetto> ricercaSoggetti(
			@Param("idEnte") Integer idEnte, 
			@Param("codice") String codice, 
			@Param("codiceFiscale") String codiceFiscale, 
			@Param("partitaIva") String partitaIva,
			@Param("descrizione") String descrizione, 
			@Param("idClasse") Integer idClasse);

	
	@Query(value = "SELECT stm.iban FROM " + 
			" siac_t_modpag stm, " + 
			" siac_t_soggetto sts, " + 
			" siac_d_ambito sda " + 
			" WHERE stm.soggetto_id = sts.soggetto_id " + 
			" AND sts.soggetto_code = :codiceSoggetto " + 
			" AND stm.iban IS NOT NULL " + 
			" AND sts.ambito_id = sda.ambito_id " + 
			" AND sda.ambito_code = 'AMBITO_FIN' " + 
			" AND sda.ente_proprietario_id = :idEnte " + 
			" AND stm.data_cancellazione IS NULL " + 
			" AND stm.validita_inizio < CURRENT_TIMESTAMP " + 
			" AND (stm.validita_fine IS NULL OR stm.validita_fine > CURRENT_TIMESTAMP) " + 
			" ORDER BY stm.modpag_id DESC " +
			";	", nativeQuery = true)
	List<String> findIbanModpagSoggetto(
		@Param("idEnte") int idEnte, 
		@Param("codiceSoggetto") String codiceSoggetto
	);
	

	@Query(value = "SELECT /*6435 as ordine,*/ x.* FROM  siac_t_modpag x limit 10", nativeQuery = true)
	List<SiacTModpag> xyz(
	);

	
	
}

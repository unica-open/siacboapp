/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTSubdoc;



public interface SiacTSubdocRepository extends JpaRepository<SiacTSubdoc, Integer> {

	//SIAC-8478 adeguo la query solo per migliorane la lettura
	@Query(value = " SELECT sts.* "
			+ " FROM siac_t_subdoc sts  "
			+ " JOIN siac_t_ente_proprietario step ON sts.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :idEnte "
			+ " JOIN siac_t_doc std ON sts.doc_id = std.doc_id  "
			+ " JOIN siac_d_subdoc_tipo sdst ON sts.subdoc_tipo_id = sdst.subdoc_tipo_id "
			+ " JOIN siac_r_subdoc_atto_amm srsaa ON sts.subdoc_id = srsaa.subdoc_id AND srsaa.data_cancellazione IS NULL "
			+ " JOIN siac_t_atto_amm staa ON srsaa.attoamm_id = staa.attoamm_id  "
			+ " JOIN siac_r_doc_sog srds ON std.doc_id = srds.doc_id AND srds.data_cancellazione IS NULL "
			+ " JOIN siac_t_soggetto soggetto ON srds.soggetto_id = soggetto.soggetto_id  "
			+ " JOIN siac_r_doc_stato srdstato ON srdstato.doc_id = std.doc_id AND srdstato.data_cancellazione IS NULL  "
			+ " JOIN siac_d_atto_amm_tipo sdaat ON staa.attoamm_tipo_id = sdaat.attoamm_tipo_id  "
			+ " JOIN siac_d_ambito sda ON soggetto.ambito_id = sda.ambito_id  "
			+ " WHERE staa.attoamm_id = :idAttoAmministrativo "
			+ " AND soggetto.soggetto_code = :codiceSoggetto "
			+ " AND sdaat.attoamm_tipo_code = 'ALG' "
			+ " AND sdst.subdoc_tipo_code = 'SS' "
			+ " AND sda.ambito_code = 'AMBITO_FIN' "
			+ " AND (:idSac IS NULL OR EXISTS ( "
			+ " 	SELECT 1 "
			+ "		FROM siac_r_atto_amm_class raac "
			+ " 	WHERE raac.attoamm_id = staa.attoamm_id "
			+ " 	AND raac.data_cancellazione IS NULL   "
			+ " 	AND raac.classif_id = CAST(CAST(:idSac AS VARCHAR) AS INTEGER))  "
			+ " ) "
			+ " AND (srsaa.validita_fine IS NULL OR srsaa.validita_fine > CURRENT_TIMESTAMP)  "
			+ " AND (srdstato.validita_fine IS NULL OR srdstato.validita_fine > CURRENT_TIMESTAMP)  "
			+ " AND (srds.validita_fine IS NULL OR srds.validita_fine > CURRENT_TIMESTAMP)  "
			+ " ORDER BY sts.subdoc_numero ", nativeQuery = true)
	List<SiacTSubdoc> findQuoteAttoAmmSoggetto(
		@Param("idEnte") int idEnte, 
		@Param("idAttoAmministrativo") Integer idAttoAmministrativo, 
		@Param("idSac") Integer idSac, 
		@Param("codiceSoggetto") String codiceSoggetto
	);
	
}

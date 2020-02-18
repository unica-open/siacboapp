/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTAttoAmm;

public interface SiacTAttoAmmRepository extends JpaRepository<SiacTAttoAmm, Integer> {

	@Query("SELECT aa FROM SiacTAttoAmm aa "
			+ " LEFT OUTER JOIN aa.stati s "
			+ " 	WITH s.enteProprietario.uid = :enteProprietarioId "
			+ " 	AND s.dataCancellazione IS NULL "
			+ " 	AND s.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " 	AND (s.dataFineValidita IS NULL OR s.dataFineValidita > CURRENT_TIMESTAMP) "
			+ " WHERE aa.enteProprietario.uid = :enteProprietarioId "
			+ " AND aa.anno=CAST(:anno AS string) "
			+ " AND (:numero IS NULL OR aa.numero=CAST(CAST(:numero AS string) AS integer)) "
			+ " AND (:idTipo IS NULL OR aa.tipo.uid=CAST(CAST(:idTipo AS string) AS integer)) "
			+ " AND (:idSac IS NULL OR EXISTS ("
			+ "								SELECT 1 FROM aa.elencoSac s WHERE s.sac.uid = CAST(CAST(:idSac AS string) AS integer)) "
			+ " ) "
			+ " AND (COALESCE(:oggetto, '')='' OR UPPER(aa.oggetto) LIKE UPPER('%' || CAST(:oggetto AS text) || '%')) "
			+ " AND aa.dataCancellazione IS NULL "
			+ " AND aa.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (aa.dataFineValidita IS NULL OR aa.dataFineValidita > CURRENT_TIMESTAMP) "
			+ " ORDER BY aa.anno DESC, aa.numero")
	List<SiacTAttoAmm> ricercaAttiAmministrativi(
			@Param("enteProprietarioId") Integer enteProprietarioId,
			@Param("anno") Integer anno, 
			@Param("numero") Integer numero, 
			@Param("idTipo") Integer idTipo,
			@Param("idSac") Integer idSac,
			@Param("oggetto") String oggetto);
}

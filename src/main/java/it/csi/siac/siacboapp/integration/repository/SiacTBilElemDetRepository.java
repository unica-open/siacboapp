/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTBilElemDet;



public interface SiacTBilElemDetRepository extends JpaRepository<SiacTBilElemDet, Integer> {
	String MAP_IMPORTI_KEY_PATTERN = "%s.%s";

	@Query("SELECT bed FROM SiacTBilElemDet bed "
			+ " WHERE bed.elementoBilancio.uid=:idCapitolo "
			+ " AND bed.tipo.codice IN :codiciTipoImporto "
			+ " AND bed.periodo.codice IN :codiciPeriodo "
			+ " AND bed.dataCancellazione IS NULL "
			+ " AND bed.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " AND (bed.dataFineValidita IS NULL OR bed.dataFineValidita>CURRENT_TIMESTAMP) "
			)
	List<SiacTBilElemDet> readImportiCapitolo(
			@Param("idCapitolo") Integer idCapitolo,
			@Param("codiciTipoImporto") List<String> codiciTipoImporto,
			@Param("codiciPeriodo") List<String> codiciPeriodo
	);

	@Query(" SELECT tm.movgestAnno, COALESCE(SUM(tmtd.movgestTsDetImporto), 0) "
			+ " FROM SiacRMovgestBilElem rmbe, SiacTMovgest tm, SiacTMovgestT tmt, SiacRMovgestTsStato rmts, SiacTMovgestTsDet tmtd "
			// Join
			+ " WHERE rmbe.siacTMovgest = tm "
			+ " AND tmt.siacTMovgest = tm "
			+ " AND rmts.siacTMovgestT = tmt "
			+ " AND tmtd.siacTMovgestT = tmt "
			// Date cancellazione e fine validita'
			+ " AND rmbe.dataCancellazione IS NULL "
			+ " AND (rmbe.dataFineValidita IS NULL OR rmbe.dataFineValidita <= CURRENT_TIMESTAMP) "
			+ " AND rmts.dataCancellazione IS NULL "
			+ " AND (rmts.dataFineValidita IS NULL OR rmts.dataFineValidita <= CURRENT_TIMESTAMP) "
			+ " AND tmt.dataCancellazione IS NULL "
			+ " AND (tmt.dataFineValidita IS NULL OR tmt.dataFineValidita <= CURRENT_TIMESTAMP) "
			+ " AND tmtd.dataCancellazione IS NULL "
			+ " AND (tmtd.dataFineValidita IS NULL OR tmtd.dataFineValidita <= CURRENT_TIMESTAMP) "
			+ " AND tm.dataCancellazione IS NULL "
			+ " AND (tm.dataFineValidita IS NULL OR tm.dataFineValidita <= CURRENT_TIMESTAMP) "
			+ " AND rmbe.siacTBilElem.dataCancellazione IS NULL "
			+ " AND (rmbe.siacTBilElem.dataFineValidita IS NULL OR rmbe.siacTBilElem.dataFineValidita <= CURRENT_TIMESTAMP) "
			// Condizioni
			+ " AND rmbe.siacTBilElem.uid = :elemId "
			+ " AND tm.siacDMovgestTipo.movgestTipoCode = 'I' "
			+ " AND rmts.siacDMovgestStato.movgestStatoCode IN ('P', 'D', 'N') "
			+ " AND tmt.siacDMovgestTsTipo.movgestTsTipoCode = 'T' "
			+ " AND tmtd.siacDMovgestTsDetTipo.movgestTsDetTipoCode = 'A' "
			+ " AND tm.siacTBil = rmbe.siacTBilElem.bilancio "
			+ " AND tm.movgestAnno IN (:anni) "
			// Group by
			+ " GROUP BY tm.movgestAnno ")
	List<Object[]> readImportiImpegnoPerCapitoloAndAnni(
			@Param("elemId") Integer elemId,
			@Param("anni") List<Integer> anni);
	
	
	@Modifying
	@Query("UPDATE SiacTBilElemDet ed SET "
			+ " ed.dataModifica=CURRENT_TIMESTAMP, "
			+ " ed.importo=:importo, "
			+ " ed.loginOperazione=:loginOperazione "
			+ " WHERE ed.uid=:idSiacTBilElemDet")
	void updateImporto(
			@Param("idSiacTBilElemDet") Integer idSiacTBilElemDet,
			@Param("importo") BigDecimal importo,
			@Param("loginOperazione") String loginOperazione
	);
	
	
}

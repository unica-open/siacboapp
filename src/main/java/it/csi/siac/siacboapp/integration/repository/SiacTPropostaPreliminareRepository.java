/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDPropostaPreliminareStato;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;



public interface SiacTPropostaPreliminareRepository extends JpaRepository<SiacTPropostaPreliminare, Integer> {
	@Query("SELECT pp FROM SiacTPropostaPreliminare pp "
			+ " WHERE pp.enteProprietario=:ente "
			+ " AND pp.dataCancellazione IS NULL "
			+ " AND (pp.capitolo IN (:elencoCapitoli) OR pp.capitolo IS NULL) "
			+ " AND (pp.dataFineValidita IS NULL OR pp.dataFineValidita>CURRENT_TIMESTAMP) "
			+ " ORDER BY pp.numero")
	List<SiacTPropostaPreliminare> readElencoPropostePreliminari(
				@Param("ente") SiacTEnteProprietario ente, 
				@Param("elencoCapitoli") List<SiacTBilElem> elencoCapitoli
			);

	@Query("SELECT COALESCE(MAX(numero), 0) + 1 FROM SiacTPropostaPreliminare pp "
			+ " WHERE pp.anno=:anno "
			+ " AND pp.dataCancellazione IS NULL "
			+ " AND (pp.dataFineValidita IS NULL OR pp.dataFineValidita>CURRENT_TIMESTAMP) ")
	Integer getNextNumeroAnno(
			@Param("anno") Integer anno
	);
	
	
	@Modifying
	@Query("UPDATE SiacTPropostaPreliminare pp "
			+ " SET stato=:stato, "
			+ " dataModifica=CURRENT_TIMESTAMP, "
			+ " loginOperazione=:loginOperazione "
			+ " WHERE pp.uid=:idProposta")
	void updateStatoPropostaPreliminare(
			@Param("idProposta") Integer idProposta,
			@Param("stato") SiacDPropostaPreliminareStato stato,
			@Param("loginOperazione") String loginOperazione
	);

}

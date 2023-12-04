/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDOrdinativoStato;



public interface SiacDOrdinativoStatoRepository extends JpaRepository<SiacDOrdinativoStato, Integer> {
	@Query("SELECT s FROM SiacDOrdinativoStato s " 
			+ " WHERE s.enteProprietario.uid=:idEnte "
			+ " AND s.dataCancellazione IS NULL "
			+ " AND s.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (s.dataFineValidita IS NULL OR s.dataFineValidita > CURRENT_TIMESTAMP)")
	List<SiacDOrdinativoStato> getElencoStatiOrdinativo(@Param("idEnte") int idEnte);
}

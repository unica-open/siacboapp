/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDSoggettoClasse;

public interface SiacDSoggettoClasseRepository extends JpaRepository<SiacDSoggettoClasse, Integer> {


	@Query("FROM SiacDSoggettoClasse " 
			+ " WHERE enteProprietario.uid = :enteProprietarioId "
			+ " AND dataCancellazione IS NULL "
			+ " AND dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (dataFineValidita IS NULL OR dataFineValidita > CURRENT_TIMESTAMP) "
			+ " ORDER BY codice")
	List<SiacDSoggettoClasse> getElencoClassiSoggetto(
			@Param("enteProprietarioId") Integer enteProprietarioId);
}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDEventoTipo;

public interface SiacDEventoTipoRepository extends JpaRepository<SiacDEventoTipo, Integer> {

	@Query(value = "normal Sql query", nativeQuery = true)
	List<SiacDEventoTipo> cercaEventiByTipoMovimento(@Param("tipoMovimento") String tipoMovimento,
			@Param("enteProprietarioId") Integer enteProprietarioId);
	
}

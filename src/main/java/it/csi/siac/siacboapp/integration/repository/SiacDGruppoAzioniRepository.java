/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDGruppoAzioni;



public interface SiacDGruppoAzioniRepository extends JpaRepository<SiacDGruppoAzioni, Integer> {
	@Query("SELECT g FROM SiacDGruppoAzioni g "
			+ " WHERE g.enteProprietario.uid=:enteId AND g.dataCancellazione IS NULL")
	List<SiacDGruppoAzioni> getElencoGruppiAzioni(@Param("enteId") int enteId);

	@Query("SELECT g FROM SiacDGruppoAzioni g " + " WHERE g.enteProprietario.uid=:enteId "
			+ " AND g.dataCancellazione IS NULL " + " AND g.uid=:uid")
	SiacDGruppoAzioni findOne(@Param("uid") Integer uid, @Param("enteId") int enteId);

}

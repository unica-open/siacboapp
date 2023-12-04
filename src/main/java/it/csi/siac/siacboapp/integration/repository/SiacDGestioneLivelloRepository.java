/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDGestioneLivello;



public interface SiacDGestioneLivelloRepository extends JpaRepository<SiacDGestioneLivello, Integer> {
	@Query("SELECT gl FROM SiacDGestioneLivello gl "
			+ " WHERE gl.enteProprietario.uid=:idEnte "
			+ " AND gl.codice=:codice "
			+ " AND gl.dataCancellazione IS NULL "
			+ " AND EXISTS ("
			+ "		SELECT ge FROM gl.gestioneEnte ge "
			+ " 		WHERE ge.dataCancellazione IS NULL "
			+ " )"
	)
	SiacDGestioneLivello getGestioneLivelloGestioneEnte(
			@Param("idEnte") Integer idEnte, 
			@Param("codice") String codice
	);

	@Query("SELECT gl FROM SiacDGestioneLivello gl "
			+ " WHERE gl.enteProprietario.uid=:idEnte "
			+ " AND gl.codice=:codice "
			+ " AND gl.dataCancellazione IS NULL "
	)
	SiacDGestioneLivello getGestioneLivello(
			@Param("idEnte") Integer idEnte, 
			@Param("codice") String codice
	);
}

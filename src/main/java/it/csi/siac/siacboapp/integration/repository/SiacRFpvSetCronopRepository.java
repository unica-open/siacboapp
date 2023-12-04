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

import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTCronop;



public interface SiacRFpvSetCronopRepository extends JpaRepository<SiacRFpvSetCronop, Integer> {
	@Query("SELECT x FROM SiacRFpvSetCronop x "
			+ " WHERE x.enteProprietario.uid=:enteId " +
			" AND x.setProgetti.uid=:setProgettiId " +
			" AND x.dataCancellazione IS NULL")
	List<SiacRFpvSetCronop> getElencoProgetti(@Param("setProgettiId") int setProgettiId, @Param("enteId") int enteId);

	
	@Query("SELECT DISTINCT r FROM SiacRFpvSetCronop r " +
			" INNER JOIN r.programma p " +
			" LEFT OUTER JOIN p.movimentiGestioneProgramma m WITH m.dataCancellazione IS NULL " +
			"  WHERE  r.uid=:uid ")
	SiacRFpvSetCronop findOne(@Param("uid") Integer uid);//task-152


	@Modifying
	@Query("UPDATE SiacRFpvSetCronop fsc SET fsc.cronoprogramma=:cronoprogramma WHERE fsc.uid=:uid ")
	void updateCronoprogramma(
			@Param("uid") Integer uid,
			@Param("cronoprogramma") SiacTCronop siacTCronop
	);
	
}

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

import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;



public interface SiacTFpvSetCronopRepository extends JpaRepository<SiacTFpvSetCronop, Integer> {
	@Query("SELECT x FROM SiacTFpvSetCronop x "
			+ " WHERE x.enteProprietario.uid=:enteId " +
			" AND x.bilancio.uid=:bilancioId " +
			" AND x.dataCancellazione IS NULL")
	List<SiacTFpvSetCronop> getElencoSetProgetti(@Param("bilancioId") int bilancioId, @Param("enteId") int enteId);

	@Query("SELECT s FROM SiacTFpvSetCronop s " 
			+ " WHERE s.enteProprietario.uid=:enteId AND s.dataCancellazione IS NULL "
			+ " AND s.uid=:uid")
	SiacTFpvSetCronop findOne(@Param("uid") Integer uid, @Param("enteId") int enteId);

	@Modifying
	@Query("UPDATE SiacTFpvSetCronop SET dataCancellazione=CURRENT_TIMESTAMP, loginOperazione=:loginOperazione WHERE uid=:uid")
	void cancellaSetProgetti(@Param("uid") Integer uid, @Param("loginOperazione") String loginOperazione);
	
	
	

}

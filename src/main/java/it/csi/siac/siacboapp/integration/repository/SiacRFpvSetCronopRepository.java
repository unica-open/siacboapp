/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacRFpvSetCronop;
import it.csi.siac.siacboapp.integration.entity.SiacTCronop;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacRFpvSetCronopRepository extends JpaRepository<SiacRFpvSetCronop, Integer> {
	@Query("SELECT x FROM SiacRFpvSetCronop x "
			+ " WHERE x.enteProprietario.uid=:enteId " +
			" AND x.setProgetti.uid=:setProgettiId " +
			" AND x.dataCancellazione IS NULL")
	List<SiacRFpvSetCronop> getElencoProgetti(@Param("setProgettiId") int setProgettiId, @Param("enteId") int enteId);

	@Override
	@Query("SELECT DISTINCT r FROM SiacRFpvSetCronop r " +
			" INNER JOIN r.programma p " +
			" LEFT OUTER JOIN p.movimentiGestioneProgramma m WITH m.enteProprietario.uid=:enteId AND m.dataCancellazione IS NULL " +
			"  WHERE  r.uid=:uid ")
	SiacRFpvSetCronop findOne(@Param("uid") Integer uid);


	@Modifying
	@Query("UPDATE SiacRFpvSetCronop fsc SET fsc.cronoprogramma=:cronoprogramma WHERE fsc.uid=:uid ")
	void updateCronoprogramma(
			@Param("uid") Integer uid,
			@Param("cronoprogramma") SiacTCronop siacTCronop
	);
	
}

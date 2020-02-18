/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacTFpvSetCronop;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
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

}

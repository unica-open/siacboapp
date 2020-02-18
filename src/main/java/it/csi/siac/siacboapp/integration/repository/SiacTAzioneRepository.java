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

import it.csi.siac.siacboapp.integration.entity.SiacTAzione;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTAzioneRepository extends JpaRepository<SiacTAzione, Integer> {
	@Query("SELECT a FROM SiacTAzione a "
			+ " WHERE a.enteProprietario.uid=:enteId AND a.dataCancellazione IS NULL")
	List<SiacTAzione> getElencoAzioni(@Param("enteId") int enteId);

	@Query("SELECT a FROM SiacTAzione a " + "LEFT OUTER JOIN a.ruoliOp r"
			+ " WITH r.dataCancellazione IS NULL" + " AND r.enteProprietario.uid=:enteId"
			+ " WHERE a.enteProprietario.uid=:enteId AND a.dataCancellazione IS NULL "
			+ " AND a.uid = :uid")
	SiacTAzione findOne(@Param("uid") Integer uid, @Param("enteId") int enteId);

}

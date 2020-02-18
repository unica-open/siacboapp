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

import it.csi.siac.siacboapp.integration.entity.SiacTGruppo;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTGruppoRepository extends JpaRepository<SiacTGruppo, Integer> {
	@Query("SELECT g FROM SiacTGruppo g "
			+ " WHERE g.enteProprietario.uid=:enteId AND g.dataCancellazione IS NULL")
	List<SiacTGruppo> getElencoGruppi(@Param("enteId") int enteId);

	@Query("SELECT g FROM SiacTGruppo g " + "LEFT OUTER JOIN g.ruoliOp r"
			+ " WITH r.dataCancellazione IS NULL" + " AND r.enteProprietario.uid=:enteId"
			+ " WHERE g.enteProprietario.uid=:enteId " + " AND g.dataCancellazione IS NULL "
			+ " AND g.uid=:uid")
	SiacTGruppo findOne(@Param("uid") Integer uid, @Param("enteId") int enteId);

}

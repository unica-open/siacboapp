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

import it.csi.siac.siacboapp.integration.entity.SiacTAccount;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTAccountRepository extends JpaRepository<SiacTAccount, Integer> {
	@Query("SELECT a FROM SiacTAccount a "
			+ " WHERE a.enteProprietario.uid=:enteId AND a.dataCancellazione IS NULL")
	List<SiacTAccount> getElencoAccount(@Param("enteId") int enteId);

	@Query("SELECT a FROM SiacTAccount a " + " LEFT OUTER JOIN a.gruppi g "
			+ " WITH g.dataCancellazione IS NULL AND g.enteProprietario.uid=:enteId"
			+ " LEFT OUTER JOIN a.ruoliOp r "
			+ " WITH r.dataCancellazione IS NULL AND r.enteProprietario.uid=:enteId"
			+ " WHERE a.enteProprietario.uid=:enteId AND a.dataCancellazione IS NULL "
			+ " AND a.uid=:uid")
	SiacTAccount findOne(@Param("uid") Integer uid, @Param("enteId") int enteId);

}

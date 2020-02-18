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

import it.csi.siac.siacboapp.integration.entity.SiacRSoggettoRuolo;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacRSoggettoRuoloRepository extends JpaRepository<SiacRSoggettoRuolo, Integer> {

	@Query("SELECT sr FROM SiacRSoggettoRuolo sr " + " INNER JOIN sr.ruolo "
			+ " INNER JOIN sr.soggetto s WHERE sr.enteProprietario.uid=:enteId "
			+ " AND sr.dataCancellazione IS NULL "
			+ " AND s.dataCancellazione IS NULL ")
	List<SiacRSoggettoRuolo> getElencoSoggettiRuoli(@Param("enteId") int enteId);

}

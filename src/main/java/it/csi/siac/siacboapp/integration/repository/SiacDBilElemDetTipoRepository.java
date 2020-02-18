/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacDBilElemDetTipo;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacDBilElemDetTipoRepository extends JpaRepository<SiacDBilElemDetTipo, Integer> {
	@Query("FROM SiacDBilElemDetTipo a " 
			+ " WHERE a.codice=:codice"
			+ " AND a.enteProprietario.uid=:enteId "
			+ " AND a.dataCancellazione IS NULL"
			+ " AND a.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " AND (a.dataFineValidita IS NULL OR a.dataFineValidita>CURRENT_TIMESTAMP) ")			
	SiacDBilElemDetTipo readTipoImportoCapitolo(
			@Param("enteId") int enteId,
			@Param("codice") String codice
		);

}

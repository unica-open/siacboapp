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

import it.csi.siac.siacboapp.integration.entity.SiacDDistinta;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacDDistintaRepository extends JpaRepository<SiacDDistinta, Integer> {

	@Query("FROM SiacDDistinta d " 
			+ " WHERE d.tipo.codice=:codiceTipoDistinta "
			+ " AND d.enteProprietario.uid=:idEnte "
			+ " AND d.dataCancellazione IS NULL"
			+ " AND d.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " AND (d.dataFineValidita IS NULL OR d.dataFineValidita>CURRENT_TIMESTAMP) "
			+ " ORDER BY d.codice ")			
	List<SiacDDistinta> leggiCodiciDistintaOrdinativi(
			@Param("idEnte") int idEnte, 
			@Param("codiceTipoDistinta") String codiceTipoDistinta);
}

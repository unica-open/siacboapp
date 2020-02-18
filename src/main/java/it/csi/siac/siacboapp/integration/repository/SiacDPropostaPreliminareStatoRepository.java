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

import it.csi.siac.siacboapp.integration.entity.SiacDPropostaPreliminareStato;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacDPropostaPreliminareStatoRepository extends JpaRepository<SiacDPropostaPreliminareStato, Integer> {
	@Query("FROM SiacDPropostaPreliminareStato "
			+ " WHERE enteProprietario=:ente "
			+ " AND codice=:codice "
			+ " AND dataCancellazione IS NULL "
			+ " AND (dataFineValidita IS NULL OR dataFineValidita>CURRENT_TIMESTAMP) ")
	SiacDPropostaPreliminareStato findByCodice(
				@Param("ente") SiacTEnteProprietario ente,
				@Param("codice") String codice
			);
}

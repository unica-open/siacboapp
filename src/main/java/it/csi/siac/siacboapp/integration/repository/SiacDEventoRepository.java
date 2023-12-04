/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDEvento;

public interface SiacDEventoRepository extends JpaRepository<SiacDEvento, Integer> {

	@Query(value = " SELECT sde.*, sdet.*, sdct.* " + 
			" FROM siac.siac_d_evento sde " + 
			" JOIN siac.siac_t_ente_proprietario step ON sde.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac.siac_d_evento_tipo sdet ON sde.evento_tipo_id = sdet.evento_tipo_id  " + 
			" JOIN siac.siac_d_collegamento_tipo sdct ON sde.collegamento_tipo_id = sdct.collegamento_tipo_id  " + 
			" WHERE sdet.evento_tipo_code = 'OI' " +
			" AND sde.evento_code = 'OIN-INS' " +
			" AND sde.data_cancellazione is NULL  " + 
			" AND (sde.validita_fine is NULL OR sde.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY sde.evento_code ", nativeQuery = true)
	List<SiacDEvento> cercaEventiByTipoMovimentoEntrata(@Param("enteProprietarioId") Integer enteProprietarioId);

	@Query(value = " SELECT sde.*, sdet.*, sdct.* " + 
			" FROM siac.siac_d_evento sde " + 
			" JOIN siac.siac_t_ente_proprietario step ON sde.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac.siac_d_evento_tipo sdet ON sde.evento_tipo_id = sdet.evento_tipo_id  " + 
			" JOIN siac.siac_d_collegamento_tipo sdct ON sde.collegamento_tipo_id = sdct.collegamento_tipo_id  " + 
			" WHERE sdet.evento_tipo_code = 'OP' " +
			" AND sde.evento_code = 'OPA-INS' " +
			" AND sde.data_cancellazione is NULL  " + 
			" AND (sde.validita_fine is NULL OR sde.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY sde.evento_code ", nativeQuery = true)
	List<SiacDEvento> cercaEventiByTipoMovimentoSpesa(@Param("enteProprietarioId") Integer enteProprietarioId);
	
}

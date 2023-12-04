/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDAttoAmmTipo;

public interface SiacDAttoAmmTipoRepository extends JpaRepository<SiacDAttoAmmTipo, Integer> {

	@Query("FROM SiacDAttoAmmTipo " 
			+ " WHERE enteProprietario.uid = :enteProprietarioId "
			+ " AND dataCancellazione IS NULL "
			+ " AND dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (dataFineValidita IS NULL OR dataFineValidita > CURRENT_TIMESTAMP)")
	List<SiacDAttoAmmTipo> getElencoTipiAttoAmministrativo(@Param("enteProprietarioId") Integer enteProprietarioId);
}

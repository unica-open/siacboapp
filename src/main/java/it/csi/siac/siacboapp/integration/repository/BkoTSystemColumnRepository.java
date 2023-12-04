/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.BkoTSystemColumn;

public interface BkoTSystemColumnRepository extends JpaRepository<BkoTSystemColumn, Integer> {
	@Query("SELECT sc FROM BkoTSystemColumn sc where sc.table.id=:tableId ORDER BY sc.ordinalPosition")
	List<BkoTSystemColumn> getTableColumns(@Param("tableId") Integer tableId);
}

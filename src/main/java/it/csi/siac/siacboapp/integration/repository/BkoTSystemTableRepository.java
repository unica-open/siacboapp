/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;

public interface BkoTSystemTableRepository extends JpaRepository<BkoTSystemTable, Integer> {
	@Query("SELECT st FROM BkoTSystemTable st ORDER BY name")
	List<BkoTSystemTable> getTables();

	@Query("SELECT st FROM BkoTSystemTable st WHERE st.type.code=:type ORDER BY name")
	List<BkoTSystemTable> getTablesByType(@Param("type") String type);

	@Query("SELECT st FROM BkoTSystemTable st where id=:id")
	BkoTSystemTable getTable(@Param("id") Integer id);

	@Query("SELECT st FROM BkoTSystemTable st JOIN FETCH st.columns where st.id=:id")
	BkoTSystemTable getTableWithColumns(@Param("id") Integer id);

	@Query("SELECT st FROM BkoTSystemTable st where UPPER(name)=UPPER(:tableName)")
	BkoTSystemTable getTableByName(@Param("tableName") String tableName);

}

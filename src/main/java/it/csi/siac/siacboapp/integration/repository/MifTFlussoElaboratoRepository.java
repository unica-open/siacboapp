/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.MifTFlussoElaborato;



public interface MifTFlussoElaboratoRepository extends JpaRepository<MifTFlussoElaborato, Integer> {
	@Query("SELECT fe FROM MifTFlussoElaborato fe " 
			+ " WHERE fe.enteProprietario.uid=:idEnte "
			+ " AND fe.tipo.codice=:codiceTipoFlusso"
			+ " AND (:anno IS NULL OR CAST(YEAR(fe.data) AS string)=:anno) "
			+ " AND fe.dataCreazione>=:dataInserimentoDa "
			+ " AND fe.dataCreazione<:dataInserimentoA " 
			+ " AND (:numeroDa IS NULL OR CAST(fe.numero AS integer)>=CAST(CAST(:numeroDa AS string) AS integer)) " 
			+ " AND (:numeroA IS NULL OR CAST(fe.numero AS integer)<=CAST(CAST(:numeroA AS string) AS integer))"
			+ " AND fe.numero IS NOT NULL " 
			+ " AND fe.dataCancellazione IS NULL "
			//+ " AND fe.dataInizioValidita < CURRENT_TIMESTAMP "
			//+ " AND (fe.dataFineValidita IS NULL OR fe.dataFineValidita > CURRENT_TIMESTAMP)"
			+" ORDER BY fe.numero "
			)
	List<MifTFlussoElaborato> getElencoFlussiElaborati(
			@Param("idEnte") int idEnte,
			@Param("codiceTipoFlusso") String codiceTipoFlusso,
			@Param("anno") String anno,
			@Param("dataInserimentoDa") Date dataInserimentoDa,
			@Param("dataInserimentoA") Date dataInserimentoA,
			@Param("numeroDa") Integer numeroDa,
			@Param("numeroA") Integer numeroA
	);  
}



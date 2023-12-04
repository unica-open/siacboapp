/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTDoc;



public interface SiacTDocRepository extends JpaRepository<SiacTDoc, Integer> {

	@Query(value = "SELECT fnc_siac_annulla_attivazioni_contabili(:idEnte, :annoBilancio, :numeroNdc, :tipoDoc, :codiceSoggetto, :loginOperazione)", nativeQuery = true)
	String annullaAttivazioniContabili(
			@Param("idEnte") int idEnte, 
			@Param("annoBilancio") Integer annoBilancio, 
			@Param("numeroNdc") String numeroNdc, 
			@Param("tipoDoc") String tipoDoc, 
			@Param("codiceSoggetto") String codiceSoggetto, 
			@Param("loginOperazione") String loginOperazione);
}

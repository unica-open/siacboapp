/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTVariazione;



public interface SiacTVariazioneRepository extends JpaRepository<SiacTVariazione, Integer> {

	@Query(value = "SELECT fnc_siac_bko_definisci_variazione(:annoBilancio, :numeroVariazione, :idEnte, :loginOperazione, CAST(CURRENT_TIMESTAMP AS timestamp without time zone))", nativeQuery = true)
	String definisciVariazione(
			@Param("idEnte") int idEnte, 
			@Param("annoBilancio") Integer annoBilancio, 
			@Param("numeroVariazione") Integer numeroVariazione, 
			@Param("loginOperazione") String loginOperazione);

	@Query("SELECT tv FROM SiacTVariazione tv "
			+ " WHERE tv.enteProprietario.uid=:idEnte "
			+ " AND tv.bilancio.uid=:idBilancio "
			+ " AND tv.numero=:numero "
			+ " AND tv.dataCancellazione IS NULL ")
	List<SiacTVariazione> findByNumero(
			@Param("idEnte") int idEnte, 
			@Param("idBilancio") int idBilancio, 
			@Param("numero") Integer numero);

}

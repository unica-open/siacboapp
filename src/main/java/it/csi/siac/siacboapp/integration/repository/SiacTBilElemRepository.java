/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;



public interface SiacTBilElemRepository extends JpaRepository<SiacTBilElem, Integer> {
	// Nessun metodo aggiuntivo
	
	/**
	 * SIAC-7639
	 * ricerca capitolo per uid oridinativo e uid ente proprietario
	 * 
	 * @param uidOrdinativo
	 * @param enteProprietarioId
	 * @return SiacTBilElem
	 */
	@Query(value = " SELECT stbe.*, stb.*, stp.* " + 
			" FROM siac.siac_r_ordinativo_bil_elem srobe " + 
			" JOIN siac.siac_t_ente_proprietario step on ( step.ente_proprietario_id  = srobe.ente_proprietario_id and step.ente_proprietario_id = :enteProprietarioId ) " + 
			" JOIN siac.siac_t_bil_elem stbe on ( srobe.elem_id = stbe.elem_id ) " + 
			" JOIN siac.siac_t_bil stb on ( stb.bil_id = stbe.bil_id ) " + 
			" JOIN siac.siac_t_periodo stp on ( stp.periodo_id = stb.periodo_id ) " + 
			" WHERE srobe.ord_id = :uidOrdinativo " + 
			" AND stbe.data_cancellazione is NULL " + 
			" AND ( stbe.validita_fine is NULL OR stbe.validita_fine > CURRENT_TIMESTAMP )" + 
			" AND stb.data_cancellazione is NULL " + 
			" AND ( stb.validita_fine is NULL OR stb.validita_fine > CURRENT_TIMESTAMP )" + 
			" AND stp.data_cancellazione is NULL " + 
			" AND ( stp.validita_fine is NULL OR stp.validita_fine > CURRENT_TIMESTAMP )" + 
			" AND srobe.data_cancellazione is NULL " + 
			" AND ( srobe.validita_fine is NULL OR srobe.validita_fine > CURRENT_TIMESTAMP )", nativeQuery = true)
	List<SiacTBilElem> readCapitoloByOrdinativoUid(@Param("uidOrdinativo")Integer uidOrdinativo, @Param("enteProprietarioId") Integer enteProprietarioId);

	

}

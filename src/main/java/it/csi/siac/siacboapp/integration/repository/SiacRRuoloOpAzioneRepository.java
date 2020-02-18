/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacRRuoloOpAzione;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacRRuoloOpAzioneRepository extends JpaRepository<SiacRRuoloOpAzione, Integer> {
	@Modifying
	@Query("DELETE FROM SiacRRuoloOpAzione ra WHERE ra.ruoloOp.uid=:idRuoloOp")
	void deleteByRuoloOp(@Param("idRuoloOp") Integer idRuoloOp);

	@Modifying
	@Query("DELETE FROM SiacRRuoloOpAzione ra WHERE ra.azione.uid=:idAzione")
	void deleteByAzione(@Param("idAzione") Integer idAzione);

}

/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacROrdinativoProvCassa;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacROrdinativoProvCassaRepository extends JpaRepository<SiacROrdinativoProvCassa, Integer> {
	// Nessun metodo aggiuntivo
}

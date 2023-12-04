/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.siac.siacboapp.integration.entity.SiacROrdinativoProvCassa;



public interface SiacROrdinativoProvCassaRepository extends JpaRepository<SiacROrdinativoProvCassa, Integer> {
	// Nessun metodo aggiuntivo
}

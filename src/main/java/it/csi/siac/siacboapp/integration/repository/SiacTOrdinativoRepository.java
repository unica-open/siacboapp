/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacTOrdinativo;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTOrdinativoRepository extends JpaRepository<SiacTOrdinativo, Integer> {
	
	@Modifying
	@Query("UPDATE SiacTOrdinativo o SET o.dataModifica=CURRENT_TIMESTAMP, o.daTrasmettere=:daTrasmettere WHERE o.uid IN (:idOrdinativi)")
	void setDaTrasmettere(
			@Param("idOrdinativi") List<Integer> idOrdinativi,
			@Param("daTrasmettere") Boolean daTrasmettere
	);
	
	@Query("SELECT o FROM SiacTOrdinativo o WHERE o.uid IN (:idOrdinativi) ORDER BY o.anno, o.numero ")
	List<SiacTOrdinativo> findByIds(@Param("idOrdinativi") List<Integer> idOrdinativi);
	
	@Query("SELECT SUM(otsd.importo) FROM SiacTOrdinativo o "
			+ " JOIN o.ordinativiTs ots "
			+ " JOIN ots.dettagliOrdinativoTs otsd"
			+ " WHERE o.uid=:idOrdinativo "
			+ " AND otsd.tipo.codice='A'")
	BigDecimal readImportoOrdinativo(@Param("idOrdinativo") Integer idOrdinativo);
}

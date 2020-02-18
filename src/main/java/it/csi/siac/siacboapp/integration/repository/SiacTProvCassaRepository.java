/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacTProvCassa;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTProvCassaRepository extends JpaRepository<SiacTProvCassa, Integer> {

	@Query("SELECT pc FROM SiacTProvCassa pc "
			+ " WHERE pc.enteProprietario.uid=:enteProprietarioId "
			+ " AND (:annoDa IS NULL OR pc.anno>=CAST(CAST(:annoDa AS string) AS integer)) "
			+ " AND (:annoA IS NULL OR pc.anno<=CAST(CAST(:annoA AS string) AS integer)) "
			+ " AND (:numeroDa IS NULL OR pc.numero>=CAST(CAST(:numeroDa AS string) AS integer)) "
			+ " AND (:numeroA IS NULL OR pc.numero<=CAST(CAST(:numeroA AS string) AS integer)) "
			+ " AND (:descrizioneCausale IS NULL OR UPPER(pc.causale) LIKE CONCAT('%', UPPER(CAST(:descrizioneCausale AS string)), '%')) "
			+ " AND (:descrizioneSottoCausale IS NULL OR UPPER(pc.subcausale) LIKE CONCAT('%', UPPER(CAST(:descrizioneSottoCausale AS string)), '%')) "
			+ " AND (:descrizioneSoggetto IS NULL OR UPPER(pc.descrizioneSoggetto) LIKE CONCAT('%', UPPER(CAST(:descrizioneSoggetto AS string)), '%')) "
			+ " AND pc.tipo.codice=:codiceTipo "
			+ " AND (CAST(CAST(:dataEmissioneDa AS string) AS date) IS NULL "
			+ "      AND CAST(CAST(:dataEmissioneA AS string) AS date) IS NULL "
			+ "      AND pc.dataEmissione IS NULL "
			+ "      OR ( "
			+ "     		(CAST(CAST(:dataEmissioneDa AS string) AS date) IS NULL "
			+ "              OR DATE_TRUNC('day', pc.dataEmissione)>=CAST(CAST(:dataEmissioneDa AS string) AS date)) "
			+ "          	AND (CAST(CAST(:dataEmissioneA AS string) AS date) IS NULL "
			+ "                  OR DATE_TRUNC('day', pc.dataEmissione)<=CAST(CAST(:dataEmissioneA AS string) AS date))"
			+ "         ) "
			+ " )"
			+ " AND (:importoDa IS NULL OR pc.importo>=CAST(CAST(:importoDa AS string) AS big_decimal)) "
			+ " AND (:importoA IS NULL OR pc.importo<=CAST(CAST(:importoA AS string) AS big_decimal)) "
			+ " AND (:dataRegolarizzazionePresente IS NULL "
			+ "      OR CAST(CAST(:dataRegolarizzazionePresente AS string) AS integer)=0 AND pc.dataRegolarizzazione IS NULL "
			+ "      OR CAST(CAST(:dataRegolarizzazionePresente AS string) AS integer)=1 AND pc.dataRegolarizzazione IS NOT NULL "
			+ "     ) "
			+ " AND (:dataAnnullamentoPresente IS NULL "
			+ "      OR CAST(CAST(:dataAnnullamentoPresente AS string) AS integer)=0 AND pc.dataAnnullamento IS NULL "
			+ "      OR CAST(CAST(:dataAnnullamentoPresente AS string) AS integer)=1 AND pc.dataAnnullamento IS NOT NULL "
			+ "     ) "
			+ " AND pc.dataCancellazione IS NULL "
			+ " AND pc.dataInizioValidita < CURRENT_TIMESTAMP "
			+ " AND (pc.dataFineValidita IS NULL OR pc.dataFineValidita > CURRENT_TIMESTAMP) "
			+ " ORDER BY pc.anno DESC, pc.numero")
	List<SiacTProvCassa> ricercaProvvisoriCassa(
			@Param("enteProprietarioId") Integer enteProprietarioId,
			@Param("annoDa") Integer annoDa, 
			@Param("annoA") Integer annoA, 
			@Param("numeroDa") Integer numeroDa, 
			@Param("numeroA") Integer numeroA, 
			@Param("descrizioneCausale") String descrizioneCausale, 
			@Param("descrizioneSottoCausale") String descrizioneSottoCausale, 
			@Param("descrizioneSoggetto") String descrizioneSoggetto, 
			@Param("codiceTipo") String codiceTipo,
			@Param("dataEmissioneDa") Date dataEmissioneDa,
			@Param("dataEmissioneA") Date dataEmissioneA,
			@Param("importoDa") BigDecimal importoDa,
			@Param("importoA") BigDecimal importoA,
			@Param("dataRegolarizzazionePresente") Integer dataRegolarizzazionePresente, 
			@Param("dataAnnullamentoPresente") Integer dataAnnullamentoPresente 
	);
	
	@Query("SELECT pc FROM SiacTProvCassa pc WHERE pc.uid IN (:idProvvisoriCassa) ORDER BY pc.anno, pc.numero ")
	List<SiacTProvCassa> findByIds(@Param("idProvvisoriCassa") List<Integer> idProvvisoriCassa);
	
	@Query(value = "SELECT fnc_siac_daregolarizzareprovvisorio(:idProvvCassa)", nativeQuery = true)
	BigDecimal calcolaImportoDaRegolarizzare(@Param("idProvvCassa") Integer idProvvCassa);

	
	@Modifying
	@Query("UPDATE SiacTProvCassa pc SET "
			+ " pc.importo=:importo, "
			+ " pc.dataModifica=:data, "
			+ " pc.loginOperazione=:loginOperazione "
			+ " WHERE pc.uid=:idProvvisorioCassa")
	void updateImportoProvvisorioCassa(
			@Param("idProvvisorioCassa") Integer idProvvisorioCassa, 
			@Param("importo") BigDecimal importo, 
			@Param("data") Date data,
			@Param("loginOperazione") String loginOperazione);
}

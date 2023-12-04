/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;



public interface SiacTMovgestRepository extends JpaRepository<SiacTMovgest, Integer> {
	
	@Query("SELECT m FROM SiacTMovgest m "
			+ " WHERE m.enteProprietario.uid=:idEnte "
			+ " AND m.siacTBil.uid=:idBilancio "
			+ " AND m.siacDMovgestTipo.movgestTipoCode=:codiceTipo "
			+ " AND m.movgestAnno=:anno "
			+ " AND m.movgestNumero=:numero "
			+ " AND m.dataCancellazione IS NULL ")
	List<SiacTMovgest> findByTipoAnnoNumero(
			@Param("idEnte") Integer idEnte, 
			@Param("idBilancio") Integer idBilancio, 
			@Param("codiceTipo") String codiceTipo, 
			@Param("anno") Integer anno, 
			@Param("numero") BigDecimal numero);

	@Modifying
	@Query("UPDATE SiacRMovgestTsSog rmts SET "
			+ " rmts.dataModifica=CURRENT_TIMESTAMP, "
			+ " rmts.dataCancellazione=CURRENT_TIMESTAMP, "
			+ " rmts.dataFineValidita=CURRENT_TIMESTAMP, "
			+ " rmts.loginOperazione=CONCAT(rmts.loginOperazione, '-', :loginOperazione) "
			+ " WHERE rmts.dataCancellazione IS NULL "
			+ " AND rmts.movimentoGestioneTS=( "
			+ "   FROM rmts.movimentoGestioneTS mgts "
			+ "     WHERE mgts.siacTMovgest.movgestId=:idMovgest "
			+ "     AND mgts.siacDMovgestTsTipo.movgestTsTipoCode='T' "
			+ " )")
	void eliminaSoggettoCollegato(@Param("idMovgest") Integer idMovgest, @Param("loginOperazione") String loginOperazione);
	
	@Modifying
	@Query("UPDATE SiacRMovgestTsSogclasse rmts SET "
			+ " rmts.dataModifica=CURRENT_TIMESTAMP, "
			+ " rmts.dataCancellazione=CURRENT_TIMESTAMP, "
			+ " rmts.dataFineValidita=CURRENT_TIMESTAMP,"
			+ " rmts.loginOperazione=CONCAT(rmts.loginOperazione, '-', :loginOperazione) "
			+ " WHERE rmts.dataCancellazione IS NULL "
			+ " AND rmts.movimentoGestioneTS=( "
			+ "   FROM rmts.movimentoGestioneTS mgts "
			+ "     WHERE mgts.siacTMovgest.movgestId=:idMovgest "
			+ "     AND mgts.siacDMovgestTsTipo.movgestTsTipoCode='T' "
			+ " )")
	void eliminaClasseSoggettoCollegata(@Param("idMovgest") Integer idMovgest, @Param("loginOperazione") String loginOperazione);

	@Modifying
	@Query("UPDATE SiacRMovgestTsStato rmts SET "
			+ " rmts.dataModifica=CURRENT_TIMESTAMP, "
			+ " rmts.siacDMovgestStato=( "
			+ "   FROM SiacDMovgestStato dms "
			+ "     WHERE dms.movgestStatoCode=:codiceStato "
			+ "     AND dms.enteProprietario=rmts.enteProprietario"			
			+ " ), "
			+ " rmts.loginOperazione=:loginOperazione "
			+ " WHERE rmts.siacTMovgestT=( "
			+ "   FROM rmts.siacTMovgestT tmt "
			+ "     WHERE tmt.siacTMovgest.movgestId=:idMovgest "
			+ "     AND tmt.siacDMovgestTsTipo.movgestTsTipoCode='T' "
			+ " )")
	void modificaStatoOperativo(
			@Param("idMovgest") Integer idMovgest,
			@Param("codiceStato") String codiceStato,
			@Param("loginOperazione") String loginOperazione
	);

	@Modifying
	@Query("UPDATE SiacTMovgest tm SET "
			+ " tm.dataModifica=CURRENT_TIMESTAMP, "
			+ " tm.parereFinanziarioDataModifica=CURRENT_TIMESTAMP, "
			+ " tm.loginOperazione=:loginOperazione, "
			+ " tm.parereFinanziarioLoginOperazione=:loginOperazione, "
			+ " tm.parereFinanziario=:parereFinanziario "
			+ " WHERE tm.movgestId=:idMovgest ")
	void modificaParereFinanziario(
			@Param("idMovgest") Integer idMovgest,
			@Param("parereFinanziario") Boolean parereFinanziario,
			@Param("loginOperazione") String loginOperazione
	);

	@Modifying
	@Query("DELETE FROM SiacRMovgestTsAttr rmta "
			+ " WHERE rmta.attributo=( "
			+ "   FROM rmta.attributo ta "
			+ "     WHERE ta.codice=:codiceAttributo "
			+ "     AND ta.enteProprietario=rmta.enteProprietario "
			+ " ) "
			+ " AND rmta.movimentoGestioneTS=( "
			+ "   FROM rmta.movimentoGestioneTS mgts "
			+ "     WHERE mgts.siacTMovgest.movgestId=:idMovgest "
			+ "     AND mgts.siacDMovgestTsTipo.movgestTsTipoCode='T' "
			+ " )")
	void deleteSiacRMovgestTsAttr(@Param("idMovgest") Integer idMovgest, @Param("codiceAttributo") String codiceAttributo);
	
	@Modifying
	@Query("UPDATE SiacRMovgestClass rmc SET "
			+ " rmc.dataModifica=CURRENT_TIMESTAMP, "
			+ " rmc.dataCancellazione=CURRENT_TIMESTAMP, "
			+ " rmc.dataFineValidita=CURRENT_TIMESTAMP, "
			+ " rmc.loginOperazione=CONCAT(rmc.loginOperazione, '-', :loginOperazione) "
			+ " WHERE rmc.dataCancellazione IS NULL "
			+ " AND rmc.siacTMovgestT.movgestTsId=:idMovgestTs "
			+ " AND rmc.classe.uid=:idSac")
	void eliminaSac(@Param("idMovgestTs") Integer idMovgestTs, @Param("idSac") Integer idSac, @Param("loginOperazione") String loginOperazione);
}

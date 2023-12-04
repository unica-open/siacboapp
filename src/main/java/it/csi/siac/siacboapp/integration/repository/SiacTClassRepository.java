/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTClass;

/**
 * @author Alessandro Todesco
 */


public interface SiacTClassRepository extends JpaRepository<SiacTClass, Integer> {
	
	/**
	 * ricerca piano dei conti per uid capitolo e uid ente proprietario
	 * 
	 * @param uidCapitolo
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac.siac_t_bil_elem stbe  " + 
			" JOIN siac.siac_t_ente_proprietario step ON ( step.ente_proprietario_id  = stbe.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId )  " + 
			" JOIN siac.siac_r_bil_elem_class srbec ON ( stbe.elem_id = srbec.elem_id )  " + 
			" JOIN siac.siac_t_class stc ON ( srbec.classif_id = stc.classif_id ) " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON ( srcft.classif_id = stc.classif_id ) " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON ( stcft.classif_fam_tree_id = srcft.classif_fam_tree_id AND stcft.class_fam_code = 'Piano dei Conti' ) " + 
			" WHERE stbe.elem_id = :uidCapitolo  " + 
			" AND stc.classif_tipo_id in (   " + 
			"  	SELECT sdct2.classif_tipo_id  " + 
			"  	FROM siac.siac_d_class_tipo sdct2  " + 
			"  	JOIN siac.siac_t_ente_proprietario step2 on ( step2.ente_proprietario_id  = sdct2.ente_proprietario_id AND step2.ente_proprietario_id = :enteProprietarioId )  " + 
			"  	WHERE sdct2.classif_tipo_code in ('PDC_V', 'PDC_IV')  " + 
			"  	AND sdct2.data_cancellazione is NULL  " + 
			" 	AND ( sdct2.validita_fine is NULL OR sdct2.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 	ORDER BY sdct2.classif_tipo_id DESC " + 
			" )  " + 
			" AND stbe.data_cancellazione is NULL  " + 
			" AND ( stbe.validita_fine is NULL OR stbe.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND srbec.data_cancellazione is NULL  " + 
			" AND ( srbec.validita_fine is NULL OR srbec.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND stc.data_cancellazione is NULL   " + 
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND srcft.data_cancellazione is NULL " + 
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> readPianoDeiContiByCapitoloUid(@Param("uidCapitolo")Integer uidCapitolo, @Param("enteProprietarioId") Integer enteProprietarioId);
	
	/**
	 * ricerca piano dei conti per uid ordinativo e uid ente proprietario
	 * 
	 * @param uidOrdinativo
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac.siac_t_ordinativo sto " + 
			" JOIN siac.siac_t_ente_proprietario step ON ( step.ente_proprietario_id  = sto.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId ) " + 
			" JOIN siac.siac_r_ordinativo_class sroc ON ( sto.ord_id = sroc.ord_id ) " + 
			" JOIN siac.siac_t_class stc ON ( sroc.classif_id = stc.classif_id )  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON ( srcft.classif_id = stc.classif_id )  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON ( stcft.classif_fam_tree_id = srcft.classif_fam_tree_id AND stcft.class_fam_code = 'Piano dei Conti' )  " + 
			" WHERE sto.ord_id = :uidOrdinativo " + 
			" AND stc.classif_tipo_id in (  " + 
			"  	SELECT sdct2.classif_tipo_id " + 
			"  	FROM siac.siac_d_class_tipo sdct2 " + 
			"  	JOIN siac.siac_t_ente_proprietario step2 on ( step2.ente_proprietario_id  = sdct2.ente_proprietario_id AND step2.ente_proprietario_id = :enteProprietarioId ) " + 
			"  	WHERE sdct2.classif_tipo_code in ('PDC_V', 'PDC_IV') " + 
			"  	AND sdct2.data_cancellazione is NULL " + 
			" 	AND ( sdct2.validita_fine is NULL OR sdct2.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 	ORDER BY sdct2.classif_tipo_id DESC " + 
			" ) " + 
			" AND sto.data_cancellazione is NULL " + 
			" AND ( sto.validita_fine is NULL OR sto.validita_fine > CURRENT_TIMESTAMP )  " + 
			" AND sroc.data_cancellazione is NULL " + 
			" AND ( sroc.validita_fine is NULL OR sroc.validita_fine > CURRENT_TIMESTAMP )  " + 
			" AND stc.data_cancellazione is NULL  " + 
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP )  " + 
			" AND srcft.data_cancellazione is NULL  " + 
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> readPianoDeiContiByOrdinativoUid(@Param("uidOrdinativo")Integer uidOrdinativo, @Param("enteProprietarioId") Integer enteProprietarioId);
	
	/**
	 * ricerca piano dei conti per uid capitolo e uid ente proprietario
	 * 
	 * @param uidCapitolo
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = "SELECT DISTINCT stc.*, srcft.* " + 
			" FROM siac.siac_t_bil_elem stbe  " + 
			" JOIN siac.siac_t_ente_proprietario step on stbe.ente_proprietario_id = step.ente_proprietario_id and step.ente_proprietario_id = :enteProprietarioId  " + 
			" JOIN siac.siac_r_bil_elem_class srbec on stbe.elem_id = srbec.elem_id  " + 
			" JOIN siac.siac_t_class stc on srbec.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_d_class_tipo sdct on stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class src on stc.classif_id = src.classif_a_id  " + 
			" JOIN siac.siac_t_class b on src.classif_b_id = b.classif_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON ( srcft.classif_id = stc.classif_id ) " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON ( " + 
			"	stcft.classif_fam_tree_id = srcft.classif_fam_tree_id " + 
			"	AND stcft.class_fam_code in ('Spesa - TitoliMacroaggregati', 'Entrata - TitoliTipologieCategorie') " + 
			" )  " + 
			" WHERE stbe.elem_id = :uidCapitolo " + 
			" AND srcft.data_cancellazione is NULL " + 
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND srbec.data_cancellazione is NULL " + 
			" AND ( srbec.validita_fine is NULL OR srbec.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND stc.data_cancellazione is NULL " + 
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND src.data_cancellazione is NULL " + 
			" AND ( src.validita_fine is NULL OR src.validita_fine > CURRENT_TIMESTAMP )" + 
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> findMacroAggregatoByCapitolo(@Param("uidCapitolo")Integer uidCapitolo, @Param("enteProprietarioId") Integer enteProprietarioId);

	/**
	 * ricerca piano dei conti per uid ordinativo e uid ente proprietario
	 * 
	 * @param uidOrdinativo
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = "SELECT DISTINCT stc.*, srcft.* " + 
			" FROM siac.siac_t_bil_elem stbe  " + 
			" JOIN siac.siac_t_ente_proprietario step on stbe.ente_proprietario_id = step.ente_proprietario_id and step.ente_proprietario_id = :enteProprietarioId  " + 
			" JOIN siac.siac_r_bil_elem_class srbec on stbe.elem_id = srbec.elem_id  " + 
			" JOIN siac.siac_t_class stc on srbec.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_d_class_tipo sdct on stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class src on stc.classif_id = src.classif_a_id  " + 
			" JOIN siac.siac_t_class b on src.classif_b_id = b.classif_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON ( srcft.classif_id = stc.classif_id ) " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON ( " + 
			"	stcft.classif_fam_tree_id = srcft.classif_fam_tree_id " + 
			"	AND stcft.class_fam_code in ('Spesa - TitoliMacroaggregati', 'Entrata - TitoliTipologieCategorie') " + 
			" )  " + 
			" WHERE stbe.elem_id = :uidOrdinativo " + 
			" AND srcft.data_cancellazione is NULL " + 
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND srbec.data_cancellazione is NULL " + 
			" AND ( srbec.validita_fine is NULL OR srbec.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND stc.data_cancellazione is NULL " + 
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND src.data_cancellazione is NULL " + 
			" AND ( src.validita_fine is NULL OR src.validita_fine > CURRENT_TIMESTAMP )" + 
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> findMacroAggregatoByOrdinativoUid(@Param("uidOrdinativo")Integer uidOrdinativo, @Param("enteProprietarioId") Integer enteProprietarioId);
	
	/**
	 * ricerca i titoli per codice tipo e uid ente proprietario
	 * 
	 * @param tipoCode
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac_t_class stc  " + 
			" JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" WHERE sdct.classif_tipo_code = :tipoCode " +
			" AND stcft.class_fam_code = 'Piano dei Conti' " + 
			" AND stc.classif_code LIKE :uscitaEntrata||'%'  " +
			" AND stc.data_cancellazione is NULL " +
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " +
			" AND srcft.data_cancellazione is NULL " +
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> readTitoliEntrataSpesaByEnteProprietario(@Param("tipoCode")String tipoCode, @Param("uscitaEntrata")String uscitaEntrata, @Param("enteProprietarioId") Integer enteProprietarioId);

	/**
	 * ricerca i filgi per uidPadre e uid ente proprietario
	 * 
	 * @param uidPadre
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac_t_class stc  " + 
			" JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" WHERE srcft.classif_id_padre = :uidPadre " +
			" AND stcft.class_fam_code = 'Piano dei Conti' " + 
			" AND stc.data_cancellazione is NULL " +
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " +
			" AND srcft.data_cancellazione is NULL " +
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> findChildrenByParentUid(@Param("uidPadre")Integer uidPadre, @Param("enteProprietarioId") Integer idEnte);

	/**
	 * ricerca i filgi per uidPadre e uid ente proprietario
	 * 
	 * @param uidPadre
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac_t_class stc  " + 
			" JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" WHERE srcft.classif_id_padre = :uidPadre " +
			" AND stcft.class_fam_code = 'Piano dei Conti' " + 
			" AND stc.data_cancellazione is NULL " +
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " +
			" AND srcft.data_cancellazione is NULL " +
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> findSiblingsByChildUid(@Param("uidPadre")Integer uidPadre, @Param("enteProprietarioId") Integer idEnte);
	
	/**
	 * ricerca i fratelli del padre passando l'uid del figlio e uid ente proprietario
	 * 
	 * @param uidPadre
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT classificatore.*, rtree.* " + 
			" FROM siac_t_class classificatore  " + 
			" JOIN siac_t_ente_proprietario ep ON classificatore.ente_proprietario_id = ep.ente_proprietario_id AND ep.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac_d_class_tipo classificatoreTipo ON classificatore.classif_tipo_id = classificatoreTipo.classif_tipo_id  " + 
			" JOIN siac.siac_r_class_fam_tree rtree ON rtree.classif_id = classificatore.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = rtree.classif_fam_tree_id  " + 
			" WHERE rtree.classif_id_padre = ( " + 
			" 	SELECT srcft.classif_id_padre  " + 
			" 	FROM siac_t_class stc  " + 
			" 	JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" 	JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" 	JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" 	JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" 	WHERE srcft.classif_id = ( " + 
			" 		SELECT rfam.classif_id_padre  " + 
			" 		FROM siac_t_class c  " + 
			" 		JOIN siac_t_ente_proprietario ente ON c.ente_proprietario_id = ente.ente_proprietario_id AND ente.ente_proprietario_id = :enteProprietarioId " + 
			" 		JOIN siac_d_class_tipo tipo ON c.classif_tipo_id = tipo.classif_tipo_id  " + 
			" 		JOIN siac.siac_r_class_fam_tree rfam ON rfam.classif_id = c.classif_id  " + 
			" 		JOIN siac.siac_t_class_fam_tree fam ON fam.classif_fam_tree_id = rfam.classif_fam_tree_id  " + 
			" 		WHERE c.classif_id = :figlio " + 
			" 		AND fam..class_fam_code = 'Piano dei Conti'  " + 
			" 		AND c.data_cancellazione is NULL  " + 
			" 		AND ( c.validita_fine is NULL OR c.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 		AND rfam.data_cancellazione is NULL " + 
			" 		AND ( rfam.validita_fine is NULL OR rfam.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 	) " + 
			" 	AND stc.data_cancellazione is NULL  " + 
			" 	AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 	AND srcft.data_cancellazione is NULL " + 
			" 	AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " + 
			" ) " + 
			" AND classificatore.data_cancellazione is NULL  " + 
			" AND ( classificatore.validita_fine is NULL OR classificatore.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND rtree.data_cancellazione is NULL " + 
			" AND ( rtree.validita_fine is NULL OR rtree.validita_fine > CURRENT_TIMESTAMP ) " + 
			" ORDER BY classificatore.classif_code ", nativeQuery = true)
	List<SiacTClass> findParentSiblingsByChildUid(@Param("figlio")Integer uidfiglio, @Param("enteProprietarioId") Integer idEnte);

	/**
	 * ricerca i primi pdc passando l'uid del padre e uid ente proprietario
	 * 
	 * @param uidPadre
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac_t_class stc  " + 
			" JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" WHERE srcft.classif_id_padre = ( " + 
			" 	SELECT stcb.classif_id  " + 
			" 	FROM siac_t_class stc  " + 
			" 	JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" 	JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" 	JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" 	JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" 	JOIN siac.siac_r_class src ON stc.classif_id = src.classif_a_id  " + 
			" 	JOIN siac.siac_t_class stcb ON src.classif_b_id = stcb.classif_id  " + 
			" 	WHERE stc.classif_id = :idPadre " + 
//			"   AND stcft.class_fam_code = 'Piano dei Conti' " + 
			" 	AND stc.data_cancellazione is NULL  " + 
			" 	AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 	AND srcft.data_cancellazione is NULL " + 
			" 	AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " + 
			" 	ORDER BY stc.classif_code " + 
			" ) " + 
			" AND stcft.class_fam_code = 'Piano dei Conti' " + 
			" AND stc.data_cancellazione is NULL  " + 
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " + 
			" AND srcft.data_cancellazione is NULL " + 
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " + 
			" ORDER BY stc.classif_code", nativeQuery =  true)
	List<SiacTClass> findPdcByCategoriaMacroaggregato(@Param("idPadre")Integer uidPadre, @Param("enteProprietarioId") Integer idEnte);

	/**
	 * ricerca il quindo livello pdc passando l'uid ordinativo e uid ente proprietario
	 * 
	 * @param pdcOrdinativoCode
	 * @param enteProprietarioId
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.*, srcft.* " + 
			" FROM siac.siac_t_ordinativo sto    " + 
			" JOIN siac.siac_t_ente_proprietario step on sto.ente_proprietario_id = step.ente_proprietario_id and step.ente_proprietario_id = :enteProprietarioId   " + 
			" JOIN siac.siac_r_ordinativo_class sroc on sto.ord_id = sroc.ord_id   " + 
			" JOIN siac.siac_t_class stc on sroc.classif_id = stc.classif_id   " + 
			" JOIN siac.siac_d_class_tipo sdct on stc.classif_tipo_id = sdct.classif_tipo_id   " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON  srcft.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft  ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" WHERE sto.ord_id = :uidOrdinativo  " + 
			" AND srcft.data_cancellazione is NULL  " + 
			" AND stcft.class_fam_code = 'Piano dei Conti' " + 
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP )  " + 
			" AND sroc.data_cancellazione is NULL  " + 
			" AND ( sroc.validita_fine is NULL OR sroc.validita_fine > CURRENT_TIMESTAMP )  " + 
			" AND stc.data_cancellazione is NULL  " + 
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code", nativeQuery =  true)
	List<SiacTClass> findPdcQuintoLivelloDaOrdinativo(@Param("uidOrdinativo")Integer uidOrdinativo, @Param("enteProprietarioId") Integer idEnte);

	/**
	 * ricerca dei centri di responsabilita' per anno e uid ente proprietario e codice tipo
	 * 
	 * @param anno
	 * @param enteProprietarioId
	 * @param classificatoreTipoCode
	 * @return SiacTClass
	 */
	@Query(value = " SELECT DISTINCT c.*, srcft.* "
			+ " FROM siac_t_class c"
			+ " JOIN siac_t_ente_proprietario step ON c.ente_proprietario_id = step.ente_proprietario_id "
			+ " JOIN siac_r_class_fam_tree srcft ON c.classif_id = srcft.classif_id "
			+ " JOIN siac_t_class_fam_tree stcft ON srcft.classif_fam_tree_id = stcft.classif_fam_tree_id "
			+ " JOIN siac_d_class_fam sdcf ON stcft.classif_fam_id = sdcf.classif_fam_id "
			+ " JOIN siac_d_class_tipo sdct ON c.classif_tipo_id = sdct.classif_tipo_id "
			+ " WHERE step.ente_proprietario_id = :enteProprietarioId "
			+ " AND srcft.classif_id_padre IS NULL "
			+ " AND sdcf.classif_fam_code = '00005' "
			+ " AND sdct.classif_tipo_code = :classificatoreTipoCode "
			+ " AND srcft.data_cancellazione IS NULL "
			+ " AND c.data_cancellazione IS NULL "
			+ " AND c.validita_inizio <= CURRENT_TIMESTAMP "
			+ " AND ( c.validita_fine IS NULL OR c.validita_fine > CURRENT_TIMESTAMP ) "
			+ " ORDER BY c.classif_code, c.classif_desc desc", nativeQuery = true)
	List<SiacTClass> findStruttureAmministrativoContabiliCDR(@Param("enteProprietarioId") Integer enteProprietarioId, @Param("classificatoreTipoCode") String classificatoreTipoCode);
	
	/**
	 * ricerca dei centri di controllo per uidPadre centro di responsabilita' e uid ente proprietario e codice tipo
	 * 
	 * @param uidPadre
	 * @param enteProprietarioId
	 * @param classificatoreTipoCode
	 * @return SiacTClass
	 */
	@Query(value = " SELECT stc.* " + 
			" FROM siac_t_class stc  " + 
			" JOIN siac_t_ente_proprietario step ON stc.ente_proprietario_id = step.ente_proprietario_id AND step.ente_proprietario_id = :enteProprietarioId " + 
			" JOIN siac_d_class_tipo sdct ON stc.classif_tipo_id = sdct.classif_tipo_id  " + 
			" JOIN siac.siac_r_class_fam_tree srcft ON srcft.classif_id = stc.classif_id  " + 
			" JOIN siac.siac_t_class_fam_tree stcft ON stcft.classif_fam_tree_id = srcft.classif_fam_tree_id  " + 
			" WHERE srcft.classif_id_padre = :uidPadre " +
			" AND stcft.class_fam_code = 'Struttura Amministrativa Contabile' " + 
			" AND sdct.classif_tipo_code = :classificatoreTipoCode " +
			" AND stc.data_cancellazione is NULL " +
			" AND ( stc.validita_fine is NULL OR stc.validita_fine > CURRENT_TIMESTAMP ) " +
			" AND srcft.data_cancellazione is NULL " +
			" AND ( srcft.validita_fine is NULL OR srcft.validita_fine > CURRENT_TIMESTAMP ) " +
			" ORDER BY stc.classif_code ", nativeQuery = true)
	List<SiacTClass> findCentriDiControlloChildrenByParentCentroDiResponsabilitaUid(@Param("uidPadre") Integer uidPadre, @Param("enteProprietarioId") Integer enteProprietarioId, @Param("classificatoreTipoCode") String classificatoreTipoCode);

	
	@Query(value = "SELECT tc.classif_id FROM siac_t_class tc, siac_d_class_tipo sdct " + 
			"				WHERE tc.classif_code = :codiceSac " + 
			"				AND tc.ente_proprietario_id = :idEnte " + 
			"				AND tc.classif_tipo_id = sdct.classif_tipo_id " + 
			"				AND sdct.classif_tipo_code = 'CDC' "
			+ " AND tc.data_cancellazione IS NULL "
			+ " AND tc.validita_inizio <= CURRENT_TIMESTAMP "
			+ " AND (tc.validita_fine IS NULL OR tc.validita_fine > CURRENT_TIMESTAMP) "
			+ " AND sdct.data_cancellazione IS NULL "
			+ " AND sdct.validita_inizio <= CURRENT_TIMESTAMP "
			+ " AND (sdct.validita_fine IS NULL OR sdct.validita_fine > CURRENT_TIMESTAMP) ",
			nativeQuery = true) 
	Integer findSiacTClassUidByCodiceCDC(
			@Param("idEnte") Integer idEnte,
			@Param("codiceSac") String codiceSac
	);
	
}

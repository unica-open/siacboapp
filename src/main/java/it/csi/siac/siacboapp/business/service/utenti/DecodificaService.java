/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.business.service.utenti;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.integration.dao.utenti.decodifica.SiacDecodificaDao;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;
import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.repository.BkoTSystemTableRepository;
import it.csi.siac.siaccorser.model.Ente;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Transactional
public class DecodificaService {
	@Autowired
	private BkoTSystemTableRepository bkoTSystemTableRepository;

	@Autowired
	private SiacDecodificaDao decodificaDao;

	public List<BkoTSystemTable> getElencoTabelleDecodifica() {
		return bkoTSystemTableRepository.getTablesByType("d");
	}
	//SIAC-8092 si adegua la ricerca al filtro per le tabelle usate con relazioni ad altre tabelle
	public List<BkoTSystemTable> getElencoTabelleDecodificaUsate() {
		return bkoTSystemTableRepository.getTablesByTypeAndColumnName("d", "ente_proprietario_id");
	}

	public BkoTSystemTable getTabellaByName(String tableName) {
		return bkoTSystemTableRepository.getTableByName(tableName);
	}

	public BkoTSystemTable getTabella(Integer tableId) {
		return bkoTSystemTableRepository.getTable(tableId);
	}

	public BkoTSystemTable getDettaglioTabella(Integer tableId) {
		return bkoTSystemTableRepository.getTableWithColumns(tableId);
	}

	public List<SiacDecodifica> getElencoDecodifiche(Integer tableId, Ente ente) {
		SiacDecodifica filter = new SiacDecodifica();
		filter.setEnteProprietario(new SiacTEnteProprietario(ente.getUid()));
		filter.getEnteProprietario().setUid(ente.getUid());

		return decodificaDao.getElencoDecodifiche(bkoTSystemTableRepository.getTable(tableId), filter);
	}

	public List<SiacDecodifica> getElencoDecodifiche(BkoTSystemTable table, Ente ente, Map<String, Object> values) {
		SiacDecodifica filter = new SiacDecodifica();
		filter.setEnteProprietario(new SiacTEnteProprietario(ente.getUid()));
		filter.setValues(values);

		return decodificaDao.getElencoDecodifiche(table, filter);
	}

	public SiacDecodifica read(Integer uid, Ente ente, Integer tableId) {
		SiacDecodifica filter = new SiacDecodifica();
		filter.setEnteProprietario(new SiacTEnteProprietario());
		filter.getEnteProprietario().setUid(ente.getUid());
		filter.setUid(uid);

		BkoTSystemTable table = bkoTSystemTableRepository.getTableWithColumns(tableId);

		SiacDecodifica decodifica = decodificaDao.getDecodifica(table, filter);

		decodifica.setTabella(table);

		return decodifica;
	}

	public void create(SiacDecodifica decodifica, Ente ente, String loginOperazione) {
		decodificaDao.create(decodifica, ente.getUid(), loginOperazione,
				bkoTSystemTableRepository.getTable(decodifica.getTabella().getId()));
	}

	public void update(SiacDecodifica decodifica) {
		decodificaDao.update(decodifica, bkoTSystemTableRepository.getTable(decodifica.getTabella().getId()));

	}

	public void delete(SiacDecodifica decodifica) {
		decodificaDao.delete(decodifica.getUid(), bkoTSystemTableRepository.getTable(decodifica.getTabella().getId()));
	}

	public Map<String, List<SiacDecodifica>> getForeignKeyValues(BkoTSystemTable tabella, Ente ente) {
		SiacTEnteProprietario siacTEnteProprietario = new SiacTEnteProprietario();
		siacTEnteProprietario.setUid(ente.getUid());

		return decodificaDao.getForeignKeyValues(tabella, siacTEnteProprietario);
	}

}

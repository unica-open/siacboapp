/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.utenti.decodifica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.PersistenceUnitUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemColumn;
import it.csi.siac.siacboapp.integration.entity.BkoTSystemTable;
import it.csi.siac.siacboapp.integration.entity.SiacDecodifica;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.repository.BkoTSystemColumnRepository;

@Component
public class SiacDecodificaDaoImpl extends BoBaseDaoImpl<SiacDecodifica> implements SiacDecodificaDao {
	@Autowired
	private BkoTSystemColumnRepository bkoTSystemColumnRepository;

	@Override
	public void create(SiacDecodifica decodifica, int idEnte, String loginOperazione, BkoTSystemTable table) {
		List<String> colNames = new ArrayList<String>();
		List<String> colValues = new ArrayList<String>();

		Map<String, Object> values = decodifica.getValues();
		Map<String, Object> params = new HashMap<String, Object>();

		for (BkoTSystemColumn column : getTableColumns(table)) {
			if (!column.isPrimaryKey() && values.containsKey(column.getName())) {
				colNames.add(column.getName());
				colValues.add(String.format("CAST(:%s AS %s)", column.getName(), column.getType()));
				params.put(column.getName(), ((String[]) values.get(column.getName()))[0]);
			}
		}

		executeNativeQuery(String.format("INSERT INTO %s (%s, validita_inizio, login_operazione) "
				+ " VALUES (%s, current_date, '%s')", table.getName(), StringUtils.join(colNames, ", "),
				StringUtils.join(colValues, ", "), loginOperazione), params);
	}

	@Override
	public void update(SiacDecodifica decodifica, BkoTSystemTable table) {
		List<String> colNamesValue = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();

		String uidClause = null;

		Map<String, Object> values = decodifica.getValues();

		for (BkoTSystemColumn column : getTableColumns(table)) {
			if (column.isPrimaryKey())
				uidClause = String.format(" %s=%d ", column.getName(), decodifica.getUid());
			else if (values.containsKey(column.getName())) {
				colNamesValue.add(String.format("%s=CAST(:%s AS %s)", column.getName(), column.getName(),
						column.getType()));
				params.put(column.getName(), ((String[]) values.get(column.getName()))[0]);
			}
		}

		if (uidClause != null)
			executeNativeQuery(String.format("UPDATE %s SET %s, data_modifica=current_date " + " WHERE %s",
					table.getName(), StringUtils.join(colNamesValue, ", "), uidClause), params);
		else
			throw new IllegalStateException();
	}

	@Override
	public void delete(Integer idDecodifica, BkoTSystemTable table) {
		String uidClause = null;

		for (BkoTSystemColumn column : getTableColumns(table)) {
			if (column.isPrimaryKey()) {
				uidClause = String.format(" %s=%d ", column.getName(), idDecodifica);

				break;
			}
		}

		if (uidClause != null)
			executeNativeQuery(
					String.format("UPDATE %s SET data_cancellazione=current_date WHERE %s", table.getName(), uidClause),
					null);
		else
			throw new IllegalStateException();
	}

	@Override
	public List<SiacDecodifica> getElencoDecodifiche(BkoTSystemTable table, SiacDecodifica filter) {
		List<String> cols = new ArrayList<String>();
		String uidClause = null;

		for (BkoTSystemColumn column : getTableColumns(table)) {
			String colStr = column.getName();

			if (column.isPrimaryKey() && filter.getUid() != null)
				uidClause = String.format(" AND %s=%d ", colStr, filter.getUid());

			cols.add(colStr);
		}

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("SELECT %s FROM %s WHERE data_cancellazione IS NULL AND ente_proprietario_id=%d",
				StringUtils.join(cols, ", "), table.getName(), filter.getEnteProprietario().getUid()));

		if (uidClause != null)
			sql.append(uidClause);
		
		if (!filter.getValues().isEmpty())
			for (Entry<String, Object> e : filter.getValues().entrySet())
				sql.append(String.format(" AND %s='%s' ", e.getKey(), e.getValue().toString()));

		@SuppressWarnings("unchecked")
		List<Object[]> recs = createNativeQuery(sql.toString(), null).setHint("javax.persistence.cache.retrieveMode",
				CacheRetrieveMode.BYPASS).getResultList();

		List<SiacDecodifica> list = new ArrayList<SiacDecodifica>();

		for (Object[] rec : recs) {
			SiacDecodifica siacDecodifica = new SiacDecodifica();

			int i = 0;

			for (BkoTSystemColumn column : getTableColumns(table)) {
				if (rec[i] != null) {
					siacDecodifica.setValue(column.getName(), rec[i]);

					if (column.isPrimaryKey())
						siacDecodifica.setUid(Integer.valueOf(rec[i].toString()));
				}

				i++;
			}

			list.add(siacDecodifica);
		}

		return list;
	}

	@Override
	public SiacDecodifica getDecodifica(BkoTSystemTable table, SiacDecodifica filter) {
		SiacDecodifica decodifica = getElencoDecodifiche(table, filter).get(0);

		decodifica.setForeignKeyValues(getForeignKeyValues(table, filter.getEnteProprietario()));

		return decodifica;
	}

	@Override
	public Map<String, List<SiacDecodifica>> getForeignKeyValues(BkoTSystemTable table,
			SiacTEnteProprietario siacTEnteProprietario) {
		SiacDecodifica filter = new SiacDecodifica();
		filter.setEnteProprietario(siacTEnteProprietario);

		Map<String, List<SiacDecodifica>> foreignKeyValues = new HashMap<String, List<SiacDecodifica>>();

		for (BkoTSystemColumn column : getTableColumns(table))
			if (column.hasForeignKey())
				foreignKeyValues.put(column.getName(),
						getElencoDecodifiche(column.getReferencingColumn().getTable(), filter));

		return foreignKeyValues;
	}

	private Collection<BkoTSystemColumn> getTableColumns(BkoTSystemTable table) {
		PersistenceUnitUtil persistenceUnitUtil = getPersistenceUnitUtil();

		if (persistenceUnitUtil.isLoaded(table, "columns"))
			return table.getColumns();

		return bkoTSystemColumnRepository.getTableColumns(table.getId());
	}
}

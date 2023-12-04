/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.indicatori;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriBase;

@Component
public class SiacTConfIndicatoriDaoImpl extends BoBaseDaoImpl<SiacTConfIndicatoriBase> implements SiacTConfIndicatoriDao {

	@Override
	public void updateIndicatori(String entity, Integer uid, List<String> fields, List<BigDecimal> values) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> fieldValueSet = new ArrayList<String>();

		Iterator<BigDecimal> valuesIt = values.iterator();
		
		for (Iterator<String> fieldsIt = fields.iterator(); fieldsIt.hasNext();) {
			String field = fieldsIt.next();

			fieldValueSet.add(String.format("%s=:%s", field, field));
			params.put(field, valuesIt.next());
		}
		
		String sql = String.format("UPDATE %s SET %s WHERE uid=%d", entity, StringUtils.join(fieldValueSet, ", "), uid);
		
		createQuery(sql, params).executeUpdate();
	}
}

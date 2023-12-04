/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dad.mapper.base;

import java.util.Map;

public abstract interface MapToEntityWrapperMapper {
	public void map(Map<String, Object> m, Object ow);
}

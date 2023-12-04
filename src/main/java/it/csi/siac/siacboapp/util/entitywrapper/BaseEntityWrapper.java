/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.util.entitywrapper;

import java.io.Serializable;

public abstract class BaseEntityWrapper implements Serializable {

	private static final long serialVersionUID = -5207996627411986615L;
	
	private Integer uid;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
}

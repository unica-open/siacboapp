/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.file;

import it.csi.siac.siaccommonapp.model.GenericPagedModel;
import it.csi.siac.siaccorser.model.file.File;

public class RisultatiRicercaFileModel extends GenericPagedModel<File> {
	private static final long serialVersionUID = 8171137173699108259L;

	private Integer indexAzioneTipoFile;

	public Integer getIndexAzioneTipoFile() {
		return indexAzioneTipoFile;
	}

	public void setIndexAzioneTipoFile(Integer indexAzioneTipoFile) {
		this.indexAzioneTipoFile = indexAzioneTipoFile;
	}
}

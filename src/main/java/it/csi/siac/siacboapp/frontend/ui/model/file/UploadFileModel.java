/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.frontend.ui.model.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.csi.siac.siaccommonapp.model.GenericModel;
import it.csi.siac.siaccorser.model.file.TipoFile;

public class UploadFileModel extends GenericModel {
	private static final long serialVersionUID = -502269282209388246L;

	private List<TipoFile> elencoTipoFile = new ArrayList<TipoFile>();
	private String codice;
	private Integer idTipo;
	private String note;
	private File file;
	private String fileContentType;
	private String fileFileName;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<TipoFile> getElencoTipoFile() {
		return elencoTipoFile;
	}

	public void setElencoTipoFile(List<TipoFile> elencoTipoFile) {
		this.elencoTipoFile = elencoTipoFile;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}

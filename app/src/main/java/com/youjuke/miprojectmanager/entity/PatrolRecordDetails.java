package com.youjuke.miprojectmanager.entity;

import java.io.Serializable;
import java.util.List;

public class PatrolRecordDetails implements Serializable {

	private int status;
	private int error_code;
	private List<PatrolRecordData> data;

	public PatrolRecordDetails() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public List<PatrolRecordData> getData() {
		return data;
	}

	public void setData(List<PatrolRecordData> data) {
		this.data = data;
	}

}

package com.unitedvision.sangihe.ehrm.simpeg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "riwayat_jabatan")
public class RiwayatJabatan extends Riwayat {

	private Jabatan jabatan;
	
	public RiwayatJabatan() {
		super();
	}

	@Column(name = "jabatan", nullable = false)
	public Jabatan getJabatan() {
		return jabatan;
	}

	public void setJabatan(Jabatan jabatan) {
		this.jabatan = jabatan;
	}
}
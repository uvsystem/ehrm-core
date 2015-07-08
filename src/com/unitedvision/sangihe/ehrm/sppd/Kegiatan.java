package com.unitedvision.sangihe.ehrm.sppd;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Digunakan hanya untuk mapping, bukan untuk pengolahan data kegiatan.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public class Kegiatan {

	private long id;
	private String nama;
	
	private List<Sppd> daftarSppd;

	public Kegiatan() {
		super();
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "nama")
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}

	@OneToMany(mappedBy = "kegiatan", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	public List<Sppd> getDaftarSppd() {
		return daftarSppd;
	}

	public void setDaftarSppd(List<Sppd> daftarSppd) {
		this.daftarSppd = daftarSppd;
	}

}
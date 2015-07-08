package com.unitedvision.sangihe.ehrm.simpeg;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unitedvision.sangihe.ehrm.absensi.Cuti;
import com.unitedvision.sangihe.ehrm.absensi.Hadir;
import com.unitedvision.sangihe.ehrm.absensi.Izin;
import com.unitedvision.sangihe.ehrm.absensi.Sakit;
import com.unitedvision.sangihe.ehrm.absensi.TugasLuar;
import com.unitedvision.sangihe.ehrm.duk.Penduduk;
import com.unitedvision.sangihe.ehrm.duk.Penduduk.Kontak;
import com.unitedvision.sangihe.ehrm.manajemen.Operator;
import com.unitedvision.sangihe.ehrm.manajemen.Token;
import com.unitedvision.sangihe.ehrm.sppd.PemegangTugas;

@Entity
@Table(name = "pegawai")
public class Pegawai implements Pejabat {

	private long id;
	private String nip;
	private Penduduk penduduk;
	private UnitKerja unitKerja;
	private String password;

	private List<RiwayatPangkat> daftarPangkat;
	private List<RiwayatJabatan> daftarJabatan;
	private List<Token> daftarToken;
	private List<Operator> daftarOperator;
	private List<PemegangTugas> daftarTugas;

	private List<Hadir> daftarHadir;
	private List<TugasLuar> daftarTugasLuar;
	private List<Sakit> daftarSakit;
	private List<Izin> daftarIzin;
	private List<Cuti> daftarCuti;
	
	public Pegawai() {
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

	@Column(name = "nip", unique = true, nullable = false)
	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Penduduk getPenduduk() {
		return penduduk;
	}

	public void setPenduduk(Penduduk penduduk) {
		this.penduduk = penduduk;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_kerja", nullable = false)
	public UnitKerja getUnitKerja() {
		return unitKerja;
	}

	public void setUnitKerja(UnitKerja unitKerja) {
		this.unitKerja = unitKerja;
	}

	@JsonIgnore
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<RiwayatPangkat> getDaftarPangkat() {
		return daftarPangkat;
	}

	public void setDaftarPangkat(List<RiwayatPangkat> daftarPangkat) {
		this.daftarPangkat = daftarPangkat;
	}

	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<RiwayatJabatan> getDaftarJabatan() {
		return daftarJabatan;
	}

	public void setDaftarJabatan(List<RiwayatJabatan> daftarJabatan) {
		this.daftarJabatan = daftarJabatan;
	}

	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Operator> getDaftarOperator() {
		return daftarOperator;
	}

	public void setDaftarOperator(List<Operator> daftarOperator) {
		this.daftarOperator = daftarOperator;
	}

	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<PemegangTugas> getDaftarTugas() {
		return daftarTugas;
	}

	public void setDaftarTugas(List<PemegangTugas> daftarTugas) {
		this.daftarTugas = daftarTugas;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	public List<Token> getDaftarToken() {
		return daftarToken;
	}

	public void setDaftarToken(List<Token> daftarToken) {
		this.daftarToken = daftarToken;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Hadir> getDaftarHadir() {
		return daftarHadir;
	}

	public void setDaftarHadir(List<Hadir> daftarHadir) {
		this.daftarHadir = daftarHadir;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<TugasLuar> getDaftarTugasLuar() {
		return daftarTugasLuar;
	}

	public void setDaftarTugasLuar(List<TugasLuar> daftarTugasLuar) {
		this.daftarTugasLuar = daftarTugasLuar;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Sakit> getDaftarSakit() {
		return daftarSakit;
	}

	public void setDaftarSakit(List<Sakit> daftarSakit) {
		this.daftarSakit = daftarSakit;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Izin> getDaftarIzin() {
		return daftarIzin;
	}

	public void setDaftarIzin(List<Izin> daftarIzin) {
		this.daftarIzin = daftarIzin;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "pegawai", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<Cuti> getDaftarCuti() {
		return daftarCuti;
	}

	public void setDaftarCuti(List<Cuti> daftarCuti) {
		this.daftarCuti = daftarCuti;
	}

	@JsonIgnore
	@Transient
	public RiwayatPangkat getPangkatTerakhir() {
		return daftarPangkat.get(daftarPangkat.size());
	}

	@Transient
	public Pangkat getPangkat() {
		return getPangkatTerakhir().getPangkat();
	}

	@JsonIgnore
	@Transient
	public RiwayatJabatan getJabatanTerakhir() {
		return daftarJabatan.get(daftarJabatan.size());
	}
	
	@Override
	@Transient
	public Jabatan getJabatan() {
		return getJabatanTerakhir().getJabatan();
	}

	@Override
	@Transient
	public Eselon getEselon() {
		return getJabatan().getEselon();
	}

	@Override
	@Transient
	public Date tanggalMulai() {
		return getJabatanTerakhir().getTanggalMulai();
	}

	@Transient
	public String getNik() {
		return penduduk.getNik();
	}

	public void setNik(String nik) {
		penduduk.setNik(nik);
	}

	@Transient
	public String getNama() {
		return penduduk.getNama();
	}

	public void setNama(String nama) {
		penduduk.setNama(nama);
	}

	@Transient
	public Date getTanggalLahir() {
		return penduduk.getTanggalLahir();
	}

	public void setTanggalLahir(Date tanggalLahir) {
		penduduk.setTanggalLahir(tanggalLahir);
	}

	@Transient
	public Kontak getKontak() {
		return penduduk.getKontak();
	}

	public void setKontak(Kontak kontak) {
		penduduk.setKontak(kontak);
	}
	
}

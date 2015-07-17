package com.unitedvision.sangihe.ehrm;

import com.unitedvision.sangihe.ehrm.simpeg.Jabatan;
import com.unitedvision.sangihe.ehrm.simpeg.Pegawai;
import com.unitedvision.sangihe.ehrm.simpeg.UnitKerja;

public class EntityRestMessage<T> extends RestMessage {
	private T model;
	
	protected EntityRestMessage(Exception ex) {
		super(ex);
	}
	
	protected EntityRestMessage(T model) {
		super("Berhasil", Type.ENTITY, model);
		this.model = model;
	}
	
	public T getModel() {
		return model;
	}
	
	public static <T> EntityRestMessage<T> entityError(Exception cause) {
		return new EntityRestMessage<T>(cause);
	}
	
	public static EntityRestMessage<UnitKerja> create(UnitKerja unitKerja) {
		return new EntityRestMessage<UnitKerja>(unitKerja);
	}
	
	public static EntityRestMessage<Jabatan> create(Jabatan jabatan) {
		return new EntityRestMessage<Jabatan>(jabatan);
	}
	
	public static EntityRestMessage<Pegawai> create(Pegawai pegawai) {
		return new EntityRestMessage<Pegawai>(pegawai);
	}

}
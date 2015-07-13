package com.unitedvision.sangihe.ehrm.test.service;

import static org.junit.Assert.*;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.ehrm.ApplicationConfig;
import com.unitedvision.sangihe.ehrm.EntityNotExistException;
import com.unitedvision.sangihe.ehrm.simpeg.SubUnitKerja;
import com.unitedvision.sangihe.ehrm.simpeg.UnitKerja;
import com.unitedvision.sangihe.ehrm.simpeg.UnitKerjaService;
import com.unitedvision.sangihe.ehrm.simpeg.UnitKerja.TipeUnitKerja;
import com.unitedvision.sangihe.ehrm.simpeg.repository.SubUnitKerjaRepository;
import com.unitedvision.sangihe.ehrm.simpeg.repository.UnitKerjaRepository;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class UnitKerjaServiceTest {

	@Autowired
	private UnitKerjaService unitKerjaService;
	@Autowired
	private UnitKerjaRepository unitKerjaRepository;
	@Autowired
	private SubUnitKerjaRepository subUnitKerjaRepository;

	private UnitKerja unitKerja;
	private long countUnitKerja;
	private long countSubUnitKerja;
	
	@Before
	public void setup() {
		countUnitKerja = unitKerjaRepository.count();
		
		unitKerja = new UnitKerja();
		unitKerja.setNama("Sekretariat Daerah");
		unitKerja.setSingkatan("SETDA");
		unitKerja.setTipe(TipeUnitKerja.SEKRETARIAT);
		
		unitKerjaService.simpan(unitKerja);
		
		assertEquals(countUnitKerja + 1, unitKerjaRepository.count());
	}
	
	@Test(expected = PersistenceException.class)
	public void test_simpan_duplicate() {
		UnitKerja unitKerja = new UnitKerja();
		unitKerja.setNama("Sekretariat Daearah");
		unitKerja.setSingkatan("SETDA");
		unitKerja.setTipe(TipeUnitKerja.SEKRETARIAT);
		
		unitKerjaService.simpan(unitKerja);
		
		assertEquals(countUnitKerja + 1, unitKerjaRepository.count());
	}
	
	@Test
	public void test_get_by_singkatan() throws EntityNotExistException {
		UnitKerja unitKerja = unitKerjaService.get("SETDA");
		
		assertNotNull(unitKerja);
	}
	
	@Test
	public void test_tambah_sub_unit_kerja() {
		SubUnitKerja subUnitKerja = new SubUnitKerja();
		subUnitKerja.setNama("Pengelola Data Elektronik");
		subUnitKerja.setSingkatan("BPDE");
		subUnitKerja.setTipe(TipeUnitKerja.BAGIAN);
		subUnitKerja.setUnitKerja(unitKerja);
		
		unitKerjaService.simpan(subUnitKerja);
		
		assertEquals(countSubUnitKerja + 1, subUnitKerjaRepository.count());
	}
}
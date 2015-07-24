package com.unitedvision.sangihe.ehrm.simpeg;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.sangihe.ehrm.ApplicationException;
import com.unitedvision.sangihe.ehrm.EntityRestMessage;
import com.unitedvision.sangihe.ehrm.ListEntityRestMessage;
import com.unitedvision.sangihe.ehrm.RestMessage;

@Controller
@RequestMapping("/satker")
public class UnitKerjaController {

	@Autowired
	private UnitKerjaService unitKerjaService;
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public RestMessage save(@RequestBody UnitKerja unitKerja) throws ApplicationException, PersistenceException {
		unitKerjaService.simpan(unitKerja);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{kode}")
	@ResponseBody
	public RestMessage delete(@PathVariable String kode) throws ApplicationException, PersistenceException {
		unitKerjaService.hapus(kode);
		
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EntityRestMessage<UnitKerja> findOne(@PathVariable Long id) throws ApplicationException, PersistenceException {
		UnitKerja unitKerja = unitKerjaService.get(id);
		
		return EntityRestMessage.create(unitKerja);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/kode/{kode}")
	@ResponseBody
	public EntityRestMessage<UnitKerja> find(@PathVariable String kode) throws ApplicationException, PersistenceException {
		UnitKerja unitKerja = unitKerjaService.get(kode);
		
		return EntityRestMessage.create(unitKerja);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search/{keyword}")
	@ResponseBody
	public ListEntityRestMessage<UnitKerja> cari(@PathVariable String keyword) throws ApplicationException, PersistenceException {
		List<UnitKerja> daftarUnitKerja = unitKerjaService.cari(keyword);
		
		return ListEntityRestMessage.createListUnitKerja(daftarUnitKerja);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sub/{id}")
	@ResponseBody
	public ListEntityRestMessage<SubUnitKerja> findSubUnit(@PathVariable Long id) throws ApplicationException, PersistenceException {
		List<SubUnitKerja> daftarSubUnitKerja = unitKerjaService.getSubUnitKerja(id);
		
		return ListEntityRestMessage.createListSubUnitKerja(daftarSubUnitKerja);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ListEntityRestMessage<UnitKerja> get() throws ApplicationException, PersistenceException {
		List<UnitKerja> daftarUnitKerja = unitKerjaService.get();
		
		return ListEntityRestMessage.createListUnitKerja(daftarUnitKerja);
	}

}

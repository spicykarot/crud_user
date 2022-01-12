package com.user.crud.controller;

import java.util.List;

import com.user.crud.service.AddressService;
import com.user.crud.model.Address;
import com.user.crud.model.AddressModel;
import com.user.crud.model.ThDistrict;
import com.user.crud.model.ThProvince;
import com.user.crud.model.ThSubdistrict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/getProvince")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ThProvince>> getAllProvince() {
        List<ThProvince> list = addressService.getAllProvince();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getDistrict")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ThDistrict>> getAllDistrict() {
        List<ThDistrict> list = addressService.getAllDistrict();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getSubdistrict")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ThSubdistrict>> getAllSubdistrict() {
        List<ThSubdistrict> list = addressService.getAllSubdistrict();
        return ResponseEntity.ok(list);
    }

    // ใช้จังหวัดเรียกอำเภอ
    @GetMapping("getDistrict/{provinceId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ThDistrict>> getDistrictByProvinceId(@PathVariable("provinceId") Long provinceId) {
        List<ThDistrict> entity = addressService.getDistrict(provinceId);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("getDistrictByName")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ThDistrict> getDistrictByName(@RequestParam("districtName") String districtName) {
        ThDistrict entity = addressService.getDistrictByName(districtName);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("searchDistrictByName")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ThDistrict> searchDistrictByName(@RequestParam("districtName") String districtName) {
        ThDistrict entity = addressService.searchDistrictByName(districtName);
        return ResponseEntity.ok(entity);
    }

    // forward
    // ใช้อำเภอเรียกตำบล
    @GetMapping("getSubdistrict/{districtId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<ThSubdistrict>> getSubdistrictByDistrictId(
            @PathVariable("districtId") Long districtId) {
        List<ThSubdistrict> entity = addressService.getSubDistrict(districtId);
        return ResponseEntity.ok(entity);
    }

    @RequestMapping("/findAddressAll")
    public List<Address> findAddressAll() {
        return addressService.findAddressAll();
    }

    @RequestMapping(value = "/getAddressAll", method = RequestMethod.POST)
    public @ResponseBody List<AddressModel> getAddressAll() {

        List<AddressModel> result = addressService.findAddressModelAll();
        return result;
    }

    @RequestMapping(value = "/getAddressById/{id}", method = RequestMethod.POST)
    public @ResponseBody AddressModel getAddressById(@PathVariable int id) {

        AddressModel result = addressService.findAddressModelById(id);
        return result;
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public @ResponseBody Boolean addAddress(@RequestBody AddressModel Address) {
        return addressService.InsertAddress(Address);
    }

    @RequestMapping(value = "/updateAddress", method = RequestMethod.PUT)
    public @ResponseBody Boolean updateAddress(@RequestBody AddressModel Address) {
        return addressService.UpdateAddress(Address);
    }

    @RequestMapping(value = "/deleteAddress/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteAddress(@PathVariable long id) {
        return addressService.deleteAddressById(id);
    }
}

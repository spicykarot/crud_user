package com.user.crud.service;

import com.user.crud.model.Address;
import com.user.crud.model.AddressModel;
import com.user.crud.model.AddressType;
import com.user.crud.model.ThDistrict;
import com.user.crud.model.ThProvince;
import com.user.crud.model.ThSubdistrict;
import com.user.crud.model.User;
import com.user.crud.repository.AddressRepository;
import com.user.crud.repository.AddressTypeRepository;
import com.user.crud.repository.ThDistrictRepository;
import com.user.crud.repository.ThProvinceRepository;
import com.user.crud.repository.ThSubdistrictRepository;
import com.user.crud.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressTypeRepository addressTypeRepository;
	@Autowired
	private ThProvinceRepository provinceRepository;
	@Autowired
	private ThDistrictRepository districtRepository;
	@Autowired
	private ThSubdistrictRepository subdistrictRepository;
	@Autowired
	private UserRepository userRepository;

	public List<ThProvince> getAllProvince() {
		return this.provinceRepository.findAll();
	}

	public List<ThDistrict> getAllDistrict() {
		return this.districtRepository.findAll();
	}

	// ******** */
	public List<ThSubdistrict> getAllSubdistrict() {
		return this.subdistrictRepository.findAll();
	}

	public List<ThDistrict> getDistrict(Long provinceId) {
		return this.districtRepository.findByProvinceId(provinceId);
	}

	public ThDistrict getDistrictByName(String districtName) {
		return this.districtRepository.findByNameTh(districtName);
	}

	public ThDistrict searchDistrictByName(String districtName) {
		return this.districtRepository.searchNameTh(districtName);
	}

	// forward
	public List<ThSubdistrict> getSubDistrict(Long districtId) {
		return this.subdistrictRepository.findByDistrictId(districtId);
	}

	// Address
	public List<Address> findAddressAll() {
		return addressRepository.findAll();
	}

	public Address findAddressById(long id) {
		return addressRepository.findById(id);
	}

	// Entity to Model
	public List<AddressModel> findAddressModelAll() {
		List<AddressModel> AddressModelList = new ArrayList<AddressModel>();
		for (Address address : findAddressAll()) {

			AddressModel AddressModel = new AddressModel();

			AddressModel.setId(address.getId());
			AddressModel.setAddressNo(address.getAddressNo());
			AddressModel.setAddressTypeId(address.getAddressType().getId());
			AddressModel.setProvinceId(address.getProvinceId().getId());
			AddressModel.setDistrictId(address.getDistrictId().getId());
			AddressModel.setSubDistrictId(address.getSubDistrictId().getId());
			AddressModel.setFullAddress(address.getFullAddress());
			//AddressModel.setUserId(address.getUserId().getId());
			AddressModelList.add(AddressModel);
		}
		return AddressModelList;
	}

	// Entity to Model
	public AddressModel findAddressModelById(long id) {

		Address address = findAddressById(id);

		AddressModel AddressModel = new AddressModel();

		AddressModel.setId(address.getId());
		AddressModel.setAddressNo(address.getAddressNo());
		AddressModel.setAddressTypeId(address.getAddressType().getId());
		AddressModel.setProvinceId(address.getProvinceId().getId());
		AddressModel.setDistrictId(address.getDistrictId().getId());
		AddressModel.setSubDistrictId(address.getSubDistrictId().getId());
		//AddressModel.setUserId(address.getUserId().getId());
		AddressModel.setFullAddress(address.getFullAddress());
		

		return AddressModel;
	}

	// Model to Entity
	public Boolean InsertAddress(AddressModel AddressModel) {
		if (AddressModel != null) {
			Address AddressTb = new Address();

			AddressType addressTypeTb = addressTypeRepository.getById(AddressModel.getAddressTypeId());
			ThProvince provinceTb = provinceRepository.getById(AddressModel.getProvinceId());
			ThDistrict districtTb = districtRepository.getById(AddressModel.getDistrictId());
			ThSubdistrict subdistrictTb = subdistrictRepository.getById(AddressModel.getSubDistrictId());
			//User userTb = userRepository.getById(AddressModel.getUserId());
			

			AddressTb.setAddressNo(AddressModel.getAddressNo());
			
			AddressTb.setAddressType(addressTypeTb);
			AddressTb.setProvinceId(provinceTb);
			AddressTb.setDistrictId(districtTb);
			AddressTb.setSubDistrictId(subdistrictTb);
			//AddressTb.setUserId(userTb);

			addressRepository.save(AddressTb);
			
			return true;
		}
		return false;

	}

	// Model to Entity
	public Boolean UpdateAddress(AddressModel AddressModel) {

		try {
			Address AddressTb = findAddressById(AddressModel.getId());
			if (AddressTb != null) {
				if (!ObjectUtils.isEmpty(AddressModel.getAddressNo())) {
					AddressTb.setAddressNo(AddressModel.getAddressNo());
				}

				if (!ObjectUtils.isEmpty(AddressModel.getAddressTypeId())) {
					AddressType addressTypeTb = addressTypeRepository.getById(AddressModel.getAddressTypeId());
					AddressTb.setAddressType(addressTypeTb);
				}

				if (!ObjectUtils.isEmpty(AddressModel.getProvinceId())) {
					ThProvince provinceTb = provinceRepository.getById(AddressModel.getProvinceId());
					AddressTb.setProvinceId(provinceTb);
				}

				if (!ObjectUtils.isEmpty(AddressModel.getDistrictId())) {
					ThDistrict districtTb = districtRepository.getById(AddressModel.getDistrictId());
					AddressTb.setDistrictId(districtTb);
				}

				if (!ObjectUtils.isEmpty(AddressModel.getSubDistrictId())) {
					ThSubdistrict subdistrictTb = subdistrictRepository.getById(AddressModel.getSubDistrictId());
					AddressTb.setSubDistrictId(subdistrictTb);
				}
				if (!ObjectUtils.isEmpty(AddressModel.getUserId())) {
					User userTb = userRepository.getById( AddressModel.getUserId());
					//AddressTb.setUserId(userTb);
				}
				addressRepository.save(AddressTb);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("On Error: " + e);
			return false;
		}

	}

	public String deleteAddressById(long id) {
		try {
			addressRepository.deleteById(id);
			return "Address Deleted Successfully";
		} catch (Exception e) {
			return e + "";
		}
	}

}

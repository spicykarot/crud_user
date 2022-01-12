package com.user.crud.service;

import java.util.ArrayList;
import java.util.List;

import com.user.crud.model.Address;
import com.user.crud.model.AddressModel;
import com.user.crud.model.AddressType;
import com.user.crud.model.ThDistrict;
import com.user.crud.model.ThProvince;
import com.user.crud.model.ThSubdistrict;
import com.user.crud.model.User;
import com.user.crud.model.UserModel;
import com.user.crud.repository.AddressRepository;
import com.user.crud.repository.AddressTypeRepository;
import com.user.crud.repository.ThDistrictRepository;
import com.user.crud.repository.ThProvinceRepository;
import com.user.crud.repository.ThSubdistrictRepository;
import com.user.crud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserCrudService {
	
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

    // User
    public List<User> findUserAll() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    // Entity to Model
    public List<UserModel> findUserModelAll() {
        List<UserModel> userModelList = new ArrayList<UserModel>();
        for (User userTb : findUserAll()) {

            UserModel userModel = new UserModel();
            List<AddressModel> addressModelList = new ArrayList<AddressModel>();

            userModel.setId(userTb.getId());
            userModel.setTitle(userTb.getTitle());
            userModel.setFname(userTb.getFname());
            userModel.setLname(userTb.getLname());

            for (Address addressTb : userTb.getAddress()) {
                AddressModel addressModel = new AddressModel();
                addressModel.setId(addressTb.getId());
                addressModel.setAddressNo(addressTb.getAddressNo());
                addressModel.setAddressTypeId(addressTb.getAddressType().getId());
                addressModel.setProvinceId(addressTb.getProvinceId().getId());
                addressModel.setDistrictId(addressTb.getDistrictId().getId());
                addressModel.setSubDistrictId(addressTb.getSubDistrictId().getId());
                addressModel.setUserId(addressTb.getUserId().getId());
                addressModel.setFullAddress(addressTb.getFullAddress());

                addressModelList.add(addressModel);
            }
            // userModel.setAddress(userTb.getAddress());
            // UserModel.setUserId(User.getUserId().getId());
            userModel.setAddressModelList(addressModelList);
            userModelList.add(userModel);
        }
        return userModelList;
    }

    // Entity to Model
    public UserModel findUserModelById(long id) {

        User userTb = findUserById(id);
        UserModel userModel = new UserModel();
        List<AddressModel> addressModelList = new ArrayList<AddressModel>();

        userModel.setId(userTb.getId());
        userModel.setTitle(userTb.getTitle());
        userModel.setFname(userTb.getFname());
        userModel.setLname(userTb.getLname());

        for (Address addressTb : userTb.getAddress()) {
            AddressModel addressModel = new AddressModel();
            
            addressModel.setId(addressTb.getId());
            addressModel.setAddressNo(addressTb.getAddressNo());
            addressModel.setAddressTypeId(addressTb.getAddressType().getId());
            addressModel.setProvinceId(addressTb.getProvinceId().getId());
            addressModel.setDistrictId(addressTb.getDistrictId().getId());
            addressModel.setSubDistrictId(addressTb.getSubDistrictId().getId());
            addressModel.setUserId(addressTb.getUserId().getId());
            addressModel.setFullAddress(addressTb.getFullAddress());

            addressModelList.add(addressModel);

            // userModel.setAddress(userTb.getAddress());
            // UserModel.setUserId(User.getUserId().getId());
            userModel.setAddressModelList(addressModelList);
        }
        return userModel;
    }
    
    // Model to Entity
    public Boolean InsertUser(UserModel userModel) {
        if (userModel != null) {
            User userTb = new User();
            List<Address> addressList = new ArrayList<Address>();
            
            userTb.setTitle(userModel.getTitle());
            userTb.setFname(userModel.getFname());
            userTb.setLname(userModel.getLname());
            //userTb = userRepository.save(userTb);
            
            for (AddressModel addressModel : userModel.getAddressModelList()) {
                Address addressTb = new Address();
                
                AddressType addressTypeTb = addressTypeRepository.getById(addressModel.getAddressTypeId());
                ThProvince provinceTb = provinceRepository.getById(addressModel.getProvinceId());
                ThDistrict districtTb = districtRepository.getById(addressModel.getDistrictId());
                ThSubdistrict subdistrictTb = subdistrictRepository.getById(addressModel.getSubDistrictId());

                addressTb.setAddressNo(addressModel.getAddressNo());

                addressTb.setAddressType(addressTypeTb);
                addressTb.setProvinceId(provinceTb);
                addressTb.setDistrictId(districtTb);
                addressTb.setSubDistrictId(subdistrictTb);   
                
                //addressTb.setId();
                addressTb.setUserId(userTb);
                addressList.add(addressTb);
            }
            userTb.setAddress(addressList);
            System.out.println(userTb.getAddress());
            System.out.println(userTb);
            userRepository.save(userTb);

            return true;
        }
        return false;

    }

    // Model to Entity
    public Boolean UpdateUser(UserModel userModel) {

        try {
            User userTb = findUserById(userModel.getId());
            if (userTb != null) {
                if (!ObjectUtils.isEmpty(userModel.getTitle())) {
                    userTb.setTitle(userModel.getTitle());
                }

                if (!ObjectUtils.isEmpty(userModel.getFname())) {
                    userTb.setFname(userModel.getFname());
                }

                if (!ObjectUtils.isEmpty(userModel.getLname())) {
                    userTb.setLname(userModel.getLname());
                }

                if (!ObjectUtils.isEmpty(userModel.getAddressModelList())) {

                    List<Address> addressList = new ArrayList<Address>();

                    for (AddressModel addressModel : userModel.getAddressModelList()) {
                        Address addressTb = addressRepository.getById(addressModel.getId());
        
                        AddressType addressTypeTb = addressTypeRepository.getById(addressModel.getAddressTypeId());
                        ThProvince provinceTb = provinceRepository.getById(addressModel.getProvinceId());
                        ThDistrict districtTb = districtRepository.getById(addressModel.getDistrictId());
                        ThSubdistrict subdistrictTb = subdistrictRepository.getById(addressModel.getSubDistrictId());
        
                        addressTb.setAddressNo(addressModel.getAddressNo());

                        addressTb.setAddressType(addressTypeTb);
                        addressTb.setProvinceId(provinceTb);
                        addressTb.setDistrictId(districtTb);
                        addressTb.setSubDistrictId(subdistrictTb);
                        addressTb.setUserId(userTb);
                        addressList.add(addressTb);
                    }
                    userTb.setAddress(addressList);
                }
                userRepository.save(userTb);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("On Error: " + e);
            return false;
        }

    }

    public String deleteUserById(long id) {
        try {
            userRepository.deleteById(id);
            return "User Deleted Successfully";
        } catch (Exception e) {
            return e + "";
        }
    }
}

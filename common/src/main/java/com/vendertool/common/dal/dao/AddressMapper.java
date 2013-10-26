package com.vendertool.common.dal.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QAddress;
import com.vendertool.common.dal.dao.codegen.QBeanAddress;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.Address.AddressStatusEnum;
import com.vendertool.sharedtypes.core.Address.AddressTypeEnum;
import com.vendertool.sharedtypes.core.Address.AddressUsecaseEnum;
import com.vendertool.sharedtypes.core.CountryEnum;

public class AddressMapper implements DALMapper<Address> {

//	private static final Logger logger = Logger.getLogger(AddressMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	Path<?>[] paths;
	
	protected AddressMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}
	
	
	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path, Address addr) {
		if(VUTIL.isNull(addr)) {
			return null;
		}
		
		QAddress a = QAddress.address;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(a.addressId.equals(rpath)) {
				map.put(a.addressId, addr.getId());
			}
			
			if(a.addrLn1.equals(rpath)) {
				map.put(a.addrLn1, addr.getAddressLine1());
			}
			
			if(a.addrLn2.equals(rpath)) {
				map.put(a.addrLn2, addr.getAddressLine2());
			}
			
			if(a.addrType.equals(rpath)) {
				AddressTypeEnum type = addr.getAddressType();
				map.put(a.addrType, (type != null) ? type.getId() : null);
			}
			
			if(a.city.equals(rpath)) {
				map.put(a.city, addr.getCity());
			}
			
			if(a.companyName.equals(rpath)) {
				map.put(a.companyName, addr.getCompany());
			}
			
			if(a.contactFirstName.equals(rpath)) {
				map.put(a.contactFirstName, addr.getFirstName());
			}
			
			if(a.contactLastName.equals(rpath)) {
				map.put(a.contactLastName, addr.getLastName());
			}
			
			if(a.countryCodeIso3.equals(rpath)) {
				CountryEnum country = addr.getCountry();
				map.put(a.countryCodeIso3, (country != null) ? country.getIso3Code() : null);
			}
			
			if(a.createdDate.equals(rpath)) {
				Date cdate = addr.getCreatedDate();
				map.put(a.createdDate, (cdate != null) ? new Timestamp(cdate.getTime()) : null);
			}
			
			if(a.lastModifiedDate.equals(rpath)) {
				map.put(a.lastModifiedDate, new Timestamp(new Date().getTime()));
			}
			
			if(a.postalCode.equals(rpath)) {
				map.put(a.postalCode, addr.getPostalCode());
			}
			
			if(a.state.equals(rpath)) {
				map.put(a.state, addr.getState());
			}
			
			if(a.status.equals(rpath)) {
				AddressStatusEnum status = addr.getStatus();
				map.put(a.status, (status != null) ? status.getId() : null);
			}
			
			if(a.useCase.equals(rpath)) {
				AddressUsecaseEnum usecase = addr.getAddressUseCase();
				map.put(a.useCase, (usecase != null) ? usecase.getId() : null);
			}
		}
		
		return map;
	}

	@Override
	public QBeanAddress populateBean(Address addr) {
		if(VUTIL.isNull(addr)) {
			return null;
		}
		
		QBeanAddress bean = new QBeanAddress();
		bean.setAddressId(addr.getId());
		bean.setAddrLn1(addr.getAddressLine1());
		bean.setAddrLn2(addr.getAddressLine2());
		
		if(addr.getAddressType() != null) {
			bean.setAddrType(addr.getAddressType().getId());
		}
		
		bean.setCity(addr.getCity());
		bean.setCompanyName(addr.getCompany());
		bean.setContactFirstName(addr.getFirstName());
		bean.setContactLastName(addr.getLastName());
		
		if(addr.getCountry() != null) {
			bean.setCountryCodeIso3(addr.getCountry().getIso3Code());
		}
		
		if(addr.getCreatedDate() != null) {
			bean.setCreatedDate(new Timestamp(addr.getCreatedDate().getTime()));
		}
		
		bean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		bean.setPostalCode(addr.getPostalCode());
		bean.setState(addr.getState());
		
		if(addr.getStatus() != null) {
			bean.setStatus(addr.getStatus().getId());
		}
		
		if(addr.getAddressUseCase() != null) {
			bean.setUseCase(addr.getAddressUseCase().getId());
		}
		
		return bean;
	}
	
	public Path<?>[] getPaths() {
		return paths;
	}

	@Override
	public Address convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QAddress a = QAddress.address;
		Address addr = new Address();
		
		for(Path<?> rpath : paths) {
			if(a.addressId.equals(rpath)) {
				addr.setId(row.get(a.addressId));
			}
			
			if(a.addrLn1.equals(rpath)) {
				addr.setAddressLine1(row.get(a.addrLn1));
			}
			
			if(a.addrLn2.equals(rpath)) {
				addr.setAddressLine2(row.get(a.addrLn2));
			}
			
			if(a.addrType.equals(rpath)) {
				if(row.get(a.addrType) != null) {
					int id = row.get(a.addrType).intValue();
					AddressTypeEnum type = AddressTypeEnum.get(id);
					if(type != null) {
						addr.setAddressType(type);
					}
				}
			}
			
			if(a.city.equals(rpath)) {
				addr.setCity(row.get(a.city));
			}
			
			if(a.companyName.equals(rpath)) {
				addr.setCompany(row.get(a.companyName));
			}
			
			if(a.contactFirstName.equals(rpath)) {
				addr.setFirstName(row.get(a.contactFirstName));
			}
			
			if(a.contactLastName.equals(rpath)) {
				addr.setLastName(row.get(a.contactLastName));
			}
			
			if(a.countryCodeIso3.equals(rpath)) {
				if(row.get(a.countryCodeIso3) != null) {
					String code = row.get(a.countryCodeIso3);
					CountryEnum country = CountryEnum.getCountryFromISO3Code(code);
					if(country != null) {
						addr.setCountry(country);
					}
				}
			}
			
			if(a.createdDate.equals(rpath)) {
				Timestamp ts = row.get(a.createdDate);
				if(ts != null) {
					addr.setCreatedDate(new Date(ts.getTime()));
				}
			}
			
			if(a.lastModifiedDate.equals(rpath)) {
				//nothing to do here
			}
			
			if(a.postalCode.equals(rpath)) {
				addr.setPostalCode(row.get(a.postalCode));
			}
			
			if(a.state.equals(rpath)) {
				addr.setState(row.get(a.state));
			}
			
			if(a.status.equals(rpath)) {
				if(row.get(a.status) != null) {
					int id = row.get(a.status).intValue();
					AddressStatusEnum status = AddressStatusEnum.get(id);
					if(status != null) {
						addr.setStatus(status);
					}
				}
			}
			
			if(a.useCase.equals(rpath)) {
				if(row.get(a.useCase) != null) {
					int id = row.get(a.useCase).intValue();
					AddressUsecaseEnum usecase = AddressUsecaseEnum.get(id);
					if(usecase != null) {
						addr.setAddressUseCase(usecase);
					}
				}
			}
		}
		
		return addr;
	}

}

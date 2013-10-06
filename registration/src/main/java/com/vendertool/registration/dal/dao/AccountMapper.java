package com.vendertool.registration.dal.dao;

import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.dao.codegen.QAccount;
import com.vendertool.registration.dal.dao.codegen.QBeanAccount;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountRoleEnum;
import com.vendertool.sharedtypes.core.AccountStatusEnum;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.Image;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.core.Phone;
import com.vendertool.sharedtypes.core.Phone.PhoneType;

public class AccountMapper implements DALMapper<Account> {
//	private static final Logger logger = Logger.getLogger(AccountMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected AccountMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			Account account) {
		if(VUTIL.isNull(account)) {
			return null;
		}
		
		QAccount a = QAccount.account;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(a.accountId.equals(rpath)) {
				map.put(a.accountId, account.getId());
			}
			
			if(a.alternateEmail.equals(rpath)) {
				map.put(a.alternateEmail, account.getAlternateEmailId());
			}

			if(a.billingAddrId.equals(rpath)) {
				map.put(a.billingAddrId, 
						(account.getBillingAddress() != null) ? 
								account.getBillingAddress().getId() : null);
			}
			
			if(a.createdDate.equals(rpath)) {
				Date date = account.getCreateDate();
				if(date == null) {
					date = new Date();
				}
				map.put(a.createdDate, new Timestamp(date.getTime()));
			}
			
			if(a.currencyCodeIso3.equals(rpath)) {
				map.put(a.currencyCodeIso3, 
						(account.getCurrency() != null) ? 
								account.getCurrency().getCurrencyCode() : null);
			}
			
			if(a.emailAddr.equals(rpath)) {
				map.put(a.emailAddr, account.getEmail());
			}
			
			if(a.firstName.equals(rpath)) {
				map.put(a.firstName, 
						(account.getContactDetails() != null) ? 
								account.getContactDetails().getFirstName() : null);
			}

			if(a.language.equals(rpath)) {
				Language lang = account.getLanguage();
				if(lang != null) {
					map.put(a.language, lang.getIsoLangCode());
				}
			}
			
			if(a.lastModifiedApp.equals(rpath)) {
				//for now put null we need to figure this later
//				map.put(a.lastModifiedApp, null);
			}
			
			if(a.lastModifiedDate.equals(rpath)) {
				map.put(a.lastModifiedDate, new Timestamp(new Date().getTime()));
			}

			if(a.lastName.equals(rpath)) {
				map.put(a.lastName, 
						(account.getContactDetails() != null) ? 
								account.getContactDetails().getLastName() : null);
			}
			
			if(a.middleName.equals(rpath)) {
				map.put(a.middleName, 
						(account.getContactDetails() != null) ? 
								account.getContactDetails().getMiddleName() : null);
			}

			if(a.password.equals(rpath)) {
				map.put(a.password, account.getPassword());
			}
			
			if(a.picture.equals(rpath)) {
				map.put(a.picture, (account.getPicture() != null) ? account
						.getPicture().getBytes() : null);
			}

			if(a.registrationAddrId.equals(rpath)) {
				map.put(a.registrationAddrId,
						((account.getContactDetails() != null) && (account
								.getContactDetails().getAddress() != null)) ? account
								.getContactDetails().getAddress().getId()
								: null);
			}
			
			if(a.roles.equals(rpath)) {
				Set<AccountRoleEnum> roles = account.getRoles();
				if((roles != null) && (!roles.isEmpty())) {
					map.put(a.roles, account.normalize(roles));
				}
			}
			
			if(a.salt.equals(rpath)) {
				map.put(a.salt, account.getPasswordSalt());
			}
			
			if(a.status.equals(rpath)) {
				if(VUTIL.isNotNull(account.getAccountStatus())) {
					map.put(a.status, new Byte(account.getAccountStatus().getId()+""));
				}
			}
			
			if(a.contactPhoneHome.equals(rpath)) {
				ContactDetails cd = account.getContactDetails();
				if (VUTIL.isNotNull(cd) && VUTIL.isNotNull(cd.getPhones())) {
					Phone phone = cd.getPhones().get(PhoneType.HOME);
					if(phone != null) {
						map.put(a.contactPhoneHome, phone.getNumber()+"");
					}
				}
			}
			
			if(a.contactPhoneMobile.equals(rpath)) {
				ContactDetails cd = account.getContactDetails();
				if (VUTIL.isNotNull(cd) && VUTIL.isNotNull(cd.getPhones())) {
					Phone phone = cd.getPhones().get(PhoneType.MOBILE);
					if(phone != null) {
						map.put(a.contactPhoneMobile, phone.getNumber()+"");
					}
				}
			}
			
			if(a.contactPhoneWork.equals(rpath)) {
				ContactDetails cd = account.getContactDetails();
				if (VUTIL.isNotNull(cd) && VUTIL.isNotNull(cd.getPhones())) {
					Phone phone = cd.getPhones().get(PhoneType.WORK);
					if(phone != null) {
						map.put(a.contactPhoneWork, phone.getNumber()+"");
					}
				}
			}
		}
		
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanAccount populateBean(Account account) {
		if(VUTIL.isNull(account)) {
			return null;
		}
		
		QBeanAccount bean = new QBeanAccount();
		bean.setAccountId(account.getId());
		bean.setAlternateEmail(account.getAlternateEmailId());
		
		Address baddr = account.getBillingAddress();
		if(baddr != null) {
			bean.setBillingAddrId(baddr.getId());
		}
		
		Date cdate = account.getCreateDate();
		if(cdate == null) {
			cdate = new Date();
		}
		bean.setCreatedDate(new Timestamp(cdate.getTime()));
		
		if(account.getCurrency() != null) {
			bean.setCurrencyCodeIso3(account.getCurrency().getCurrencyCode());
		}
		
		bean.setEmailAddr(account.getEmail());
		
		ContactDetails cd = account.getContactDetails();
		if(VUTIL.isNotNull(cd)) {
			bean.setFirstName(cd.getFirstName());
			bean.setLastName(cd.getLastName());
			bean.setMiddleName(cd.getMiddleName());
			if(cd.getAddress() != null) {
				bean.setRegistrationAddrId(cd.getAddress().getId());
			}
			
			Map<PhoneType, Phone> phoneMap = cd.getPhones();
			if(VUTIL.isNotNull(phoneMap)) {
				Phone p = phoneMap.get(PhoneType.HOME);
				if(p != null) {
					bean.setContactPhoneHome(p.getNumber().toString());
				}
				
				p = phoneMap.get(PhoneType.WORK);
				if(p != null) {
					bean.setContactPhoneWork(p.getNumber().toString());
				}
				
				p = phoneMap.get(PhoneType.MOBILE);
				if(p != null) {
					bean.setContactPhoneMobile(p.getNumber().toString());
				}
			}
		}
		
		if(account.getLanguage() != null) {
			bean.setLanguage(account.getLanguage().getIsoLangCode());
		}
		
		bean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		bean.setPassword(account.getPassword());
		if(account.getPicture() != null) {
			bean.setPicture(account.getPicture().getBytes());
		}
		
		if(account.getRoles() != null) {
			bean.setRoles(account.normalize(account.getRoles()));
		}
		
		bean.setSalt(account.getPasswordSalt());
		if(account.getAccountStatus() != null) {
			bean.setStatus(new Byte(account.getAccountStatus().getId()+""));
		}
		
		
		return bean;
		
	}
	
	public Account convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QAccount a = QAccount.account;
		Account account = new Account();
		
		for(Path<?> rpath : paths) {
			if(a.accountId.equals(rpath)) {
				account.setId(row.get(a.accountId));
			}
			
			if(a.alternateEmail.equals(rpath)) {
				account.setAlternateEmailId(row.get(a.alternateEmail));
			}

			if(a.billingAddrId.equals(rpath)) {
				
				if(row.get(a.billingAddrId) != null) {
					Address baddr = account.getBillingAddress();
					if(baddr == null) {
						baddr = new Address();
						account.setBillingAddress(baddr);
					}
					
					baddr.setId(row.get(a.billingAddrId));
				}
			}
			
			if(a.createdDate.equals(rpath)) {
				if(row.get(a.createdDate) != null) {
					account.setCreateDate(new Date(row.get(a.createdDate).getTime()));
				}
			}
			
			if(a.currencyCodeIso3.equals(rpath)) {
				try {
					Currency c = Currency.getInstance(row.get(a.currencyCodeIso3));
					if(c != null) {
						account.setCurrency(c);
					}
				} catch (Exception e) {
					account.setCurrency(null);
				}
			}
			
			if(a.emailAddr.equals(rpath)) {
				account.setEmail(row.get(a.emailAddr));
			}
			
			if(a.firstName.equals(rpath)) {
				if(row.get(a.firstName) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						account.setContactDetails(cd);
					}
					cd.setFirstName(row.get(a.firstName));
				}
			}

			if(a.language.equals(rpath)) {
				String langCode = row.get(a.language);
				if(langCode != null) {
					Language lang = Language.getLanguage(langCode);
					if(lang != null) {
						account.setLanguage(lang);
					}
				}
			}
			
			if(a.lastModifiedApp.equals(rpath)) {
				//for now put null we need to figure this later
//				map.put(a.lastModifiedApp, null);
			}
			
			if(a.lastModifiedDate.equals(rpath)) {
				if(row.get(a.lastModifiedDate) != null) {
					account.setLastModifiedDate(new Date(row.get(a.lastModifiedDate).getTime()));
				}
			}

			if(a.lastName.equals(rpath)) {
				if(row.get(a.lastName) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						account.setContactDetails(cd);
					}
					cd.setLastName(row.get(a.lastName));
				}
			}
			
			if(a.middleName.equals(rpath)) {
				if(row.get(a.middleName) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						account.setContactDetails(cd);
					}
					cd.setMiddleName(row.get(a.middleName));
				}
			}

			if(a.password.equals(rpath)) {
				account.setPassword(row.get(a.password));
			}
			
			if(a.picture.equals(rpath)) {
				byte[] bytes = row.get(a.picture);
				if((bytes != null) && (bytes.length > 0)) {
					Image img = new Image();
					img.setBytes(bytes);
					account.setPicture(img);
				}
			}

			if(a.registrationAddrId.equals(rpath)) {
				if(row.get(a.registrationAddrId) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						cd.setAddress(new Address());
						account.setContactDetails(cd);
					}
					if(cd.getAddress() == null) {
						cd.setAddress(new Address());
					}
					cd.getAddress().setId(row.get(a.registrationAddrId));
				}
			}
			
			if(a.roles.equals(rpath)) {
				String roles = row.get(a.roles);
				if((roles != null) && (!roles.trim().isEmpty())) {
					account.setRoles(account.denormalize(roles));
				}
			}
			
			if(a.salt.equals(rpath)) {
				account.setPasswordSalt(row.get(a.salt));
			}
			
			if(a.status.equals(rpath)) {
				if(row.get(a.status) != null) {
					int sid = row.get(a.status).intValue();
					AccountStatusEnum se = AccountStatusEnum.get(sid);
					if(se != null) {
						account.setAccountStatus(se);
					}
				}
			}
			
			if(a.contactPhoneHome.equals(rpath)) {
				if(row.get(a.contactPhoneHome) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						account.setContactDetails(cd);
					}
					Phone p = new Phone();
					p.setNumber(Integer.getInteger(row.get(a.contactPhoneHome)));
					
					cd.addPhone(PhoneType.HOME, p);
				}
			}
			
			if(a.contactPhoneWork.equals(rpath)) {
				if(row.get(a.contactPhoneWork) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						account.setContactDetails(cd);
					}
					Phone p = new Phone();
					p.setNumber(Integer.getInteger(row.get(a.contactPhoneWork)));
					
					cd.addPhone(PhoneType.WORK, p);
				}
			}
			
			if(a.contactPhoneMobile.equals(rpath)) {
				if(row.get(a.contactPhoneMobile) != null) {
					ContactDetails cd = account.getContactDetails();
					if(cd == null) {
						cd = new ContactDetails();
						account.setContactDetails(cd);
					}
					Phone p = new Phone();
					p.setNumber(Integer.getInteger(row.get(a.contactPhoneMobile)));
					
					cd.addPhone(PhoneType.MOBILE, p);
				}
			}
		}
		
		return account;
	}
}
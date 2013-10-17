package com.vendertool.registration.dal.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.dao.codegen.QBeanForgotPassword;
import com.vendertool.registration.dal.dao.codegen.QForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword.ForgotPasswordStatusEnum;

public class ForgotPasswordMapper implements DALMapper<ForgotPassword> {
//	private static final Logger logger = Logger.getLogger(ForgotPasswordMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;
	
	protected ForgotPasswordMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}
	
	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path, ForgotPassword fp) {
		
		if(VUTIL.isNull(fp)) {
			return null;
		}
		
		QForgotPassword f = QForgotPassword.forgotPassword;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(f.forgotPasswordId.equals(rpath)) {
				map.put(f.forgotPasswordId, fp.getId());
			}
			
			if(f.accountId.equals(rpath)) {
				map.put(f.accountId, fp.getAccountId());
			}
			
			if(f.createdDate.equals(rpath)) {
				Date dt = fp.getCreatedDate();
				if(VUTIL.isNull(dt)) {
					dt = new Date();
				}
				
				map.put(f.createdDate, new Timestamp(dt.getTime()));
			}
			
			if(f.emailAddr.equals(rpath)) {
				map.put(f.emailAddr, fp.getEmail());
			}
			
			if(f.ipAddr.equals(rpath)) {
				map.put(f.ipAddr, fp.getIpAddress());
			}
			
			if(f.lastModifiedDate.equals(rpath)) {
				map.put(f.lastModifiedDate, new Timestamp(new Date().getTime()));
			}
			
			if(f.status.equals(rpath)) {
				if(VUTIL.isNotNull(fp.getStatus())) {
					map.put(f.status, new Byte(fp.getStatus().getId()+""));
				}
			}
		}
		
		return map;
	}

	@Override
	public QBeanForgotPassword populateBean(ForgotPassword fp) {
		if(VUTIL.isNull(fp)) {
			return null;
		}
		
		QBeanForgotPassword fpbean = new QBeanForgotPassword();
		
		fpbean.setForgotPasswordId(fp.getId());
		fpbean.setAccountId(fp.getAccountId());
		
		Date dt = fp.getCreatedDate();
		if(dt != null) {
			fpbean.setCreatedDate(new Timestamp(dt.getTime()));
		}
		
		fpbean.setEmailAddr(fp.getEmail());
		fpbean.setIpAddr(fp.getIpAddress());
		
		fpbean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		
		if(VUTIL.isNotNull(fp.getStatus())) {
			fpbean.setStatus(new Byte(fp.getStatus().getId()+""));
		}
		
		return fpbean;
	}

	@Override
	public ForgotPassword convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QForgotPassword f = QForgotPassword.forgotPassword;
		ForgotPassword fp = new ForgotPassword();
		
		for(Path<?> rpath : paths) {
			if(f.forgotPasswordId.equals(rpath)) {
				fp.setId(row.get(f.forgotPasswordId));
			}
			
			if(f.accountId.equals(rpath)) {
				fp.setAccountId(row.get(f.accountId));
			}
			
			if(f.createdDate.equals(rpath)) {
				if(VUTIL.isNotNull(row.get(f.createdDate))) {
					fp.setCreatedDate(new Date(row.get(f.createdDate).getTime()));
				}
			}
			
			if(f.emailAddr.equals(rpath)) {
				fp.setEmail(row.get(f.emailAddr));
			}
			
			if(f.ipAddr.equals(rpath)) {
				fp.setIpAddress(row.get(f.ipAddr));
			}
			
//			if(f.lastModifiedDate.equals(rpath)) {
//				if(VUTIL.isNotNull(row.get(f.lastModifiedDate))) {
//					fp.setLastModifiedDate(new Date(row.get(f.lastModifiedDate).getTime()));
//				}
//			}
			
			if(f.status.equals(rpath)) {
				if(VUTIL.isNotNull(row.get(f.status))) {
					int sid = row.get(f.status).intValue();
					ForgotPasswordStatusEnum statusEnum = ForgotPasswordStatusEnum.get(sid);
					if(statusEnum != null) {
						fp.setStatus(statusEnum);
					}
				}
			}
			
		}
		
		return fp;
	}

}

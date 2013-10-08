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
import com.vendertool.registration.dal.dao.codegen.QAccountConfirmation;
import com.vendertool.registration.dal.dao.codegen.QBeanAccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation;

public class AccountConfirmationMapper implements DALMapper<AccountConfirmation> {

//	private static final Logger logger = Logger.getLogger(AccountConfirmationMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;
	
	protected AccountConfirmationMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}
	
	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			AccountConfirmation accConf) {
		if(VUTIL.isNull(accConf)) {
			return null;
		}
		
		QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(ac.accountId.equals(rpath)) {
				map.put(ac.accountId, accConf.getId());
			}
			
			if(ac.confirmationCode.equals(rpath)) {
				map.put(ac.confirmationCode, accConf.getConfirmCode());
			}
			
			if(ac.confirmationDate.equals(rpath)) {
				map.put(ac.confirmationDate,
						(accConf.getConfirmationDate() != null) ? new Timestamp(
								accConf.getConfirmationDate().getTime()) : null);
			}
			
			if(ac.sessionId.equals(rpath)) {
				map.put(ac.sessionId, accConf.getConfirmSessionId());
			}
			
			if(ac.numberOfAttempts.equals(rpath)) {
				map.put(ac.numberOfAttempts, accConf.getConfirmationAttempts());
			}
			
			if(ac.accountConfirmationId.equals(rpath)) {
				map.put(ac.accountConfirmationId, accConf.getId());
			}
			
			if(ac.expiryDate.equals(rpath)) {
				map.put(ac.expiryDate,
						(accConf.getExpiryDate() != null) ? new Timestamp(
								accConf.getExpiryDate().getTime()) : null);
			}
		}
		
		return map;
	}

	@Override
	public QBeanAccountConfirmation populateBean(AccountConfirmation accConf) {
		if(VUTIL.isNull(accConf)) {
			return null;
		}
		
		QBeanAccountConfirmation accbean = new QBeanAccountConfirmation();
		
		accbean.setAccountConfirmationId(accConf.getId());
		
		if(VUTIL.isNotNull(accConf.getConfirmCode())) {
			accbean.setConfirmationCode(accConf.getConfirmCode().shortValue());
		}
		
		if(VUTIL.isNotNull(accConf.getConfirmationDate())) {
			accbean.setConfirmationDate(new Timestamp(accConf.getConfirmationDate().getTime()));
		}
		
		accbean.setSessionId(accConf.getConfirmSessionId());
		
		Date cdate = accConf.getCreateDate();
		if(cdate == null) {
			cdate = new Date();
		}
		accbean.setCreatedDate(new Timestamp(cdate.getTime()));
		
		Date edate = accConf.getExpiryDate();
		if(edate != null) {
			accbean.setExpiryDate(new Timestamp(edate.getTime()));
		}
		
		return accbean;
		
	}

	@Override
	public AccountConfirmation convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
		AccountConfirmation accConf = new AccountConfirmation();
		
		for(Path<?> rpath : paths) {
			if(ac.accountConfirmationId.equals(rpath)) {
				accConf.setId(row.get(ac.accountId));
			}
			
			if(ac.confirmationCode.equals(rpath)) {
				Short code = row.get(ac.confirmationCode);
				if(VUTIL.isNotNull(code)){
					accConf.setConfirmCode(new Integer(code.intValue()));
				}
			}
			
			if(ac.confirmationDate.equals(rpath)) {
				if(VUTIL.isNotNull(row.get(ac.confirmationDate))) {
					accConf.setConfirmationDate(new Date(row.get(ac.confirmationDate).getTime()));
				}
			}
			
			if(ac.sessionId.equals(rpath)) {
				accConf.setConfirmSessionId(row.get(ac.sessionId));
			}
			
			if(ac.createdDate.equals(rpath)) {
				if(VUTIL.isNotNull(row.get(ac.createdDate))) {
					accConf.setCreateDate(new Date(row.get(ac.createdDate).getTime()));
				}
			}
			
			if(ac.numberOfAttempts.equals(rpath)) {
				if(VUTIL.isNotNull(row.get(ac.numberOfAttempts))) {
					accConf.setConfirmationAttempts(row.get(ac.numberOfAttempts));
				}
			}
			
			if(ac.expiryDate.equals(rpath)) {
				if(VUTIL.isNotNull(row.get(ac.expiryDate))) {
					accConf.setExpiryDate(new Date(row.get(ac.expiryDate).getTime()));
				}
			}
		}
		
		return accConf;
	}
}
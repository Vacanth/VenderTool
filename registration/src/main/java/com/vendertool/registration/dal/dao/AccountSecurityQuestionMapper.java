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
import com.vendertool.registration.dal.dao.codegen.QAccountSecurityQuestion;
import com.vendertool.registration.dal.dao.codegen.QBeanAccountSecurityQuestion;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.SecurityQuestionCodeEnum;

public class AccountSecurityQuestionMapper implements
		DALMapper<AccountSecurityQuestion> {

//	private static final Logger logger = Logger.getLogger(AccountSecurityQuestionMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected AccountSecurityQuestionMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}
	
	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			AccountSecurityQuestion question) {
		
		if(VUTIL.isNull(question)) {
			return null;
		}
		
		QAccountSecurityQuestion q = QAccountSecurityQuestion.accountSecurityQuestion;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(q.accountSecurityQuestionId.equals(rpath)) {
				map.put(q.accountSecurityQuestionId, question.getId());
			}
			
			if(q.createdDate.equals(rpath)) {
				Date dt = question.getCreatedDate();
				if(dt == null) {
					dt = new Date();
				}
				map.put(q.createdDate, new Timestamp(dt.getTime()));
			}
			
			if(q.lastModifiedDate.equals(rpath)) {
				map.put(q.lastModifiedDate, new Timestamp(new Date().getTime()));
			}
			
			if(q.securityAnswer.equals(rpath)) {
				map.put(q.securityAnswer, question.getAnswer());
			}
			
			if(q.securityQuestionCode.equals(rpath)) {
				map.put(q.securityQuestionCode,
						(question.getQuestion() != null && question.getQuestion().getQuestionCode() != null) ? 
								question.getQuestion().getQuestionCode().getCode() : null);
			}
		}
		
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	@Override
	public QBeanAccountSecurityQuestion populateBean(AccountSecurityQuestion question) {
		if(VUTIL.isNull(question)) {
			return null;
		}
		
		QBeanAccountSecurityQuestion bean = new QBeanAccountSecurityQuestion();
		
		if(question.getQuestion() != null && question.getQuestion().getQuestionCode() != null) {
			bean.setSecurityQuestionCode(question.getQuestion().getQuestionCode().getCode());
		}
		bean.setAccountSecurityQuestionId(question.getId());
		if(question.getCreatedDate() != null) {
			bean.setCreatedDate(new Timestamp(question.getCreatedDate().getTime()));
		}
		bean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		bean.setSecurityAnswer(question.getAnswer());
		
		return bean;
	}

	@Override
	public AccountSecurityQuestion convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QAccountSecurityQuestion q = QAccountSecurityQuestion.accountSecurityQuestion;
		AccountSecurityQuestion question = new AccountSecurityQuestion();
		
		for(Path<?> rpath : paths) {
			if(q.accountSecurityQuestionId.equals(rpath)) {
				question.setId(row.get(q.accountSecurityQuestionId));
			}
			
			if(q.createdDate.equals(rpath)) {
				Timestamp ts = row.get(q.createdDate);
				if(ts != null) {
					question.setCreatedDate(new Date(ts.getTime()));
				}
			}
			
			if(q.lastModifiedDate.equals(rpath)) {
				//add it later if needed
			}
			
			if(q.securityAnswer.equals(rpath)) {
				question.setAnswer(row.get(q.securityAnswer));
			}
			
			if(q.securityQuestionCode.equals(rpath)) {
				String code = row.get(q.securityQuestionCode);
				if(code != null) {
					SecurityQuestionCodeEnum ce = SecurityQuestionCodeEnum.get(code);
					if(ce != null) {
						question.getQuestion().setQuestionCode(ce);
					}
				}
			}
		}
		
		return question;
	}
}
package com.vendertool.common.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.vendertool.common.dal.dao.codegen.QAddress;
import com.vendertool.common.dal.dao.codegen.QBeanAddress;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.dal.fieldset.FieldSets;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.Address.AddressStatusEnum;

public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {

	private static final Logger logger = Logger.getLogger(AddressDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	@Override
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForAddress()";
	}

	@Override
	public Long insert(Long accountId, Address addr) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(addr)) {
			InsertException ie = new InsertException("Cannot insert null address");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAddress a = QAddress.address;
			
			Long addressId = addr.getId();
			if(VUTIL.isNull(addressId) || (addressId.longValue() <= 0)) {
				Long seq = generateNextSequence(con);
				if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
		    		InsertException ie = new InsertException("Unable to generate valid sequence for address");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				addr.setId(seq);
			}
	
	    	// YOU CAN DO THIS...
	//		SQLInsertClause s = insert(con, a)
	//				.populate(account, new AddressMapper(a.all()));
	    	
	    	//OR YOU CAN DO THIS...
			QBeanAddress bean = new AddressMapper(a.all()).populateBean(addr);
			if((accountId != null) && (accountId.longValue() > 0)) {
				bean.setAccountId(accountId);
			}
	    	SQLInsertClause s = insert(con, a)
	    				.populate(bean);
	    	
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    		return addr.getId();
	    	} catch (Exception e) {
	    		InsertException ie = new InsertException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}
	
	
	@Override
	public List<Address> findByAccountId(Long accountId) throws DBConnectionException, FinderException,
			DatabaseException {
		if(VUTIL.isNull(accountId)) {
			FinderException fe = new FinderException("Cannot find addresses with null account id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QAddress a = QAddress.address;
			
			SQLQuery query = from(con, a)
					.orderBy(a.createdDate.desc())
					.where(a.accountId.eq(accountId));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Tuple> rows = query.list(a.all());
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
	    	List<Address> addresses = new ArrayList<Address>();
	    	for(Tuple row : rows) {
	    		Address address = new AddressMapper(a.all()).convert(row, a.all());
	    		addresses.add(address);
	    	}
			
			return addresses;
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public Address findById(Long id) throws DBConnectionException,
			FinderException, DatabaseException {
		
		if(VUTIL.isNull(id)) {
			FinderException fe = new FinderException("Cannot find address with null id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QAddress a = QAddress.address;
			
			SQLQuery query = from(con, a)
					.where(a.addressId.eq(id));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Tuple> rows = query.list(a.all());
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			Address address = new AddressMapper(a.all()).convert(rows.get(0), a.all());
			if(address == null) {
				FinderException fe = new FinderException("Cannot find address");
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return address;
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void deleteById(Long id, boolean force) throws DBConnectionException,
			DeleteException, DatabaseException {
		
		if(VUTIL.isNull(id)) {
			DeleteException fe = new DeleteException("Cannot delete address with null id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		//Soft delete
		if(!force) {
			try {
				updateStatusById(id, AddressStatusEnum.DELETED);
				return;
			} catch (UpdateException e) {
	    		DeleteException ie = new DeleteException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
			}
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAddress a = QAddress.address;
			
			SQLDeleteClause s = delete(con, a)
				.where(a.addressId.eq(id));
		
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    		return;
	    	} catch (Exception e) {
	    		DeleteException ie = new DeleteException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updateAddressMetadata(Address addr)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(addr)) {
			UpdateException ue = new UpdateException("Cannot update null address");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAddress a = QAddress.address;
	    	SQLUpdateClause s = update(con, a)
					.populate(addr, new AddressMapper(FieldSets.ADDRESS_UPDATESET.METADATA));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updateStatusById(Long id, AddressStatusEnum status)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(id)) {
			UpdateException ue = new UpdateException("Cannot update null address");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			Byte val = null;
			if(status != null) {
				val = new Byte(status.getId()+"");
			}
			
			QAddress a = QAddress.address;
			SQLUpdateClause s = update(con, a)
					.set(a.status, val)
					.where(a.addressId.eq(id));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}		
	}

	@Override
	public void deleteByAccountId(Long accountId, boolean force)
			throws DBConnectionException, DeleteException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			DeleteException fe = new DeleteException("Cannot delete address with null account id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		//Soft delete
		if(!force) {
			try {
				updateStatusByAccountId(accountId, AddressStatusEnum.DELETED);
				return;
			} catch (UpdateException e) {
	    		DeleteException ie = new DeleteException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
			}
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAddress a = QAddress.address;
			
			SQLDeleteClause s = delete(con, a)
				.where(a.accountId.eq(accountId));
		
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    		return;
	    	} catch (Exception e) {
	    		DeleteException ie = new DeleteException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updateStatusByAccountId(Long accountId, AddressStatusEnum status)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			UpdateException ue = new UpdateException("Cannot update null address");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			Byte val = null;
			if(status != null) {
				val = new Byte(status.getId()+"");
			}
			
			QAddress a = QAddress.address;
			SQLUpdateClause s = update(con, a)
					.set(a.status, val)
					.where(a.accountId.eq(accountId));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
		
	}
	
	
}
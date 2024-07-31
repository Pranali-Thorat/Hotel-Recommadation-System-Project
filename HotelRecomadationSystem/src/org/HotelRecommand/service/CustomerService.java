package org.HotelRecommand.service;

import org.HotelRecommand.model.CustomerModel;
import org.HotelRecommand.repository.CustomerRepository;

import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

public class CustomerService {
 CustomerRepository custRepo =new CustomerRepository();
	public Boolean CustomerLogin(CustomerModel cm) 
	{
		
		return custRepo.CustomerLogin(cm);
	}
	public boolean AddCustomer(CustomerModel custmodel) {
		
		return custRepo.AddCustomer(custmodel);
	}
	public int custIdByName(String cuser) {
		
		return custRepo.custIbByName(cuser);
	}
	
	public String getCityNameByCustId(int cstid) {
		
		return custRepo.getCityNameByCustId(cstid);
	}
    public String getAreaNameByCustId(int cstid)
    {
    	return custRepo.getAreaNameByCustId(cstid);
    }
}

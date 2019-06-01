package com.nt.service;

import com.nt.bo.EmployeeBO;
import com.nt.dao.EmployeeDAO;
import com.nt.dao.EmployeeDAOImpl;
import com.nt.dto.EmployeeDTO;

public class EmployeeServiceImpl implements EmployeeService{
	
		private EmployeeDAO dao=null;
	
		public EmployeeServiceImpl() {
				System.out.println("EmployeeServiceImpl 0-param con");
				dao=new EmployeeDAOImpl();
		}

	@Override
	public String calculateGrossnNetSalary(EmployeeDTO dto) {
	
				int sal=dto.getSal(),count=0;
				float gross_sal=0,net_sal=0;
				EmployeeBO bo=null;
				
				//gross sal
				gross_sal=(float) (sal+(0.3*sal));
				//net sal
				net_sal=(float) (gross_sal-(0.1*gross_sal));
				
				bo=new EmployeeBO();

				bo.setEname(dto.getEname());
				bo.setAddrs(dto.getAddrs());
				bo.setDesg(dto.getDesg());
				bo.setGross_sal(gross_sal);
				bo.setNet_sal(net_sal);
				
				count=dao.insert(bo);
				
				if(count!=0) 
					return "Record inserted";
				else
					return "Record not inserted";
	}
}

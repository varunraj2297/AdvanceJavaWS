package com.nt.service;

import com.nt.dto.EmployeeDTO;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public void calculateGrossnNetSalaries(EmployeeDTO dto) {
	        float sal=0.0f,grossSal=0.0f,netSal=0.0f;
	        
	        sal=dto.getSal();
	        grossSal=sal+(0.5f*sal);
	        netSal=sal-(0.2f*sal);
	        
	        dto.setGrossSal(grossSal);
	        dto.setNetSal(netSal);
	}
}

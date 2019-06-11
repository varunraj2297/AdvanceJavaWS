package com.nt.service;

import org.apache.commons.beanutils.BeanUtils;

import com.nt.bo.JobSeekerBO;
import com.nt.dao.JobSeekerDAO;
import com.nt.dao.JobSeekerDAOImpl;
import com.nt.dto.JobSeekerDTO;

public class JobSeekerMgmtServiceImpl implements JobSeekerMgmtService {
      
    private JobSeekerDAO dao;
	
	public JobSeekerMgmtServiceImpl() {
		dao=new JobSeekerDAOImpl();
	}

	@Override
	public String registerJobSeeker(JobSeekerDTO dto) throws Exception {
	    int result=0; 
		JobSeekerBO bo=null;
		
	    bo=new JobSeekerBO();
	    BeanUtils.copyProperties(bo, dto);
	    result=dao.insert(bo);
	  
	   	return (result==0)?"Registration Failed":"Registration Succeded";
	}

}

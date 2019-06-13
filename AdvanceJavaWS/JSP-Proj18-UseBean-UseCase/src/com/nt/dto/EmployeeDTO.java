package com.nt.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeDTO implements Serializable {
       private int eno;
       private String ename;
       private String job;
       private float sal;
       private float grossSal;
       private float netSal;
}

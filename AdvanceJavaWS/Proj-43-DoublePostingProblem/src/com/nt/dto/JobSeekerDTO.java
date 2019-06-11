package com.nt.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class JobSeekerDTO implements Serializable {
	private int jid;
	private String jname;
	private int age;
	private String addrs;
	private String skill;
	private int exp;
	private String loc;
	private int expsal;
}

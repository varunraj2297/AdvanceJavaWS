package com.nt.dto;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {
				private int eid;
				private String ename;
				private String addrs;
				private String desg;
				private int sal;
				
				
				public int getEid() {
					return eid;
				}
				public void setEid(int eid) {
					this.eid = eid;
				}
				public String getEname() {
					return ename;
				}
				public void setEname(String ename) {
					this.ename = ename;
				}
				public String getAddrs() {
					return addrs;
				}
				public void setAddrs(String addrs) {
					this.addrs = addrs;
				}
				public String getDesg() {
					return desg;
				}
				public void setDesg(String desg) {
					this.desg = desg;
				}
				public int getSal() {
					return sal;
				}
				public void setSal(int sal) {
					this.sal = sal;
				}
				@Override
				public String toString() {
					return "EmployeeDTO [eid=" + eid + ", ename=" + ename + ", addrs=" + addrs + ", desg=" + desg
							+ ", sal=" + sal + "]";
				}
				
}

package com.nt.bo;

public class EmployeeBO {
	private int eid;
	private String ename;
	private String addrs;
	private String desg;
	private float gross_sal;
	private float net_sal;
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
	public float getGross_sal() {
		return gross_sal;
	}
	public void setGross_sal(float gross_sal) {
		this.gross_sal = gross_sal;
	}
	public float getNet_sal() {
		return net_sal;
	}
	public void setNet_sal(float net_sal) {
		this.net_sal = net_sal;
	}
	@Override
	public String toString() {
		return "EmployeeBO [eid=" + eid + ", ename=" + ename + ", addrs=" + addrs + ", desg=" + desg + ", gross_sal="
				+ gross_sal + ", net_sal=" + net_sal + "]";
	}	
	
}

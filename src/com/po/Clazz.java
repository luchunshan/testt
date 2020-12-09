package com.po;

import java.io.Serializable;

public class Clazz implements Serializable {
   private Integer cid;
   private String cname;
public Clazz() {
	super();
	// TODO Auto-generated constructor stub
}
public Clazz(Integer cid, String cname) {
	super();
	this.cid = cid;
	this.cname = cname;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
   
}

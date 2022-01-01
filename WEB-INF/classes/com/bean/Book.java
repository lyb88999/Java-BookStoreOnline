package com.bean;

import java.util.Date;

/**
 * @author lh
 *
 */
public class Book {
	private int id=0;
	private String name="";
	private String pic="";
	private String clazz="";
	private String disc="";
	private String pub="";
	private Date pub_data=null;
	private String editor="";
	private float price=0;
	private int count=0;
	public Book() {
		
	}
	public Book(int id, String name, String pic, String clazz, String disc, String pub, Date pub_data, String editor,
			float price,int count) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
		this.clazz = clazz;
		this.disc = disc;
		this.pub = pub;
		this.pub_data = pub_data;
		this.editor = editor;
		this.price = price;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public Date getPub_data() {
		return pub_data;
	}
	public void setPub_data(Date pub_data) {
		this.pub_data = pub_data;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}

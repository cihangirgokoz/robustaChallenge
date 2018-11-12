package com.robusta.excelread.model;

import javax.xml.bind.annotation.XmlRootElement;

public class JsonResponse {

	private String result;
	
	private String value;
	
	

	public JsonResponse() {
		super();
	}

	public JsonResponse(String result, String value) {
		super();
		this.result = result;
		this.value = value;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	@Override
	public String toString() {
		return "JsonResponse [result=" + result + ", value=" + value + "]";
	}	
	
}

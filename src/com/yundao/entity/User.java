package com.yundao.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1720498741620105960L;

	private String time; // 当前时间

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "User [time=" + time + "]";
	}
}

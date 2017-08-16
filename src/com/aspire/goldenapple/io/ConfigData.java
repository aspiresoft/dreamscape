package com.aspire.goldenapple.io;

import java.io.Serializable;

public class ConfigData implements Serializable {

	public int keys[];
	private static final long serialVersionUID = 333L;

	public ConfigData() {
		keys = new int[14];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = -1;
		}
	}

}

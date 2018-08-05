package com.dfdk.yunwei.shiro.cache.ex;

public class SerializationException extends Exception{
	
	private static final long serialVersionUID = -4274370409919001207L;
	
	public SerializationException(String msg) {
        super(msg);
    }
    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

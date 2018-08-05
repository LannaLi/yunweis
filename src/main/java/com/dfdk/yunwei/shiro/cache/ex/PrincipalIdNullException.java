package com.dfdk.yunwei.shiro.cache.ex;

public class PrincipalIdNullException extends RuntimeException {

	private static final long serialVersionUID = -3804500307296147575L;
	
	private static final String MESSAGE = "Principal Id shouldn't be null!";

    public PrincipalIdNullException(Class<? extends Object>  clazz, String idMethodName) {
        super(clazz + " id field: " +  idMethodName + ", value is null\n" + MESSAGE);
    }
}

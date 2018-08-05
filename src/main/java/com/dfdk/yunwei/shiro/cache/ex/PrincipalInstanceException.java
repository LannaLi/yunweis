package com.dfdk.yunwei.shiro.cache.ex;

public class PrincipalInstanceException extends RuntimeException {

	private static final long serialVersionUID = -7925941861296685568L;
	private static final String MESSAGE = "We need a field to identify this Cache Object in Redis. "
            + "So you need to defined an id field which you can get unique id to identify this principal. "
            + "For example, if you use UserInfo as Principal class, the id field maybe userId, userName, email, etc. "
            + "For example, getUserId(), getUserName(), getEmail(), etc.\n"
            + "Default value is authCacheKey or id, that means your principal object has a method called \"getAuthCacheKey()\" or \"getId()\"";

    public PrincipalInstanceException(Class<? extends Object> clazz, String idMethodName) {
        super(clazz + " must has getter for field: " +  idMethodName + "\n" + MESSAGE);
    }

    public PrincipalInstanceException(Class<? extends Object>  clazz, String idMethodName, Exception e) {
        super(clazz + " must has getter for field: " +  idMethodName + "\n" + MESSAGE, e);
    }
}

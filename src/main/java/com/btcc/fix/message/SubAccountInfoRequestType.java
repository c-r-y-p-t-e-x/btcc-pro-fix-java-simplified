package com.btcc.fix.message;

import quickfix.CharField;

/**
 * Created by zhenning on 16/5/11.
 */
public class SubAccountInfoRequestType extends CharField {

    public static final int FIELD = 9263;
    public static final char NOT_PUSH = '0';
    public static final char PUSH_SNAPSHOT = '1';

    public SubAccountInfoRequestType() {
        this(NOT_PUSH);
    }

    public SubAccountInfoRequestType(char data) {
        super(9263, data);
    }

}

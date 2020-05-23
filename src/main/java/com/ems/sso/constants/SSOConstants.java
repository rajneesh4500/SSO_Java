package com.ems.sso.constants;

public class SSOConstants {

    public static final String SSO_MAPPING = "/sso";

    public static final String VALIDATE_USER = "/validateUser";
    public static final String VALIDATE_PASS_PATH = "/validatePass";
    public static final String VALIDATE_USER_SESSION = "/validateSession";

    public static final String VALIDATE_USER_EMAIL = "select user_id from user_details_login where email_id = ?";

    public static final String VALIDATE_USER_USERID = "select user_id from user_details_login where emp_id = ?";

    public static final String VALIDATE_PASS = "{call EMP_PKG.VALIDATE_USER(?,?,?)}";
}

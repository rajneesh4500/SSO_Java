package com.ems.sso.repository;


import com.ems.sso.constants.SSOConstants;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Integer validateUserId(String userId){
        return validate(userId, SSOConstants.VALIDATE_USER_USERID);
    }
    public Integer validiateUserEmail(String emailId){
        return validate(emailId, SSOConstants.VALIDATE_USER_EMAIL);
    }

    public String validatePass(Integer userId, String pass){
        String rs = jdbcTemplate.execute(new CallableStatementCreator() {

            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement calla = con.prepareCall(SSOConstants.VALIDATE_PASS);
                calla.setInt(2,1);
                calla.setString(1,"admin");
                calla.registerOutParameter(3, OracleTypes.LONGNVARCHAR);
                return calla;
            }
        }, new CallableStatementCallback<String>() {

            @Override
            public String doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.execute();
                String rs = (String)cs.getString(3);

                return rs;
            }

        });
        return rs;
    }


    private Integer validate(String value, String sql){

        Integer userId = null;
        try {
            userId = jdbcTemplate.queryForObject(sql, new Object[]{value}, Integer.class);
        }
         catch(Exception e){
            userId = -1;
            }
        System.out.println(userId);
        if(userId == null || userId <=0){
            return -1;
        }
        return userId;
    }
}

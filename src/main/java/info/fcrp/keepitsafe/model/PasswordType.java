package info.fcrp.keepitsafe.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.type.StringType;

public class PasswordType extends StringType {
 
    @Override
    public void set(PreparedStatement st, Object value, int index)
            throws SQLException {
        
        //TODO encrypt
        super.set(st, value, index);
    }
    
    @Override
    public Object get(ResultSet rs, String name) throws SQLException {
        //TODO decrypt
        return super.get(rs, name);
    }
}

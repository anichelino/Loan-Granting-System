package in.co.loan.granting.system.config;


import org.hibernate.dialect.MySQLDialect;

public class MySQLCustomDialect extends MySQLDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=MyISAM";
    }
}

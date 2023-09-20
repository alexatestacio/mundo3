package cadastrobd.model.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static int getValue(String sequenceName) {
        ConectorBD db = ConectorBD.getInstance();
        try {
            ResultSet rs = db.getSelect("SELECT NEXT VALUE FOR Seq_PessoaID;");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package cadastrobd.model.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static int getValue(String sequenceName) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ConectorBD db = ConectorBD.getInstance();
        try {
            PreparedStatement pstmt = db.getPrepared("SELECT NEXT VALUE FOR ?");
            pstmt.setString(1, sequenceName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // Obtém o próximo valor da sequência
            } else {
                throw new SQLException("Não foi possível obter o próximo valor da sequência.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter valor da sequência: " + e.getMessage());
        } finally {
            db.close(rs);
            db.close(stmt);
        }
    }
}

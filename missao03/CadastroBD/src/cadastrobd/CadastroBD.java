package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.sql.SQLException;
import java.sql.ResultSet;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;


public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PessoaFisica pf = new PessoaFisica(
            1,
            "Alex",
            "Rua Fritz Utzeri 132",
            "Rio",
            "RJ",
            "(21) 99924-1427",
            "alex@aluno.com",
            "123.444.555-34"
        );

        PessoaJuridica pj = new PessoaJuridica(
            2,
            "Alex INC",
            "Rua Fritz Utzeri 132",
            "Rio",
            "RJ",
            "(21) 99924-1427",
            "alex@empresa.com",
            "12.345.678/0001-99"
        );
        pf.exibir();
        System.out.println("---------");
        pj.exibir();

        ConectorBD conn = ConectorBD.getInstance();
        try {
            ResultSet rs = conn.getSelect("SELECT * FROM pessoa");
            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }

            System.out.println(rowcount);

        } catch (SQLException e) {}





    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author daniel.freitas
 */
public class PresumidoSimplesMSG {

    private static String responsavel;

    public PresumidoSimplesMSG(String responsavel) {
        this.responsavel = responsavel;
    }

    public String gerarMsg() {
        String msg = "Prezado " + responsavel + ",\n\n\n"
                + "Você já organizou sua documentação contábil? São 15 documentos que você precisa nos enviar\n"
                + "mensalmente.\n"
                + "Lembre-se: a documentação deve ser enviada no primeiro dia útil de cada mês para elaborarmos\n"
                + "suas demonstrações contábeis e apurarmos seu imposto.\n"
                + "\n\n"
                + "O que enviar\n\n"
                + "1. Extratos bancários em OFX E PDF"
                + "2. Arquivo \"contas pagas\"(com a indicação do nome do fornecedor ou da despesa\n"
                + "efetivamente paga)\n"
                + "3. Posição do Estoque no último dia do mês\n"
                + "4. Posição do Contas a receber no último dia do mês\n"
                + "5. Posição do Contas a pagar no último dia do mês\n"
                + "6. Despesas digitalizadas\n"
                + "7. Notas dos serviços tomados\n"
                + "8. SPED FISCAL(envio semanal)\n"
                + "9. Arquivos XML’s das notas fiscais de frete, entrada e saída\n"
                + "10. Redução Z e memórias fiscais\n"
                + "11. Relação de Notas canceladas, inutilizadas, devolvidas e transferidas\n"
                + "\n\n\n"
                + "Arnaud Marcolino\n"
                + "ELITE Consultores do Brasil\n"
                + "Tel: 84 9 9108 8525  / 2020 7000\n"
                + "www.eliteconsultores.com.br";

        return msg;
    }
}

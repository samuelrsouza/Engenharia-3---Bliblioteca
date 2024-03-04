import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControleTest2 {
    private static final String RA_ALUNO_VERIFICADO = "10";
    private static final String RA_ALUNO_COM_DEBITO = "4";
    private static final String RA_ALUNO_NORMAL = "123";

    private static final int[] PRAZOS_EMPRESTIMO = {1, 2, 3}; // Prazos fictícios
    private static final int DIAS_ATRASO_MULTA = 3;
    private static final float VALOR_MULTA_POR_DIA = 2.0f;

    @Test
    public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
        Aluno aluno = new Aluno(RA_ALUNO_VERIFICADO);
        assertFalse("O aluno não verificado não deve ser autorizado a fazer empréstimos", aluno.verficaAluno());

        Controle c = new Controle();
        boolean retorno = c.emprestar(RA_ALUNO_VERIFICADO, PRAZOS_EMPRESTIMO.length, PRAZOS_EMPRESTIMO);
        assertFalse("O empréstimo não deve ser permitido para o aluno não verificado", retorno);
    }

    @Test
    public void testNaoEmprestaLivrosParaAlunoComDebito() {
        Aluno aluno = new Aluno(RA_ALUNO_COM_DEBITO);
        assertFalse("AAAAA", aluno.verificaDebito());

        Controle c = new Controle();
        boolean retorno = c.emprestar(RA_ALUNO_COM_DEBITO, PRAZOS_EMPRESTIMO.length, PRAZOS_EMPRESTIMO);
        assertFalse("O empréstimo não deve ser permitido para o aluno com débito", retorno);
    }

    @Test
    public void testEmprestaLivros() {
        Controle c = new Controle();
        boolean retorno = c.emprestar(RA_ALUNO_NORMAL, PRAZOS_EMPRESTIMO.length, PRAZOS_EMPRESTIMO);
        assertTrue("Está emprestando normalmente", retorno);
    }

    @Test
    public void testCalculaMulta() {
        // Cria um livro e define a data prevista para DIAS_ATRASO_MULTA dias atrás e a data de devolução como hoje
        Livro livro = new Livro(1);
        Date dataPrevista = new Date(System.currentTimeMillis() - (86400000 * DIAS_ATRASO_MULTA)); // Data prevista há DIAS_ATRASO_MULTA dias
        Date dataDevolucao = new Date(System.currentTimeMillis()); // Data de devolução (hoje)

        // Cria um item com o livro e as datas definidas
        Item item = new Item(livro, dataPrevista);
        item.dataDevolucao = dataDevolucao;

        // Calcula a multa esperada para DIAS_ATRASO_MULTA dias de atraso (VALOR_MULTA_POR_DIA por dia) e verifica se está correta
        float multaEsperada = VALOR_MULTA_POR_DIA * DIAS_ATRASO_MULTA; // Multa esperada para DIAS_ATRASO_MULTA dias de atraso (VALOR_MULTA_POR_DIA por dia)
        assertEquals("A multa deve ser calculada corretamente para DIAS_ATRASO_MULTA dias de atraso", multaEsperada, item.calculaMulta(), 0.01f);
    }
}

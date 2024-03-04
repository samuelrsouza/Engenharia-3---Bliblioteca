import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    
 public class ControleTest {

	 @Test
	 public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
	     String ra = "10";
	     Aluno aluno = new Aluno(ra);
	     
	     assertFalse("O aluno não verificado não deve ser autorizado a fazer empréstimos", aluno.verficaAluno());

	     int[] prazos = {1, 2, 3}; // 
	     Controle c = new Controle();
	     boolean retorno = c.emprestar(ra, prazos.length, prazos);
	     assertFalse("O empréstimo não deve ser permitido para o aluno não verificado", retorno);
	 }

	@Test
	public void testNaoEmprestaLivrosParaAlunoComDebito() {
		String ra = "4";
		Aluno aluno = new Aluno(ra);
		assertFalse("AAAAA",aluno.verificaDebito());

		int[] prazos = {1,2,3};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos.length, prazos);
	    assertFalse("O empréstimo não deve ser permitido para o aluno com débito", retorno);	
	}

	@Test
	public void testEmprestaLivros() {
		String ra = "123";
		int[] prazos = {1,3,5,6};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos.length, prazos);
		assertTrue("Está emprestando normalmente",retorno);
	}
	
	@Test
	public void testCalculaMulta() {
	    // Cria um livro e define a data prevista para 3 dias atrás e a data de devolução como hoje
	    Livro livro = new Livro(1);
	    Date dataPrevista = new Date(System.currentTimeMillis() - (86400000 * 3)); // Data prevista há 3 dias
	    Date dataDevolucao = new Date(System.currentTimeMillis()); // Data de devolução (hoje)

	    // Cria um item com o livro e as datas definidas
	    Item item = new Item(livro, dataPrevista);
	    item.dataDevolucao = dataDevolucao;

	    // Calcula a multa esperada para 3 dias de atraso (R$2,00 por dia) e verifica se está correta
	    float multaEsperada = 6.0f; // Multa esperada para 3 dias de atraso (R$2,00 por dia)
	    assertEquals("A multa deve ser calculada corretamente para 3 dias de atraso", multaEsperada, item.calculaMulta(), 0.01f);
	}


}


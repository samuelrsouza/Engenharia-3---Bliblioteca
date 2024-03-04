import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Date;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmprestimoTestTest {

    @Test
    public void testEmpresta1LivroNaoAplicaDataExtra() {
        List<Livro> livros = new ArrayList<>();
        Livro l = new Livro(1);
        livros.add(l);

        assertDataDevolucaoCorreta(livros, 0);
    }

    @Test
    public void testEmpresta2LivrosNaoAplicaDataExtra() {
        List<Livro> livros = new ArrayList<>();
        Livro primeiroLivro = new Livro(0);
        Livro segundoLivro = new Livro(1);
        livros.add(primeiroLivro);
        livros.add(segundoLivro);

        assertDataDevolucaoCorreta(livros, 0);
    }

    @Test
    public void testEmpresta3LivrosAplicaDataExtra() {
        List<Livro> livros = new ArrayList<>();
        Livro primeiroLivro = new Livro(0);
        Livro segundoLivro = new Livro(1);
        Livro terceiroLivro = new Livro(2);
        livros.add(primeiroLivro);
        livros.add(segundoLivro);
        livros.add(terceiroLivro);

        assertDataDevolucaoCorreta(livros, DIAS_EXTRA_DEVOLUCAO_1);
    }

    @Test
    public void testEmpresta4LivrosAplicaDataExtra() {
        List<Livro> livros = new ArrayList<>();
        Livro primeiroLivro = new Livro(0);
        Livro segundoLivro = new Livro(1);
        Livro terceiroLivro = new Livro(2);
        Livro quartoLivro = new Livro(3);
        livros.add(primeiroLivro);
        livros.add(segundoLivro);
        livros.add(terceiroLivro);
        livros.add(quartoLivro);

        assertDataDevolucaoCorreta(livros, DIAS_EXTRA_DEVOLUCAO_2);
    }

    @Test
    public void testEmpresta5LivrosAplicaDataExtra() {
        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Livro l = new Livro(i);
            livros.add(l);
        }

        assertDataDevolucaoCorreta(livros, DIAS_EXTRA_DEVOLUCAO_3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmprestaMaisDe5Livros() {
        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < MAX_LIVROS_EMPRESTIMO + 1; i++) {
            Livro l = new Livro(i);
            livros.add(l);
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
    }

    private void assertDataDevolucaoCorreta(List<Livro> livros, int diasExtras) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);

        Item ultimoItem = emprestimo.item.get(livros.size() - 1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, diasExtras);

        assertEquals("A data de devolução do último livro emprestado deve aplicar data extra",
            calendar.getTime().getDay(),
            ultimoItem.getDataDevolucao().getDay());
    }

    public static final int DIAS_EXTRA_DEVOLUCAO_1 = 2;
    public static final int DIAS_EXTRA_DEVOLUCAO_2 = 5;
    public static final int DIAS_EXTRA_DEVOLUCAO_3 = 8;
    public static final int MAX_LIVROS_EMPRESTIMO = 5;
}






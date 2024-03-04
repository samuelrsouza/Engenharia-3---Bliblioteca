import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EmprestimoTest {

    @Parameters(name = "{index}: Dias Extras: {0}, Quantidade de Livros: {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { 5, 3 }, // 5 dias extras, 3 livros
            { 8, 4 }, // 8 dias extras, 4 livros
            { 11, 5 } // 11 dias extras, 5 livros
        });
    }

    private int diasExtras;
    private int quantidadeLivros;

    public EmprestimoTest(int diasExtras, int quantidadeLivros) {
        this.diasExtras = diasExtras;
        this.quantidadeLivros = quantidadeLivros;
    }

    private Date calcularDataDevolucao(int diasExtras) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, diasExtras);
        return calendar.getTime();
    }

    @Test
    public void testEmprestaLivrosAplicaDataExtra() {
        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < quantidadeLivros; i++) {
            livros.add(new Livro(i));
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);

        Item ultimoItem = emprestimo.item.get(quantidadeLivros - 1);

        Date dataDevolucaoEsperada = calcularDataDevolucao(diasExtras);
        assertEquals("A data de devolução do último livro emprestado deve aplicar data extra",
                     dataDevolucaoEsperada.getDay(), ultimoItem.getDataDevolucao().getDay());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmprestaMaisDe5Livros() {
        List<Livro> livros = new ArrayList<>();
        Livro primeiroLivro = new Livro(0);
        Livro segundoLivro = new Livro(1);
        Livro terceiroLivro = new Livro(2);
        Livro quartoLivro = new Livro(3);
        Livro quintoLivro = new Livro(4);
        Livro sextoLivro = new Livro(5);
        livros.add(primeiroLivro);
        livros.add(segundoLivro);
        livros.add(terceiroLivro);
        livros.add(quartoLivro);
        livros.add(quintoLivro);
        livros.add(sextoLivro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.emprestar(livros);
    }
    
}

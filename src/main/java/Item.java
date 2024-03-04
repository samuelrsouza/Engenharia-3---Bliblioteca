
import java.util.Calendar;
import java.util.Date;

public class Item {
	Livro livro;
    public Date dataDevolucao;
    public Date dataPrevista;
    
	public Item(Livro livro, Date dataPrevista) {
		super();
		this.livro = livro;
		this.dataPrevista = dataPrevista;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Date calculaDataDevolucao(Date data) {  
	   dataDevolucao = data;
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(data);
	   calendar.add(Calendar.DATE, livro.verPrazo());
       dataDevolucao = calendar.getTime();
	   return dataDevolucao;
	}
	
	public float calculaMulta() {
        if (dataPrevista == null || dataDevolucao == null) {
            throw new IllegalStateException("Datas não foram devidamente inicializadas.");
        }
        
        long diff = dataDevolucao.getTime() - dataPrevista.getTime();
        if (diff <= 0) {
            return 0.0f; // Sem multa se devolvido antes ou na data prevista
        } else {
            int diasAtraso = (int) (diff / (1000 * 60 * 60 * 24)); // Converte milissegundos para dias
            float multa = diasAtraso * 2.0f; // Assumindo multa de R$2,00 por dia de atraso
            return multa;
        }
    }

}

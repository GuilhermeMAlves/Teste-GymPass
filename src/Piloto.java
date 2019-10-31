import java.util.List;

public class Piloto {
	
	private String nome;
	private int codPiloto;
	private List <Volta> listaVoltas;
	private String tempoTotal;
	private int numeroTotalVoltas;
	private long tempoTotalEmMili;
	private String melhorVolta;
	private Double mediaVelocidade;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodPiloto() {
		return codPiloto;
	}
	public void setCodPiloto(int codPiloto) {
		this.codPiloto = codPiloto;
	}
	public List<Volta> getListaVoltas() {
		return listaVoltas;
	}
	public void setListaVoltas(List<Volta> listaVoltas) {
		this.listaVoltas = listaVoltas;
	}
	public String getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	public int getNumeroTotalVoltas() {
		return numeroTotalVoltas;
	}
	public void setNumeroTotalVoltas(int numeroTotalVoltas) {
		this.numeroTotalVoltas = numeroTotalVoltas;
	}
	public long getTempoTotalEmMili() {
		return tempoTotalEmMili;
	}
	public void setTempoTotalEmMili(long tempoTotalEmMili) {
		this.tempoTotalEmMili = tempoTotalEmMili;
	}
	public String getMelhorVolta() {
		return melhorVolta;
	}
	public void setMelhorVolta(String melhorVolta) {
		this.melhorVolta = melhorVolta;
	}
	public Double getMediaVelocidade() {
		return mediaVelocidade;
	}
	public void setMediaVelocidade(Double mediaVelocidade) {
		this.mediaVelocidade = mediaVelocidade;
	}

}

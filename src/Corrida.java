
public class Corrida {
	
	private String hora;
	private int codPiloto;
	private int numVolta;
	private String piloto;
	private String tempoVolta;
	private String velocidade;
	
	public Corrida() {
		
	}
	
	public Corrida(String hora, int codPiloto, int numVolta, String piloto, String tempoVolta, String velocidade) {
		super();
		this.hora = hora;
		this.codPiloto = codPiloto;
		this.numVolta = numVolta;
		this.piloto = piloto;
		this.tempoVolta = tempoVolta;
		this.velocidade = velocidade;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getCodPiloto() {
		return codPiloto;
	}
	public void setCodPiloto(int codPiloto) {
		this.codPiloto = codPiloto;
	}
	public int getNumVolta() {
		return numVolta;
	}
	public void setNumVolta(int numVolta) {
		this.numVolta = numVolta;
	}
	public String getPiloto() {
		return piloto;
	}
	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}
	public String getTempoVolta() {
		return tempoVolta;
	}
	public void setTempoVolta(String tempoVolta) {
		this.tempoVolta = tempoVolta;
	}
	public String getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}

}

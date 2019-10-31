import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Resultado {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List <Corrida> listaCorrida = new ArrayList<Corrida>();
		List <Piloto> listaPilotos = new ArrayList<Piloto>();
		List <Volta> listaVoltas = new ArrayList<Volta>();
		List <Integer> verificaPilotos = new ArrayList<Integer>();
		Util util = new Util();
		Piloto piloto;
		System.out.printf("Coloque o arquivo na pasta C:/Corrida/corrida.txt\n\n");

		try {
			// Leitura do arquivo
			FileReader arq = new FileReader("C:\\Corrida\\corrida.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); 
			
			int qtd = 0;
			
			// percorre linha a linha o conteudo do arquivo
			while (linha != null) {
				qtd++;
				// ignora a primeira linha - cabeçalho
				if (qtd == 1) {
					linha = lerArq.readLine();
					continue;
				}
				
				String atributos[];
				// quebro o conteudo da linha por | para ter cada informação separada
				atributos = linha.split("\\|");
				String conteudo;
				Corrida c = new Corrida();
				Volta v = new Volta();
				for (int i = 0; i < atributos.length; i++) {
					if (atributos[i].contains("â€“")) { // remove caractere -
						conteudo = atributos[i].replaceAll("â€“", "");
					} else {
						conteudo = atributos[i];
					}
					conteudo = conteudo.trim();
					// coloco cada informação em seu atributo na classe Volta e Corrida
					switch (i){
		            case 0: // hora
		                c.setHora(conteudo);
		                v.setHora(conteudo);
		                break;
		            case 1: // cod piloto
		            	// faço uma lista única de pilotos (pego o cód do piloto uma única vez)
		            	if (!verificaPilotos.contains(Integer.parseInt(conteudo))) {
		            		verificaPilotos.add(Integer.parseInt(conteudo));
		            	}
		                c.setCodPiloto(Integer.parseInt(conteudo));
		                v.setCodPiloto(Integer.parseInt(conteudo));
		                break;
		            case 2: // nome piloto
		                c.setPiloto(conteudo);
		                break;
		            case 3: // numero da volta
		            	c.setNumVolta(Integer.parseInt(conteudo));
		            	v.setNumVolta(Integer.parseInt(conteudo));
		                break;
		            case 4: // tempo da volta
		                c.setTempoVolta(conteudo);
		                v.setTempo(conteudo);
		                break;
		            case 5: // velocidade
		                c.setVelocidade(conteudo);
		                v.setVelocidade(conteudo);
		                break;
					}
				}
				
				listaCorrida.add(c);
				listaVoltas.add(v);

				linha = lerArq.readLine();
			}
			
			// aqui tenho populado as listas de Voltas e Corridas
			
			// pela lista de códigos de pilotos eu busco as demais informações do piloto e crio uma lista de pilotos
			for (Integer codPiloto : verificaPilotos) {
				for (Corrida corrida : listaCorrida) {
					if (codPiloto == corrida.getCodPiloto()) {
						piloto = new Piloto();
						piloto.setCodPiloto(codPiloto);
						piloto.setNome(corrida.getPiloto());
						
						listaPilotos.add(piloto);
						break;
					}
				}
			}
			
			// lista de Pilotos com nome e cód
			
			long melhorVoltaCorrida = 0;
			String pilotoMelhorVolta = "";
			
			// loop com a lista de pilotos
			for (Piloto p : listaPilotos) {
				List <Volta> voltasDoPiloto = new ArrayList<Volta>();
				long tempoTotal = 0;
				int totalVoltasPiloto = 0;
				Double somaVelMedia = 0D;
				long melhorVoltaPiloto = 0;
				// loop com a lista de voltas
				for (Volta v : listaVoltas) {
					if (p.getCodPiloto() == v.getCodPiloto()) {
												
						// quebro a informação de tempo para pegar minutos, segundos e milisegundos separadamente
						String[] tempo = v.getTempo().split(":");
						String[] tempoSegMili = tempo[1].split("\\.");
						tempoTotal = tempoTotal + util.converteMilisegundos(Integer.parseInt(tempo[0]), Integer.parseInt(tempoSegMili[0]), Integer.parseInt(tempoSegMili[1]));
						// verifico se a volta atual é a melhor volta do piloto
						if (melhorVoltaPiloto == 0) {
							melhorVoltaPiloto = util.converteMilisegundos(Integer.parseInt(tempo[0]), Integer.parseInt(tempoSegMili[0]), Integer.parseInt(tempoSegMili[1]));
						} else {
							long voltaEmMili = util.converteMilisegundos(Integer.parseInt(tempo[0]), Integer.parseInt(tempoSegMili[0]), Integer.parseInt(tempoSegMili[1]));
							if (voltaEmMili < melhorVoltaPiloto) {
								melhorVoltaPiloto = voltaEmMili;
							}
						}
						// verifico se a volta atual é a melhor volta da corrida
						if (melhorVoltaCorrida == 0) {
							melhorVoltaCorrida = util.converteMilisegundos(Integer.parseInt(tempo[0]), Integer.parseInt(tempoSegMili[0]), Integer.parseInt(tempoSegMili[1]));
							pilotoMelhorVolta = p.getNome();
						} else {
							long voltaEmMili = util.converteMilisegundos(Integer.parseInt(tempo[0]), Integer.parseInt(tempoSegMili[0]), Integer.parseInt(tempoSegMili[1]));
							if (voltaEmMili < melhorVoltaCorrida) {
								melhorVoltaCorrida = voltaEmMili;
								pilotoMelhorVolta = p.getNome();
							}
						}
						// contagem de voltas do piloto
						totalVoltasPiloto++;
						// calculo para velocidade média do piloto					
						Double velVolta = Double.valueOf(v.getVelocidade().replace(",", "."));
						somaVelMedia = somaVelMedia + velVolta;
						// lista com as voltas do piloto
						voltasDoPiloto.add(v);
					}
				}
				
				p.setMediaVelocidade(somaVelMedia / totalVoltasPiloto);
				p.setMelhorVolta(util.converteData(melhorVoltaPiloto));
				p.setTempoTotalEmMili(tempoTotal);
				p.setTempoTotal(util.converteData(tempoTotal));
				p.setNumeroTotalVoltas(totalVoltasPiloto);
				p.setListaVoltas(voltasDoPiloto);
			}
			
			// ordeno a lista de pilotos por numero de voltas
			Collections.sort(listaPilotos, new Comparator() {
				public int compare(Object o1, Object o2) {
					Piloto p1 = (Piloto) o1;
					Piloto p2 = (Piloto) o2;
					return p1.getNumeroTotalVoltas() > p2.getNumeroTotalVoltas() ? -1 : (p1.getNumeroTotalVoltas() < p2.getNumeroTotalVoltas() ? +1 : 0);
				}
			});
			
			int verificaVoltas = 0;
			List <Piloto> listaPilotosAux = new ArrayList<Piloto>();
			List <Piloto> listaPilotosOrdenada = new ArrayList<Piloto>();
			// falo a ordenação de pilotos por tempo de volta, listaPilotosAux guarda uma lista de pilotos com a mesma 
			// quantidade de voltas e listaPilotosOrdenada é minha lista final com os pilotos ordenados por voltas e tempo
			for (Piloto p : listaPilotos) {
				if (verificaVoltas != p.getNumeroTotalVoltas()) {
					if (listaPilotosAux.size() != 0) {
						Collections.sort(listaPilotosAux, new Comparator() {
							public int compare(Object o1, Object o2) {
								Piloto p1 = (Piloto) o1;
								Piloto p2 = (Piloto) o2;
								return p1.getTempoTotalEmMili() < p2.getTempoTotalEmMili() ? -1 : (p1.getTempoTotalEmMili() > p2.getTempoTotalEmMili() ? +1 : 0);
							}
						});
						for (Piloto p2 : listaPilotosAux) {
							listaPilotosOrdenada.add(p2);
						}
						
					}
					
					listaPilotosAux = new ArrayList<Piloto>();
					listaPilotosAux.add(p);
				} else {
					listaPilotosAux.add(p);
				}
				verificaVoltas = p.getNumeroTotalVoltas();
			}
			
			Collections.sort(listaPilotosAux, new Comparator() {
				public int compare(Object o1, Object o2) {
					Piloto p1 = (Piloto) o1;
					Piloto p2 = (Piloto) o2;
					return p1.getTempoTotalEmMili() < p2.getTempoTotalEmMili() ? -1 : (p1.getTempoTotalEmMili() > p2.getTempoTotalEmMili() ? +1 : 0);
				}
			});
			for (Piloto p2 : listaPilotosAux) {
				listaPilotosOrdenada.add(p2);
			}
			
			// tratamento para verificar tempo total de corrida
			
			String[] tempoPrimeiraVolta = listaVoltas.get(0).getHora().split(":");
			String[] tempoPrimeiraVolta2 = tempoPrimeiraVolta[2].split("\\.");
			
			String[] tempoUltimaVolta = listaVoltas.get(listaVoltas.size() - 1).getHora().split(":");
			String[] tempoUltimaVolta2 = tempoUltimaVolta[2].split("\\.");
			
			long tempoTotalMili = 0;
			if (tempoPrimeiraVolta[0].equals(tempoUltimaVolta[0])) {
				long primeiraVoltaMili = util.converteMilisegundos(Integer.parseInt(tempoPrimeiraVolta[1]), Integer.parseInt(tempoPrimeiraVolta2[0]), Integer.parseInt(tempoPrimeiraVolta2[1]));
				long ultimaVoltaMili = util.converteMilisegundos(Integer.parseInt(tempoUltimaVolta[1]), Integer.parseInt(tempoUltimaVolta2[0]), Integer.parseInt(tempoUltimaVolta2[1]));
				tempoTotalMili = ultimaVoltaMili - primeiraVoltaMili;
			} else {
				// se a hora fosse diferente teria que fazer um tratamento no método da classe util
			}
			
			// Exibição de lista final ordenada por voltas e tempo			
			
			int posicao = 0;
			System.out.println("Tempo Total de Corrida: " + util.converteData(tempoTotalMili));
			System.out.println("Melhor Volta: " + util.converteData(melhorVoltaCorrida) + " - Piloto: " + pilotoMelhorVolta);
			for (Piloto p :listaPilotosOrdenada) {
				posicao++;
				DecimalFormat df = new DecimalFormat("#,###.00");
				System.out.println(posicao + "º " + p.getNome() + " Voltas: " + p.getNumeroTotalVoltas() + " Tempo Total: " + p.getTempoTotal() + " Velocidade Média: " + df.format(p.getMediaVelocidade()) + " Melhor Volta: " + p.getMelhorVolta());
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n",
					e.getMessage());
		}

		System.out.println();
	}

}

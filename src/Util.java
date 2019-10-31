
public class Util {
	
	public long converteMilisegundos (int minuto, int segundo, int mili) {
		long totalMili = (minuto * 1000 * 60) + (segundo * 1000) + mili;
		return totalMili;
	}
	
	public String converteData(long mili) {
		int minutos = (int) (mili / 60000);
		int segundos = (int) (mili % 60000) / 1000;
		int milisegundos = (int) (mili % 60000) % 1000;
		
		String minutosS = String.valueOf(minutos);
		String segundosS = String.valueOf(segundos);
		String milisegundosS = String.valueOf(milisegundos);
		
		if (minutosS.length() == 1) {
			minutosS = "0" + minutosS;
		}
		
		if (segundosS.length() == 1) {
			segundosS = "0" + segundosS;
		}
		
		if (milisegundosS.length() == 2) {
			milisegundosS = "0" + milisegundosS;
		} else if (milisegundosS.length() == 1) {
			milisegundosS = "00" + milisegundosS;
		}
			
		
		return minutosS + ":" + segundosS + ":" + milisegundosS ;
	}

}

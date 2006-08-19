package asearch.preparador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class Constantes {
	
	public Collection<String> stopWords = new ArrayList<String>();
	private static Constantes instancia = null;
	
	private Constantes(){
		try {
	        
			// lê o arquivo com stop words a serem eliminadas.
			
			InputStream input = getClass().getResourceAsStream("/stopwords.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			
	        String palavra;
	        while ((palavra = in.readLine()) != null) {
	           stopWords.add(palavra);
	        }
	        in.close();
	        
	    } catch (IOException e) {
	    	System.out.println(" Erro ao ler arquivo de configuração.");
	    	e.printStackTrace();
	    }
		
	}
	
	public static Constantes getInstancia(){
		if(instancia == null){
			instancia = new Constantes();
		}
		return instancia;
	}

	public Collection<String> getStopWords() {
		return stopWords;
	}


}

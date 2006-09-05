package asearch;

import java.io.IOException;

import asearch.indexador.BaseIndices;
import asearch.indexador.Indexador;

public class PreparadorConsultas {

	
		public static void main(String[] asr) throws Throwable, ClassNotFoundException {
			String consulta = "probability";
			Indexador.carregarBaseIndices("base.asr");
			BaseIndices base = Indexador.getBase();
			
			
		}
}

/**
 * 
 */
package asearch;

import java.io.File;
import java.io.IOException;

import asearch.base.Artigo;
import asearch.indexador.BaseIndices;
import asearch.indexador.Indexador;

/**
 * @author maas
 *
 */
public class ExtrairCentroide {
	static long before;
	static long after;
	
	private static void start() {
		before = System.currentTimeMillis();
	}
	
	private static void end() {
		after = System.currentTimeMillis();
		long diff = (after - before);
		
		System.out.println("[" +   Runtime.getRuntime().freeMemory() / 1024.0 + " MB ]"+ (diff/1000.0));
		after = before = 0;
		
		
		System.gc();
	}
	public static void extrairCentroide(File baseFile, int centroide) {
		try {
			Indexador.carregarBaseIndices(baseFile.getAbsolutePath());
			BaseIndices base = Indexador.getBase();
			
			base.atualizarPesos();
			
			for (Artigo artigo : base.getArtigos()) {
				start();
				System.out.println("processando artigo: " + artigo.getFile().getName() + " palavras antes: "+ artigo.getPalavras().size());
				artigo.extrairCentroide(centroide);
				System.out.println("" + artigo);
				end();
			}
			File baseNewFile = new File(baseFile.getParent(), baseFile.getName().concat("centroid"));
			Indexador.salvarBaseIndices(baseNewFile.getAbsolutePath());;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length <= 2 && args.length >= 1) {
			int centroide = 50;
			if (args.length == 2) {
				centroide = Integer.parseInt(args[1]);
			}
			extrairCentroide(new File(args[0]), centroide);
		} else {
			System.out.println("uso: ExtrairCentroide [[base] [centroide]]");
		}		
	}

}

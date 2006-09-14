package asearch;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import asearch.base.Artigo;
import asearch.indexador.EntradaBaseIndice;
import asearch.indexador.Indexador;
import asearch.preparador.Preparador;

/**
 * @author maas
 *
 */
public class ProcessoOffline {
	static long before;
	static long after;
	
	private static void start() {
		before = System.currentTimeMillis();
	}
	
	private static void end() {
		after = System.currentTimeMillis();
		long diff = (after - before);
		System.out.println(""+ (diff/1000.0));
		after = before = 0;
	}
	
	public static void indexarDiretorio(File dir, boolean subdirs) {
		
		System.out.println("indexando diretório " + dir.getAbsolutePath());
		if (dir.isDirectory()) {
			String[] files = dir.list();
			System.out.println(files.length + " entries.");
			for (String str : files) {
				System.out.println("\tfile: " + str);
				File file = new File(dir, str);
				if (file.isDirectory() && subdirs) {
					indexarDiretorio(file, subdirs);
				} else if (file.exists() && file.getName().endsWith(".pdf")) { // somente pdfs
					try {
						String s = file.getName();
						System.out.println("\t\tperparando \"" + s + "\"");
						
						start();
						Artigo artigo = Preparador.prepararArtigo(file.getAbsolutePath());
						end();
						
						System.out.println("\t\tindexando \"" + s + "\"");
						start();
						Indexador.indexarArtigo(artigo);
						end();
						
						System.out.println("\n");
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		indexarDiretorio(new File("C:\\temp\\maas\\asearch_svn\\pdfs"), true);
		Indexador.salvarBaseIndices("base2.asr");
		analisarBase("base2.asr");
	}
	
	
	private static void analisarBase(String file) throws IOException, ClassNotFoundException {
		Indexador.carregarBaseIndices(file);
		
		System.out.println("recalculando pesos");
		start();
		Indexador.atualizarPesos();
		end();
		
//		System.out.println("" + Indexador.getBase());
//		System.out.println("====\nImprimindo arquivos:\n\n\n");

//		for (Artigo artigo : Indexador.getBase().getArtigos()) {
//			System.out.println(artigo.getNomeArquivo()+"\n=====\n" + artigo);					
//		}
		
		int vocabulario = Indexador.getBase().getEntradas().size();
		EntradaBaseIndice[] palavras = Indexador.getBase().getEntradas().toArray(new EntradaBaseIndice[0]);
		Arrays.sort(palavras, new ComparaFrequencia());
		
		System.out.println("vocabulário: " + vocabulario + " palavra(s)\n");
		System.out.println("\nmais frequentes:\n\n");
		
		for(int i =0;i<palavras.length && i<20;++i) {
			System.out.println((i+1) + ") " + palavras[i].getTermo() + " \t " + palavras[i].getOcorrenciasNaBase());
		}

		palavras = Indexador.getBase().getEntradas().toArray(new EntradaBaseIndice[0]);
		Arrays.sort(palavras, new ComparaIDF());

		System.out.println("\nmais importantes:\n\n");
		
		for(int i =0;i<palavras.length && i<20;++i) {
			System.out.println((i+1) + ") " + palavras[i].getTermo() + " \t " + palavras[i].getIDF());
		}
		
	}


	private static class ComparaFrequencia implements Comparator<EntradaBaseIndice> {
		public int compare(EntradaBaseIndice arg0, EntradaBaseIndice arg1) {
			return arg1.getOcorrenciasNaBase() - arg0.getOcorrenciasNaBase();
		}
	}

	private static class ComparaIDF implements Comparator<EntradaBaseIndice> {
		public int compare(EntradaBaseIndice arg0, EntradaBaseIndice arg1) {
			return (int)Math.signum((arg1.getIDF() - arg0.getIDF()));
		}
	}
}

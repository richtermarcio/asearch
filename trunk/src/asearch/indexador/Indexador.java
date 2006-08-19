package asearch.indexador;

import java.io.IOException;

import asearch.base.Artigo;
import asearch.preparador.Preparador;

public class Indexador {
	private static BaseIndices base = new BaseIndices();

	public static void indexarArtigo(Artigo artigo) {
		if (base!=null) {
			base.indexar(artigo);
		}
	}
	
	public static void salvarBaseIndices(String arquivo) {
		
	}
	
	public static void carregarBaseIndices(String arquivo) {

	}
	
	public static void main(String args[]) {
		try {
			Artigo artigo = Preparador.prepararArtigo("C://Documents and Settings//maas//workspace//minweb//pdfs//teste.pdf");
//			System.out.println("" + artigo);
			
			System.out.println("==========================");
	
			indexarArtigo(artigo);
			System.out.println("BASE: " + getBase());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BaseIndices getBase() {
		return base;
	}

	public static void setBase(BaseIndices base) {
		Indexador.base = base;
	}
}

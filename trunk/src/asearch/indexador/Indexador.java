package asearch.indexador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import asearch.base.Artigo;
import asearch.preparador.Preparador;

public class Indexador {
	private static BaseIndices base = new BaseIndices();

	public static void indexarArtigo(Artigo artigo) {
		if (base!=null) {
			base.indexar(artigo);
		} else {
			throw new IllegalStateException("no base loaded");			
		}
	}
	
	public static void atualizarPesos() {
		base.atualizarPesos();		
	}
	
	public static void salvarBaseIndices(String arquivo) throws IOException {
		if (base != null) {
			FileOutputStream fos = new FileOutputStream(arquivo, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(base);
		} else {
			throw new IllegalStateException("no base loaded");
		}
	}
	
	public static void carregarBaseIndices(String arquivo) throws IOException, ClassNotFoundException {
		if (base != null) {
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			base = (BaseIndices)ois.readObject();
		} else {
			throw new IllegalStateException("no base loaded");
		}
	}
	
	public static void main(String args[]) {
		try {
			Artigo artigo = Preparador.prepararArtigo("C://Temp//maas//asearch//pdfs//teste.pdf");			
			indexarArtigo(artigo);
			System.out.println("BASE: " + getBase());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BaseIndices getBase() {
		return base;
	}
}

package alura.aulas.xstream.marcus;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void serializacaoXStream() {

		String xmlDeOrigem = xmlDeOrigem();

		Compra compra = compraComGeladeiraFerro();

		XStream xstream = xStreamParaCompraProduto();

//		Compra compraResultado = (Compra) xstream.fromXML(xmlDeOrigem);
//		assertEquals(compra, compraResultado);
		assertEquals(xmlDeOrigem, xstream.toXML(compra));

	}

	private Compra compraComGeladeiraFerro() {

		Produto geladeira = buildGeladeira();
		Produto ferro = buildFerro();

		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(geladeira);
		produtos.add(ferro);

		return new Compra(15, produtos);

	}

	private Compra compraComLivroMusica() {

		Livro livro = buildLivro();
		Musica musica = buildMusica();

		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(livro);
		produtos.add(musica);

		return new Compra(15, produtos);

	}

	private Musica buildMusica() {
		return new Musica("Meu Passeio", 100.0, "música livre", 1590);
	}

	private Livro buildLivro() {
		return new Livro("O Pássaro Raro", 100.0, "dez histórias sobre a existência", 1589);
	}

	private Produto buildFerro() {
		return new Produto("ferro de passar", 100, "ferro com vaporizador", 1588);
	}

	private Produto buildGeladeira() {
		return new Produto("geladeira", 1000, "geladeira duas portas", 1587);
	}

	private XStream xStreamParaCompraProduto() {

		XStream xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.registerLocalConverter(Produto.class, "preco", new PrecoConverter());
		/*xstream.alias("musica", Musica.class);
		xstream.alias("livro", Livro.class);*/
		xstream.alias("compra", Compra.class);
		xstream.alias("produto", Produto.class);
		/*xstream.aliasField("descrição", Produto.class, "descricao");*/
		/*xstream.addImplicitCollection(Compra.class, "produtos");*/
		/*xstream.addDefaultImplementation(LinkedList.class, List.class);*/
		xstream.useAttributeFor(Produto.class, "codigo");
		return xstream;

	}

	private String xmlDeOrigem() {

		 String xmlDeOrigem = "<compra>\n" +
		 "  <id>15</id>\n" +
		 "  <produtos>\n" +
		 "    <produto codigo=\"1587\">\n" +
		 "      <nome>geladeira</nome>\n" +
		 "      <preco>R$ 1.000,00</preco>\n" +
		 "      <descricao>geladeira duas portas</descricao>\n" +
		 "    </produto>\n" +
		 "    <produto codigo=\"1588\">\n" +
		 "      <nome>ferro de passar</nome>\n" +
		 "      <preco>R$ 100,00</preco>\n" +
		 "      <descricao>ferro com vaporizador</descricao>\n" +
		 "    </produto>\n" +
		 "  </produtos>\n" +
		 "</compra>";
		
		// Implicit Collection
		// String xmlDeOrigem = "<compra>\n" + " <id>15</id>\n" + " <produto
		// codigo=\"1587\">\n"
		// + " <nome>geladeira</nome>\n" + " <preco>1000.0</preco>\n"
		// + " <descricao>geladeira duas portas</descricao>\n" + " </produto>\n"
		// + " <produto codigo=\"1587\">\n" + " <nome>geladeira</nome>\n" + "
		// <preco>1000.0</preco>\n"
		// + " <descricao>geladeira duas portas</descricao>\n" + " </produto>\n"
		// + "</compra>";
		
//		String xmlDeOrigem = "<compra>\n" + "  <id>15</id>\n" + "  <produtos class=\"linked-list\">\n"
//				+ "    <livro codigo=\"1589\">\n" + "      <nome>O Pássaro Raro</nome>\n"
//				+ "      <preco>100.0</preco>\n" + "      <descrição>dez histórias sobre a existência</descrição>\n"
//				+ "    </livro>\n" + "    <musica codigo=\"1590\">\n" + "      <nome>Meu Passeio</nome>\n"
//				+ "      <preco>100.0</preco>\n" + "      <descrição>música livre</descrição>\n" + "    </musica>\n"
//				+ "  </produtos>\n" + "</compra>";
		return xmlDeOrigem;

	}

}
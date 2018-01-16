package alura.aulas.xstream.marcus;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class PrecoConverter implements SingleValueConverter {

	// Single Value Converter
	private NumberFormat getFormatter() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "br"));
	}
	
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	public String toString(Object value) {
		return getFormatter().format((Double) value);
	}

	public Object fromString(String value) {
		
		try {
			return Double.parseDouble(getFormatter().parse(value).toString());
		} catch (ParseException ex) {
			throw new ConversionException("Erro ao convertar Preco.\n " + ex);
		}
		
	}

	
	
	/* Stardard Converter
	private NumberFormat getFormatter() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "br"));
	}
	
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {
		double num = (Double) obj;
		writer.setValue(getFormatter().format(num));
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		
		try {
			return Double.parseDouble(getFormatter().parse(reader.getValue()).toString());
		} catch (ParseException ex) {
			throw new ConversionException("Erro ao convertar Preco.\n " + ex);
		}
		
	}
	*/

}

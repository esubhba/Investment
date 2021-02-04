/**
 * 
 */
package za1975.equity.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author SUBHADEEP
 *
 */
public class MoneySerializer extends JsonSerializer<BigDecimal> {

	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		
		gen.writeString(value.setScale(2, RoundingMode.FLOOR).toString());

	}

}

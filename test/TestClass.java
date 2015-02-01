
import com.spfi.ims.helper.StringHelper;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;

public class TestClass {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println("095 / 2013::0".split("::", -10)[1]);
//        System.out.println(new BigDecimal("60").setScale(2).toString().split("[.]")[1]);
//        HashMap map = new HashMap();
//        map.put("UNDRE", 1);
//        map.put("UPROR", 2);
//        map.put("STAW", 3);
//        map.put("FLAINT", 4);

//        map.keySet().;
//        String prsNumber = "70460004315";
//        System.out.println(prsNumber.substring(4, prsNumber.length()));
        String data = URLDecoder.decode("29%2F01%2F2015%3As%3A29%2F01%2F2015%3As%3ARemarks%3As%3A7050%3As%3A30123%3As%3ACARTON%20PRINTING%2004%20ATLANTIK%20SCO%20603%20%3As%3A0.00%3As%3APCS%3As%3A23.00%3As%3A%3Ase%3A29%2F01%2F2015%3As%3A29%2F01%2F2015%3As%3ARemarks%3As%3A7050%3As%3A30124%3As%3ACARTON%20PRINTING%2004%20PEDRO%20SCB%20603%3As%3A0.00%3As%3APCS%3As%3A50.00%3As%3A%3Ase%3A29%2F01%2F2015%3As%3A29%2F01%2F2015%3As%3ARemarks%3As%3A7050%3As%3A61870%3As%3AFIRE%20WOOD%20(%20KAYU%20BAKAR%20)%3As%3A0.00%3As%3ABUNDLES%3As%3A100.00%3As%3A%3Ase%3A", "utf-8");
        System.out.println(data);
        String[] separator = StringHelper.getDataSeparator(data, 2);
        System.out.println(separator[1]);
        data = data.replaceAll(":s:", separator[0]).replaceAll(":se:", separator[1]);
        System.out.println(data);
    }

}

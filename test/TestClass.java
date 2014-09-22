
import java.math.BigDecimal;

public class TestClass {
    
    public static void main(String[] args) {
//        System.out.println("095 / 2013::0".split("::", -10)[1]);
        System.out.println(new BigDecimal("60").setScale(2).toString().split("[.]")[1]);
    }
    
}

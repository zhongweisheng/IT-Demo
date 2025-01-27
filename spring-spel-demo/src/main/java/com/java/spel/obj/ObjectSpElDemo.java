package com.java.spel.obj;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Dylan
 * @version 1.0.0
 * @since 2021-07-14
 */
public class ObjectSpElDemo {
    public static void main(String[] args) {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        System.out.println(   c.getTime() .toString() );
        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("name"); // Parse name as an expression
        String name = (String) exp.getValue(tesla);
        // name == "Nikola Tesla"

        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        // result == true
        System.out.println(result);

        String mm = "419";
        if(mm.contains("19")){
            System.out.println( mm +" contains "+" 19  " +true);
        } else {
            System.out.println( mm +" contains "+" 19 " +false );
        }


    }

    public List<String> list ;
}

package com.java.spel.test;

import lombok.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpELTest {
    public static void main(String[] args) {


        ExpressionParser ep = new SpelExpressionParser();
//创建上下文变量
        EvaluationContext ctx = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为name，内容为Hello
        ctx.setVariable("name", "Hello");
        System.out.println(ep.parseExpression("#name").getValue(ctx));//输出：Hello


        ExpressionParser parser = new SpelExpressionParser();
// Integer列表
        List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue();
        System.out.println("list: " + numbers);

// evaluates to a Java map containing the two entries
        Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue();

        Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue();

        System.out.println( inventorInfo);
        System.out.println( mapOfMaps);


// List的元素为List
        List<List> listlOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue();
        System.out.println("List<List> : " + listlOfLists);
        Society society = new Society();

        List<Inventor> members = new ArrayList<Inventor>();
        members.add(new Inventor("Nikola Tesla","China"));
        members.add(new Inventor("Nikola 8888","US"));
        society.setMembers(members);



        StandardEvaluationContext societyContext = new StandardEvaluationContext(society);

        parser.parseExpression("name").setValue(societyContext, "IEEE");

        societyContext.setVariable("queryName", "Nikola Tesla");

       String  expression = "isMember(#queryName)? #queryName + ' is a member of the ' " +
                "+ Name + ' Society' : #queryName + ' is not a member of the ' + Name + ' Society'";

        String queryResultString = parser.parseExpression(expression)
                .getValue(societyContext, String.class);
// queryResultString = "Nikola Tesla is a member of the IEEE Society"
        System.out.println( queryResultString);


        String valueName = parser.parseExpression("name").getValue(societyContext,String.class);
        System.out.println(valueName );

        Boolean isMember = parser.parseExpression(" isMember(#queryName) " ).getValue(societyContext,Boolean.class);
        System.out.println(isMember );

        System.out.println( societyContext);

        /**
         * 所有的 queryNameList 都在  societyContext 中 ；
         * society 中有两个 名称 Nikola Tesla Nikola 8888;
         */
        List<String> queryNameList = new ArrayList<>( );
        queryNameList.add("Nikola Tesla");
        queryNameList.add("Nikola 8888");
        queryNameList.add("Nikola 999");

        societyContext.setVariable("queryNameList", queryNameList );

        Boolean allIn = parser.parseExpression(" allIn(#queryNameList) " ).getValue(societyContext,Boolean.class);
        System.out.println(allIn );

        Inventor inventor = new Inventor("Nikola Tesla","China");
        Boolean equalInvertor = parser.parseExpression(" ( name == 'Nikola Tesla' )   and  ( nationality == 'China' )    " ).getValue(inventor,Boolean.class);
        System.out.println(equalInvertor );

        Inventor inventor2 = new Inventor("Nikola Tesla","China");
        Boolean equalInvertor2 = parser.parseExpression(" ( name == \"Nikola Tesla\"  ) || (  nationality == \"China\" )   " ).getValue(inventor2,Boolean.class);
        System.out.println(equalInvertor2 );




    }
}

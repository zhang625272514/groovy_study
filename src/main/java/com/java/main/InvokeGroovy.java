package com.java.main;

import com.java.inter.IFoo;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.net.URL;

/**
 * Created by cheng on 2015/9/21.
 */
public class InvokeGroovy {

    public static void testText(){
        String text = "package com.groovy.impl\n" +
                "\n" +
                "import com.java.inter.IFoo\n" +
                "\n" +
                "/**\n" +
                " * Created by cheng on 2015/9/21.\n" +
                " */\n" +
                "class Foo implements  IFoo{\n" +
                "\n" +
                "    def x\n" +
                "\n" +
                "    @Override\n" +
                "    Object run(Object foo, Object bar) {\n" +
                "        println 'Hello World!'\n" +
                "        x = 123\n" +
                "        println foo * 10\n" +
                "        println bar\n" +
                "        FooO fooO = new FooO();\n" +
                "        println fooO.add(x, foo * 10)\n" +
                "        return 'success'\n" +
                "    }\n" +
                "}\n";

        try {
            ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
            GroovyClassLoader groovyCl = new GroovyClassLoader(cl);

            @SuppressWarnings("rawtypes")
            Class groovyClass = groovyCl.parseClass(text);

            // 接口方式调用
            IFoo foo = (IFoo) groovyClass.newInstance();
            System.out.println(foo.run(new Integer(2), "More parameter..."));

            System.out.println("=============================");

            // 代理方式调用
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            System.out.println(groovyObject.invokeMethod("run", new Object[]{new Integer(2), "More parameter..."}));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void testFile(){
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            URL url = InvokeGroovy.class.getResource("/com/" +
                    "groovy/impl/Foo.groovy");
            File f = new File(url.toURI());

//            File f = new File("E:\\project\\groovy\\java_groovy\\src\\main\\java\\com\\groovy\\impl\\Foo.groovy");
           /* File fooOF = new File(InvokeGroovy.class.getResource("/com/" +
                    "groovy/impl/FooO.groovy").toURI());

            groovyCl.parseClass(fooOF);*/

            @SuppressWarnings("rawtypes")
            Class groovyClass = groovyCl.parseClass(f);


            IFoo foo = (IFoo) groovyClass.newInstance();

            System.out.println(foo.run(new Integer(2), "More parameter..."));

            System.out.println("=============================");

            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            System.out.println(groovyObject.invokeMethod("run", new Object[]{new Integer(2),"More parameter..."}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        testText();
//        testFile();
    }
}

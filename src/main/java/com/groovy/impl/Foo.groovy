package com.groovy.impl

import com.java.inter.IFoo

/**
 * Created by cheng on 2015/9/21.
 */
class Foo implements  IFoo{

    def x

    @Override
    Object run(Object foo, Object bar) {
        println 'Hello World!'
        x = 123
        println foo * 10
        println bar
        FooO fooO = new FooO();
        println fooO.add(x, foo * 10)
        return 'success'
    }
}

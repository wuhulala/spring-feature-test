package com.wuhulala.spring.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/3/20<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class MockCalculatorTest {

//    @InjectMocks --- injects mock or spy fields into tested object automatically.
//
//            这个注解不会把一个类变成mock或是spy，但是会把当前对象下面的Mock/Spy类注入进去,按类型注入。
//
//    @Mock 生成的类，所有方法都不是真实的方法，而且返回值都是NULL。---> when(dao.getOrder()).thenReturn("returened by mock ");
//
//    @Spy ---Creates a spy of the real object. The spy calls real methods unless they are stubbed.
//
//            生成的类，所有方法都是真实方法，返回值都是和真实方法一样的。---> doReturn("twotwo").when(ps).getPriceTwo();
//
//    Mockito可以完成对一般对象方法的模拟，但是对于静态函数、构造函数、私有函数等还是无能为力.

    //@Mock
    private Calculator calculator = new Calculator();

    @Before
    public void init(){
        //MockitoAnnotations.initMocks(this);
        calculator = Mockito.spy(calculator);
        Mockito.when(calculator.add(1, 1)).thenReturn(4);
    }

    /**
     * 普通测试
     */
    @Test
    public void add() {
        Assert.assertEquals(3, calculator.add(1, 2));
        //Assert.assertEquals(-3, calculator.add(-1, -2));
    }

    /**
     * mock特殊方法测试
     */
    @Test
    public void addMockSpy() {
        Assert.assertEquals(4, calculator.add(1, 1));
        //Assert.assertEquals(-3, calculator.add(-1, -2));
    }

    /**
     * 异常测试
     */
    @Test(expected = CalculateException.class)
    public void testAddOutOfBound() {
        System.out.println(calculator);
        Assert.assertEquals(-2, calculator.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    /**
     * 判断1ms可以运算到多少
     */
    @Test(timeout = 1)
    public void testAddPerformance() {
        for (int i = 0; i < 100; i++) {
            calculator.add(i, i + 1);
        }
    }

    /**
     * 展示mock和spy的区别
     */
    @Test
    public void test() {

        //test for Mock
        List mockList = Mockito.mock(List.class);
        mockList.add("1");
        System.out.println(mockList.get(0));//返回null，说明并没有调用真正的方法
        Mockito.when(mockList.size()).thenReturn(100);//stub
        System.out.println(mockList.size());//size() method was stubbed，返回100

        //test for Spy
        List list = new LinkedList();
        List spy = Mockito.spy(list);

        //optionally, you can stub out some methods:
        Mockito.when(spy.size()).thenReturn(100);

        //using the spy calls real methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());
    }
}
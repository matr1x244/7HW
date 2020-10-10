public class StartTest {

    @AfterSuite ()
    public void afterTest(){
        System.out.println("After_test");
    }

    @Test (priority = 10)
    public void test1(){

        System.out.println("Test 1");
    }

    @Test(priority = 1)
    public void test2(){

        System.out.println("Test 2");
    }

    @Test(priority = 5)
    public void test5(){

        System.out.println("Test 5");
    }

    @Test(priority = 9)
    public void test3(){

        System.out.println("Test 3");
    }

    @Test (priority = 2)
    @AfterSuite
    public void test4(){

        System.out.println("Test 4");
    }

    @BeforeSuite ()
    public void beforeTest(){

        System.out.println("Before_test");
    }
    
}
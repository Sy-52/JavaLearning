public class StopAndSeeThread {
    public static void main(String[] args) {
        m1();
    }

    private static void m1(){m2();}

    private static void m2(){int a = 333;m3();}

    private static void m3(){m4();}

    private static void m4(){int a = 666;m5();}

    private static void m5(){m6();}

    private static void m6(){
        System.out.println("断点停住。");
    }
}

package proxy.jdkproxy;

/**
 * @author tanggewei@eglsgame.com
 * @date 2019/12/31 16:11
 */
public class Target implements TargetInterface {
    
    @Override
    public void method1() {
        System.out.println("method1 running ....");
    }
    
    @Override
    public String method2() {
        System.out.println("method2 running ....");
        return "method2";
    }
    
    @Override
    public int method3(int x) {
        System.out.println("method3 running ....");
        return ++x;
    }
}

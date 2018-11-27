/*
 * @author F3ver1
 * @date 2018/11/27 22:51
 */
interface Subject{
    public void make();
}
class RealSubject implements Subject{
    public void make(){
        System.out.println("核心工作开始了");
    }
}
class ProxySubject implements Subject{
    private Subject subject;
    public ProxySubject(Subject subject){
        this.subject = subject;
    }
    public void prepare(){
        System.out.println("核心操作之前的操作");
    }
    public void destroy(){
        System.out.println("核心操作之后的收尾操作");
    }
    public void make(){
        this.prepare();
        this.subject.make();
        this.destroy();
    }
}
public class DesignPattern {
    public static void main(String[] args) {
    Subject subject = new ProxySubject(new RealSubject());
    subject.make();
    }
}

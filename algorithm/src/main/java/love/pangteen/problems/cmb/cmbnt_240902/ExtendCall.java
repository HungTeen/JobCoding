package love.pangteen.problems.cmb.cmbnt_240902;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/2 20:45
 **/
public class ExtendCall {

    public static void main(String[] args) {
        Caller caller = new Caller();
        A b = new B();
        caller.call(b);
    }

    public static class Caller {

        public void call(A a){
            System.out.println("call(A)");
            a.f();
        }

        public void call(B b){
            System.out.println("call(B)");
            b.f();
        }
    }

    public static class A {

        public void f(){
            System.out.println("A.f()");
        }
    }

    public static class B extends A {

        @Override
        public void f() {
            System.out.println("B.f()");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Test.getTest());
        Test2.printHelloWorld();
        Test2.HelloWorld.printHelloWorld();
        Test2.FooBar.printFromXToY(0, 10);
        System.out.println(Test2.FooBar.Inner.getAnswer());
    }
}

public class Greeter {
    public static void main(String[] args) {
        System.out.println("Welcome to Java greeter");
        for(String arg : args){
            System.out.print(arg + " ");
        }
    }
}

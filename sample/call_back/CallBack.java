package call_back;

interface Greetable { void greet(); }

public class CallBack {
    public static void main(String[] args) {
        CatGreeting catGreeting = new CatGreeting();
        DuckGreet duckGreet = new DuckGreet();
        PigGreeting pigGreeting = new PigGreeting();
        var test = new CallBack();
        test.sayGreeting(catGreeting); // callback
        test.sayGreeting(pigGreeting);
        test.sayGreeting(duckGreet);
    }

    public void sayGreeting(Greetable greetable) {
        greetable.greet();
    }
}

class CatGreeting implements Greetable {
    @Override
    public void greet() {
        System.out.println("Cat greet");
    }
}

class PigGreeting implements Greetable {
    @Override
    public void greet() {
        System.out.println("Pig greet");
    }
}

class DuckGreet implements Greetable {
    @Override
    public void greet() {
        System.out.println("Duck greet");
    }
}

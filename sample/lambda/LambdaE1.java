package lambda;

public class LambdaE1 {
    public static void main(String[] args) {
        ButtonClass button = new ButtonClass();
        button.pressEvent(() -> System.out.println("press"));
    }
}

class ButtonClass {
    public void pressEvent(PressHandler handler) {
        handler.pressEvenHandler();
    }
}

interface PressHandler { void pressEvenHandler(); }

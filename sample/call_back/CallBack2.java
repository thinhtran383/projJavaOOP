package call_back;

interface EvenHandler { void clickEvenHandler(); }

public class CallBack2 {
    public static void main(String[] args) {
        Button myButton = new Button();
        Handler myHandler = new Handler();
        myButton.onClick(myHandler);
        new Button().onClick(() -> System.out.println("Mot nut khac dang duoc nhan"));
        new Button().onClick(() -> System.out.println("Thinh tran"));
    }
}

class Button {
    public void onClick(EvenHandler handler) {
        handler.clickEvenHandler();
    }
}

class Handler implements EvenHandler {
    @Override
    public void clickEvenHandler() {
        System.out.println("Nut ban dang dc click");
    }
}

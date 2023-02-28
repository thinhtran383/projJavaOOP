package interface_;

public interface inter1 {
    public static final int x = 120; // mac dinh co the bo public static final

    public abstract void doSomeThing(); // mac dinh co the bo public abtract

    default void doSomeThing2() {
        System.out.println("doSomeThing2");
    }

    default void doSomeThing3() { // phan than hoan chinh khi co default, co the duoc override lai
        System.out.println("doSomeThing3");
    }
}

interface inter2 extends inter1 {
    @Override // override lai cha
    default void doSomeThing3() {
        System.out.println("Override doSomeThing3");
    }

    void doSomeThing2(); // hoac tro thanh abstract
}

class Test implements inter1, inter2 { // override toan bo hoac la tro thanh abtract class
    @Override
    public void doSomeThing() {
        System.out.println("out");
    }

    @Override
    public void doSomeThing2() {}
}

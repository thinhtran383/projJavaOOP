package generic;

public class Grandfather {}

class Father extends Grandfather {}

class Child extends Father {}

class Grandchild extends Child {
    private String name;

    public Grandchild(String name) {
        this.name = name;
    }

    public Grandchild() {}
}
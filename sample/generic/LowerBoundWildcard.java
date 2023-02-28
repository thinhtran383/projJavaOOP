package generic;

import java.util.ArrayList;

public class LowerBoundWildcard {
    public static void addNewObject(ArrayList<? super Grandchild> list) {
        list.add(new Grandchild("Lan"));
        list.add(new Grandchild("Ha"));
        list.add(new Grandchild("Huy"));
        list.add(new Grandchild("Hoa"));
    }

    public static void main(String[] args) {
        ArrayList<Father> fathers = new ArrayList<>();
        ArrayList<Child> children = new ArrayList<>();
        ArrayList<Grandchild> grandchildren = new ArrayList<>();
        addNewObject(fathers);
        addNewObject(children);
        addNewObject(grandchildren);
    }
}

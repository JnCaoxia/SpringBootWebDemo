import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Z extends X {

    Y y = new Y();

    Z() {
        //super();
        System.out.print("Z");
    }

    public static void main(String[] args) {
        new Z();
    }
}
class X {
    Y b = new Y();
    X() {
        System.out.print("X");
    }
}

class Y {

    Y() {
        System.out.print("Y");
    }
}

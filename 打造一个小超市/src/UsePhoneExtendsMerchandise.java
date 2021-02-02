import supermarket.*;

public class UsePhoneExtendsMerchandise {
    public static void main(String[] args) {
        Phone phone = new Phone(
                "手机001", 1, 100, 1999, 999,
                4.5, 3.5, 4, 128, "索尼", "安卓");
        System.out.println(phone.getMerchandiseName());

        phone.buy(100);

        phone.buy(3);

        //super
        Phone phone1 = new Phone();
        phone1.describe();
    }
}

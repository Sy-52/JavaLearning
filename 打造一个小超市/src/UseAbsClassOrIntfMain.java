import supermarket.*;

import java.util.Date;

import static supermarket.Category.*;

public class UseAbsClassOrIntfMain {
    public static void main(String[] args) {

        Date productDate = new Date();
        Date expireDate = new Date(productDate.getTime() + 365L * 24 * 3600 * 1000);

        GamePointCard gamePointCard = new GamePointCard(
                "游戏点卡", 1, 100, 45, 5, ELECTRIC, productDate, expireDate);
        // >> TODO gamePointCard有4种类型：Merchandise、Object、ExpireDateMerchandise、VirtualMerchandise.
        ExpireDateMerchandise am = gamePointCard;

        System.out.println(am.notExpireInDays(100));
        System.out.println(am.leftDatePercentage());
        System.out.println(am.actualValueNow(am.leftDatePercentage()));

    }
}

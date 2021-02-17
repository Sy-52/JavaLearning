import supermarket.ExpireDateMerchandise;
import supermarket.GamePointCard;
import supermarket.Merchandise;

import java.util.Date;

import static supermarket.Category.*;

public class UseInterface {
    public static void main(String[] args) {

        Date productDate = new Date();
        Date expireDate = new Date(productDate.getTime() + 365L * 24 * 3600 * 1000);
        GamePointCard gamePointCard = new GamePointCard(
                "游戏点卡", 1, 100, 45, 5, ELECTRIC, productDate, expireDate);
        // >> TODO gamePointCard有4种类型：Merchandise、Object、ExpireDateMerchandise、VirtualMerchandise.
        ExpireDateMerchandise expireDateMerchandise = gamePointCard;

        Merchandise m = gamePointCard;
    }
}

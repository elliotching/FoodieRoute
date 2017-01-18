package fcsit.foodieroute;

/**
 * Created by Elliot on 26-Aug-16.
 */
public class Food {
    private int id;
    private String mFoodName;
    private String mFoodSellerShop;




    public Food() {
    }

    public Food(String name, String shopName) {
        super();
        mFoodName = name;
        mFoodSellerShop = shopName;
    }

    public int getId() {
        return id;
    }

    public String getFoodName() {
        return mFoodName;
    }

    public String getFoodShop() {
        return mFoodSellerShop;
    }

    public void setId(int n) {
        id = n;
    }

    public void setFoodName(String name) {
        mFoodName = name;
    }

    public void setFoodShop(String foodShop) {
        mFoodSellerShop = foodShop;
    }




    public String toString() {
        return "Book [id=" + id + ", Food=" + mFoodName + ", Shop=" + mFoodSellerShop + "]";
    }
}

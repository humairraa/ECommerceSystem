// Humaira Adeeb
// 501030823
public class Shoes extends Product
{
    // initizalizing
    int black6_stockCount;
    int brown6_stockCount;
    int black7_stockCount;
    int brown7_stockCount;
    int black8_stockCount;
    int brown8_stockCount;
    int black9_stockCount;
    int brown9_stockCount;
    int black10_stockCount;
    int brown10_stockCount;

    // shoe constructor
    public Shoes(String name,String id, double price, int black6_stockCount, int brown6_stockCount, int black7_stockCount, int brown7_stockCount, int black8_stockCount,
                 int brown8_stockCount, int black9_stockCount, int brown9_stockCount, int black10_stockCount, int brown10_stockCount,int stats)
    {
        // Made use of the constructor in the super class shoes. Initializes additional Shoes instance variables.
        // Set the category to CLOTHING
        super(name, id, price, 100000, Product.Category.CLOTHING,stats);
        this.black6_stockCount = black6_stockCount;
        this.brown6_stockCount = brown6_stockCount;
        this.black7_stockCount = black7_stockCount;
        this.brown7_stockCount = brown7_stockCount;
        this.black8_stockCount = black8_stockCount;
        this.brown8_stockCount = brown8_stockCount;
        this.black9_stockCount = black9_stockCount;
        this.brown9_stockCount = brown9_stockCount;
        this.black10_stockCount = black10_stockCount;
        this.brown10_stockCount = brown10_stockCount;
    }

    // switch case returns the value  truse of false, depending on if the option is valid or not
    public boolean validOptions(String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black":
            case "8 Black":
            case "10 Black":
            case "8 Brown":
            case "10 Brown":
            case "7 Brown":
            case "7 Black":
            case "9 Black":
            case "6 Brown":
            case "9 Brown":
                return true;
            default: return false;
        }
    }

    @Override
    // switch case returns the value of stock count
    public int getStockCount(String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": return black6_stockCount;
            case "6 Brown": return brown6_stockCount;
            case "7 Black": return black7_stockCount;
            case "7 Brown": return brown7_stockCount;
            case "8 Black": return black8_stockCount;
            case "8 Brown": return brown8_stockCount;
            case "9 Black": return black9_stockCount;
            case "9 Brown": return brown9_stockCount;
            case "10 Black": return black10_stockCount;
            case "10 Brown": return brown10_stockCount;
            default: return super.getStockCount(productOptions);
        }
    }

    @Override
    //sets the stock count of each shoe colour and size.
    public void setStockCount(int stockCount, String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": this.black6_stockCount = stockCount; break;
            case "6 Brown": this.brown6_stockCount = stockCount; break;
            case "7 Black": this.black7_stockCount = stockCount; break;
            case "7 Brown": this.brown7_stockCount = stockCount; break;
            case "8 Black": this.black8_stockCount = stockCount; break;
            case "8 Brown": this.brown8_stockCount = stockCount; break;
            case "9 Black": this.black9_stockCount = stockCount; break;
            case "9 Brown": this.brown9_stockCount = stockCount; break;
            case "10 Black": this.black10_stockCount = stockCount; break;
            case "10 Brown": this.brown10_stockCount = stockCount; break;
        }
    }

    // Uses the productOptions to check the number of stock of all shoes
    //When user orders the shoe, the stockcount is reduced here
    @Override
    public void reduceStockCount(String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": black6_stockCount--; break;
            case "6 Brown": brown6_stockCount--; break;
            case "7 Black": black7_stockCount--; break;
            case "7 Brown": brown7_stockCount--; break;
            case "8 Black": black8_stockCount--; break;
            case "8 Brown": brown8_stockCount--; break;
            case "9 Black": black9_stockCount--; break;
            case "9 Brown": brown9_stockCount--; break;
            case "10 Black": black10_stockCount--; break;
            case "10 Brown": brown10_stockCount--; break;

        }
    }

    @Override
    // Prints shoe information in super class
    public void print()
    {
        super.print();
    }

}
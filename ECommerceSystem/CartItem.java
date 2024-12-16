// Humaira Adeeb
// 501030823
public class CartItem extends Product
{
    private Product product;
    private String productOptions;

    CartItem (Product product, String productOptions) // constructor calls product and product options in the constructor
    {
        this.product = product;
        this.productOptions = productOptions;
    }

    public Product getProduct()
    {
        return product;
    } // gets the product

    public String getProductOptions()
    {
        return productOptions;
    } // gets the product options

    public void print()
    {
        System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", product.getId(), product.getCategory(), product.getName(), product.getPrice());
    }

    public boolean equals (CartItem other)
    {
        return this.product.getId().equals(other.getProduct().getId()) && this.productOptions.equals(other.getProductOptions());
    }
}

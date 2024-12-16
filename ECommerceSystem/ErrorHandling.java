// Humaira Adeeb
// 501030823

// These are the exceptions being thrown in ECommerceSystem for part 3B
public class ErrorHandling
{
class CustomerNotFound extends RuntimeException
{
    public CustomerNotFound()
    {
        super("Customer found.");
    }
    public CustomerNotFound(String error)
    {
    super(error);
    }
}
class CustomerNameNotFound extends RuntimeException
{
    public CustomerNameNotFound()
    {
        super ("Customer not found.");
    }
}
class CustomerAddressNotFound extends RuntimeException
{
    public CustomerAddressNotFound()
    {
        super ("Customer address not found.");
    }
}
class ProductNotFound extends RuntimeException
{
    public ProductNotFound()
    {
        super("Product not found.");
    }
    public ProductNotFound(String error)
    {
    super(error);
    }
}
class ProductOptionsNotFound extends RuntimeException
{
    public ProductOptionsNotFound()
    {
        super("Invalid product options.");
    }
    public ProductOptionsNotFound(String error)
    {
    super(error);
    }
}
class ProductOutOfStock extends RuntimeException
{
    public ProductOutOfStock()
    {
        super ("Product out of stock.");
    }
}
class OrderNotFound extends RuntimeException
{
    public OrderNotFound()
    {
        super ("Order # not found.");
    }
}
}
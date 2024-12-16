// Humaira Adeeb
// 501030823
import java.util.ArrayList;
import java.util.HashMap;

// carries the cart methods to add, remove & print.
public class Cart
{
    private ArrayList <CartItem> cart;
    private HashMap <String, Integer> cartItem;
    Cart (String customerId)
    {
        this.cart = new ArrayList<CartItem>();
        this.cartItem = new HashMap<>();
    }
    // created arraylist and a hashmap for cart
    public ArrayList<CartItem> getCart()
    {
        return cart;
    }
    public HashMap <String, Integer> getCartItem()
    {
        return cartItem;
    }
    private boolean cartItemCurrent (CartItem item)
    {
        boolean cic = false;
        for (CartItem itemNext: cart)
        {
            if (item.equals(itemNext))
            {
                cic = true;
            }
        }
        return cic;
    }
    public void cartItemAdd (CartItem item) // adds the order items to the cart
    {
        String id = item.getProduct().getId();
        if (cartItemCurrent(item))
        {
            cartItem.put(id, cartItem.get(id) + 1);
        }
        else
        {
            cart.add(item);
            cartItem.put(id, 1);
        }
    }
    public void cartItemRemove (CartItem item) // removes the order items from the cart
    {
        String id = item.getProduct().getId();
        if (cartItem.get(id) > 1)
        {
            cartItem.put(id, cartItem.get(id) - 1);
        }
        else
        {
            for (CartItem IN: cart)
            {
                if (item.equals(IN))
                {
                    item = IN;
                }
            }
            cart.remove(item);
            cartItem.remove(id);
        }
    }
    public void cartPrint() // prints the cart
    {
        if (cart.isEmpty())
        {
            System.out.println("Empty cart...");
        }
        else
        {
            for (CartItem i : cart)
            {
                int quantity = cartItem.get(i.getProduct().getId());
                System.out.print("There are " + quantity + " item(s) in the cart.");
                i.print();
                System.out.println();
            }
        }
    }
    public boolean cartCheck(CartItem item) // checks if there is anything in the cart then takes it out
    {
        boolean cc = false;
        for (CartItem itemNext : cart)
        {
            if (item.equals(itemNext))
            {
                cc = true;
            }
        }
        return cc;
    }

}
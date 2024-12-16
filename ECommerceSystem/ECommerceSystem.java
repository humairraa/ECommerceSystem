// Humaira Adeeb
// 501030823
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem extends ErrorHandling {
    private LinkedHashMap<String, Product> products = new LinkedHashMap<>();
    private ArrayList<Product>productsArrayList = new ArrayList<>(products.values());
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<ProductOrder> orders = new ArrayList<>();
    private ArrayList<ProductOrder> shippedOrders = new ArrayList<>();
    private Map<Product, Integer> statsMap = new LinkedHashMap<>();

    // generates order numbers, customer id's, product id's
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    String error;

    //generates number
    Random random = new Random();

    public ECommerceSystem() {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products
        // Create some products. Notice how generateProductId() method is used
        String productid = generateProductId();
        products.put(productid, new Shoes("Nike Air Jordan's Retro", productid, 170.0, 40, 35, 25, 92, 36, 55, 20, 36, 28, 92, 0));
        productid = generateProductId();
        products.put(productid, new Shoes("Black Timberlands", productid, 450.0, 46, 52, 93, 26, 77, 22, 88, 99, 21, 12, 0));
        productid = generateProductId();
        products.put(productid, new Shoes("Nike Air forces", productid, 120.0, 47, 75, 37, 29, 36, 65, 76, 28, 26, 11, 0));
        productid = generateProductId();
        products.put(productid, new Shoes("Adidas Superstars", productid, 90.0, 65, 11, 37, 75, 39, 50, 60, 43, 52, 73, 0));

        // for exceptions
        try {
            productsFileReader();
        } catch (IOException e) {
            e.printStackTrace(); System.exit(1);
        }


//    	Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    // gets prods from the product.txt file
    private HashMap<String, Product> productsFileReader() throws FileNotFoundException
    {
        FileReader productsReader = new FileReader("products.txt");
        Scanner in = new Scanner(productsReader);
        while (in.hasNextLine())
        {
            String line = in.nextLine();
            if (line.equals("BOOKS"))
            {
                String name = in.nextLine();
                double price = Double.parseDouble(in.nextLine());
                String stock = in.nextLine();
                Scanner one_1 = new Scanner(stock);
                one_1.useDelimiter(" ");
                int hardcoverStock = Integer.parseInt(one_1.next());
                int paperbackStock = Integer.parseInt(one_1.next());
                String BookDeets = in.nextLine();
                Scanner two_2 = new Scanner(BookDeets);
                two_2.useDelimiter(":");
                String title = two_2.next();
                String authorName = two_2.next();
                int year = Integer.parseInt(two_2.next());
                String productId = generateProductId();
                products.put(productId, new Book(name, productId, price, paperbackStock, hardcoverStock, title, authorName, year, 0));
            }
            else if (line.equals("CLOTHING"))
            {
                String name = in.nextLine();
                Double price = Double.parseDouble(in.nextLine());
                int stock = Integer.parseInt(in.nextLine());
                String productId = generateProductId();
                products.put(productId, new Product(name, productId, price, stock, Product.Category.CLOTHING, 0));
            }
            else if (line.equals("COMPUTERS"))
            {
                String name = in.nextLine();
                Double price = Double.parseDouble(in.nextLine());
                int stock = Integer.parseInt(in.nextLine());
                String productId = generateProductId();
                products.put(productId, new Product(name, productId, price, stock, Product.Category.COMPUTERS, 0));
            }
            else if (line.equals("FURNITURE"))
            {
                String name = in.nextLine();
                Double price = Double.parseDouble(in.nextLine());
                int stock = Integer.parseInt(in.nextLine());
                String productId = generateProductId();
                products.put(productId, new Product(name, productId, price, stock, Product.Category.FURNITURE, 0));
            }
            else if (line.equals("GENERAL"))
            {
                String name = in.nextLine();
                Double price = Double.parseDouble(in.nextLine());
                int stock = Integer.parseInt(in.nextLine());
                String productId = generateProductId();
                products.put(productId, new Product(name, productId, price, stock, Product.Category.GENERAL, 0));
            }
        }
        return products;
    }

    private String generateOrderNumber() {
        return "" + orderNumber++;
    }
    private String generateCustomerId() {
        return "" + customerId++;
    }
    private String generateProductId() {
        return "" + productId++;
    }
    public String getErrorMessage() {
        return errMsg;
    }
    public String getErr(){
        return error;
    }

    public void printAllProducts()
    {
        for (Map.Entry<String, Product> entry : products.entrySet())
        {
            entry.getValue().print();
        }
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
        for (Map.Entry<String, Product> entry : products.entrySet())
        {
            if (entry.getValue().getCategory().equals(Product.Category.BOOKS))
            {
                entry.getValue().print();
            }
        }
    }

    // Print all current orders
    public void printAllOrders()
    {
        for (ProductOrder o : orders)
            o.print();
    }

    // Print all shipped orders
    public void printAllShippedOrders()
    {
        for (ProductOrder po : shippedOrders)
            po.print();
    }

    // Print all customers
    public void printCustomers()
    {
        for (Customer c : customers)
        {
            c.print();
        }
    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId)
    {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        // ... code here
        Customer cust = null;
        for (Customer c : customers)
        {
            if (customerId.equals(c.getId()))
            {
                cust = c;
            }
            else if (cust == null)
            {
                throw new CustomerNameNotFound();
            }
        }
        if (cust == null)
        {
            return false;
        }
        // Print current orders of this customer
        System.out.println("Current Orders of Customer " + customerId);
        // enter code here
        for (ProductOrder o : orders)
        {
            if (o.getCustomer().equals(cust))
                o.print();
        }

        // Print shipped orders of this customer
        System.out.println("\nShipped Orders of Customer " + customerId);
        //enter code here
        for (ProductOrder s : shippedOrders)
        {
            if (s.getCustomer().equals(cust))
                s.print();
        }
        return true;
    }

    public void printAuthorBooks(String authorName)
    {
        ArrayList<Product> product = new ArrayList<Product>();
        for (Map.Entry<String, Product> entry : products.entrySet())
        {
            product.add(entry.getValue());
        }
        ArrayList<Book> Books = new ArrayList<Book>();
        Book books = null;
        for (Product b : product)
        {
            if (b.getCategory().equals(Product.Category.BOOKS)) // checking to find all the products of category BOOKS
            {
                books = (Book) b;
                if (books.getAuthor().equals(authorName))
                    Books.add(books);
            }
        }
        Comparator<Book> ByAuthorNAME = new Comparator<Book>()
        { // This is the code for sorting the year of the books in ascending order
            @Override
            public int compare(Book order1, Book order2)
            {
                if (order1.getYear() > order2.getYear())
                    return 1;
                else
                    return -1;
            }
        };
        Books.sort(ByAuthorNAME);
        for (Book b : Books)
            b.print();
    }


    public void AddtoCart(String productId, String customerId, String productOptions)
    { //adds to the cart
        Customer pcustomer = null;
        for (Customer cus: customers)
        {
            if (cus.getId().equals(customerId))
            {
                pcustomer = cus;
            }
        }
        if (pcustomer == null)
        {
            throw new CustomerNameNotFound();
        }
        Product preproduct = null;
        for (Map.Entry<String, Product> entry : products.entrySet()) { // iterating through the products list
            if (entry.getValue().getId().equals(productId)) {
                preproduct = entry.getValue();
            }
        }
        if (preproduct == null)
        {
            throw new ProductOptionsNotFound();
        }
        CartItem item = new CartItem(preproduct, productOptions);
        pcustomer.getCart().cartItemAdd(item);
    }

    public void RemCartItem(String customerId, String productId, String productOptions)
    {
        Customer pcustomer = null;
        for (Customer customer : customers)
        {
            if (customer.getId().equals(customerId))
            {
                pcustomer = customer;
            }
        }
        if (pcustomer == null)
        {
            throw new CustomerNameNotFound();
        }
        Product pproduct = null;
        for (Product product : productsArrayList)
        {
            if (product.getId().equals(productId))
            {
                pproduct = product;
            }
        }
        if (pproduct == null)
        {
            throw new ProductNotFound();
        }
        CartItem item = new CartItem(pproduct, productOptions);
        if (pcustomer.getCart().cartCheck(item))
        {
            pcustomer.getCart().cartItemRemove(item);
        }
        else if (!pcustomer.getCart().cartCheck(item))
        {
            throw new ProductNotFound();
        }
    }

    public void printCart(String customerId)
    { // prints the cart
        Customer pcustomer = null;
        for (Customer customer: customers)
        {
            if (customer.getId().equals(customerId))
            {
                pcustomer = customer;
            }
        }
        if (pcustomer == null)
        {
            throw new CustomerNameNotFound();
        }
        pcustomer.getCart().cartPrint();
    }

    public void orderItems(String customerId) { //orders the items in the cart
        {
            Customer pcustomer = null;
            for (Customer customer : customers) {
                if (customer.getId().equals(customerId)) {
                    pcustomer = customer;
                }
            }
            if (pcustomer == null)
            {
                throw new CustomerNameNotFound();
            }
            Cart cart = pcustomer.getCart();
            for (CartItem c : cart.getCart())
            {
                String id = c.getProduct().getId();
                int x = cart.getCartItem().get(id);
                for (int i = 0; i < x; ++i)
                {
                    orderProduct(c.getProduct().getId(), customerId, c.getProductOptions());
                }
            }
        }
    }

    public String orderProduct (String productId, String customerId, String productOptions)
    {
        // First check to see if customer object with customerId exists in array list customers
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Customer object
        Customer cust = null;
        for (Customer c : customers)
        { // iterating throughout the customers list
            if (c.getId().equals(customerId))
            {
                cust = c;
            }
        }
        if (cust == null)
        {
            throw new CustomerNameNotFound();
        }
        if (cust == null)
            return null;
        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        Product prod = null;
        for (Map.Entry<String, Product> entry : products.entrySet())
        { // iterating through the products list
            if (entry.getValue().getId().equals(productId))
            {
                prod = entry.getValue();
            }
        }
        if (prod == null)
        {
            throw new ProductNotFound();
        }
        if (prod == null)
            return null;
        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        // See class Product and class Book for the method validOptions()
        // If options are not valid, set errMsg string and return null;
        if (!productOptions.equals(""))
        {
            if (!prod.validOptions(productOptions))
            {
                if (prod.getCategory().equals(Product.Category.BOOKS))
                {
                    throw new ProductOptionsNotFound();
                }
                else if (prod.getCategory().equals(Product.Category.CLOTHING))
                {
                    throw new ProductOptionsNotFound();
                }
                else
                {
                    throw new ProductNotFound();
                }
            }
        }
        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null
        if (prod.getStockCount(productOptions) == 0)
        {
            throw new ProductOutOfStock();
        }
        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string
        ProductOrder order = new ProductOrder(generateOrderNumber(), prod, cust, productOptions);
        orders.add(order);
        prod.reduceStockCount(productOptions);
        for (ProductOrder o : orders)
        {
            if(orders.size() == 1){
                statsMap.put(prod, prod.getStats());
                prod.increaseStatsCount(prod.getId());
            }
            else if(prod.getId().equals(o.getProduct().getId())){
                prod.increaseStatsCount(prod.getId());
                break;
            }
            else{
                statsMap.put(prod, prod.getStats());
            }
        }
        return "Order #" + (orderNumber - 1);
    }

    /*
     * Create a new Customer object and add it to the list of customers
     */

    public boolean createCustomer(String name, String address)
    {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter
            if (name.equals("") || name == null)
            {
                throw new CustomerNameNotFound();
            } else if (address.equals("") || address == null)
            {
                throw new CustomerAddressNotFound();
            }
            else
            {
                Customer new_customer = new Customer(generateCustomerId());
                customers.add(new_customer);
                return true;
            }
    }

    public boolean shipOrder(String orderNumber)
    {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        ArrayList<ProductOrder> nshippedord = new ArrayList<ProductOrder>();
        ProductOrder ship_order = null;
        for (ProductOrder o : orders)
        {
            if (o.getOrderNumber().equals(orderNumber))
            {
                nshippedord.add(o);
                shippedOrders.add(o); // adding the ordered products into the shippedOrders list
                o.print();
                ship_order = o;
                break;
            }
            else
            {
//                throw new InvalidOrderNumberException("Order " + orderNumber + " not found");
                errMsg = "Order " + orderNumber + " Not Found";
                continue;
            }
        }
        orders.removeAll(nshippedord); // using the arraylist to remove shipped from orders.
        if (ship_order == null)
        {
            return false;
        }
        return true;
    }

    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber) { // cancels the order
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        ArrayList<ProductOrder> ncancelledord = new ArrayList<ProductOrder>();
        ProductOrder ship_Order = null;
        for (ProductOrder o : orders)
        {
            if (o.getOrderNumber().equals(orderNumber))
                {
                    ncancelledord.add(o);
                    ship_Order = o;
                }
            else
                {
                    errMsg = "Order " + orderNumber + " Not Found";
                    continue;
                }
        }

        orders.removeAll(ncancelledord);
        if (ship_Order == null)
            return false;
        return true;
    }

    // Sort price
    public void printByPrice()
    {
        List<Map.Entry<String, Product>> listt = new LinkedList<Map.Entry<String, Product>>(products.entrySet());
        Collections.sort(listt, new Comparator<Map.Entry<String, Product>>()
        {
            @Override
            public int compare(Map.Entry<String, Product> or1, Map.Entry<String, Product> or2)
            {
                if (or1.getValue().getPrice() > or2.getValue().getPrice())
                    return 1;
                else
                    return -1;
            }
        });
        Map<String, Product> sbp = new LinkedHashMap<String, Product>();
        for (Map.Entry<String, Product> entry : listt)
        {
            sbp.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Product> entry : sbp.entrySet())
        {
            entry.getValue().print();
        }
    }

    // Sort name
    public void printByName()
    {
        List<Map.Entry<String, Product>> list = new LinkedList<Map.Entry<String, Product>>(products.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Product>>()
        {
            @Override
            public int compare(Map.Entry<String, Product> or1, Map.Entry<String, Product> or2)
            {
                return or1.getValue().getName().compareTo(or2.getValue().getName());
            }
        });
        Map<String, Product> sbn = new LinkedHashMap<String, Product>();
        for (Map.Entry<String, Product> entry : list)
        {
            sbn.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Product> entry : sbn.entrySet())
        {
            entry.getValue().print();
        }
    }

    // Sort customer by name
    public void sortCustomersByName()
    {
        Comparator<Customer> bycustname = new Comparator<Customer>()
        {
            @Override
            public int compare(Customer or1, Customer or2) {
                return or1.getName().compareTo(or2.getName());
            }
        };
        Collections.sort(customers, bycustname);
    }

    public void printStats() // prints stats of products ordered
    {
        ArrayList<Product> list = new ArrayList<Product>(statsMap.keySet());
        Comparator<Product> comByPrice = new Comparator<Product>()
        {
            public int compare(Product prods1, Product prods2)
            {
                if(prods1.getStats()>prods2.getStats())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }
        };
        Collections.sort(list, comByPrice);
        for (Product pr : list){
            pr.statPrint();
        }
    }
}
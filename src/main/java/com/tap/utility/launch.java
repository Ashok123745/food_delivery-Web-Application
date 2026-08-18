package com.tap.utility;

//import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.food.DAOImpl.CartItemDAOimpl;
import com.food.model.CartItem;

//import com.tap.DAOImpl.OrderItemImpl;
//import com.tap.model.OrderItem;

//import com.tap.DAOImpl.OrderDAOImpl;
//import com.tap.DAOImpl.MenuDAOimpl;

//import com.tap.model.Menu;
//import com.tap.model.Order;

//import com.tap.DAOImpl.ResturantDAOimpl;
//import com.tap.DAOImpl.UserDAOimpl;
//import com.tap.model.Resturant;
//import com.tap.model.User;

public class launch {
	
	
	public static void main(String[] args) {
		
   Scanner	scan = new Scanner(System.in);
   
                    //ADD USER DETAILS//
//		User U=new User();
//		System.out.println("Enter the Username");
//		String username=scan.next();
//		System.out.println("Enter the Password");
//		String password=scan.next();
//		System.out.println("Enter the Email");
//		String Email=scan.next();
//		System.out.println("Enter the Address");
//		String Address=scan.next();
//		System.out.println("Enter the role");
//		String role=scan.next();
//      User u=new User(username, password, Email, Address, role, null, null);
//      UserDAOimpl udao = new UserDAOimpl();
//      udao.addUser(u );
//      System.out.println(u);
   
                           // Get USER DETAILS//
   
// System.out.println("Enter the User ID:");
// int userId=scan.nextInt();
// UserDAOimpl dao = new UserDAOimpl();
// User user= dao.getUser(userId);
// if(user !=null) {
//	   System.out.println("User Details");
//	   System.out.println(user);
// }else {
//	   System.out.println("Sorry! User Not Found.");
// }
   
                       ////UPDATE USER DETAILS//
   
// System.out.println("Enter the USER ID To Update:");
// int id=scan.nextInt();
// System.out.println("Enter the username");
// String username=scan.next();
// System.out.println("Enter the Password");
// String password=scan.next();
// System.out.println("Enter the Email");
// String email=scan.next();
// System.out.println("Enter the Address");
// String address = scan.next();
// scan.nextLine(); // consume the newline after scan.next()
// System.out.println("Enter the Role");
// String role = scan.nextLine();
// User user=new User(username, password, email, address, role, null, null);
// user.setUserId(id);
// UserDAOimpl dao=new UserDAOimpl();
// dao.updateUser(user);
   
                         ///DELETE USER//
   
//   System.out.println("Enter the UserID to Delete");
//   int id=scan.nextInt();
//  UserDAOimpl dao=new UserDAOimpl();
//  dao.deleteUser(id);
   
                      //GET ALL USER DETAILS//
// UserDAOimpl dao = new UserDAOimpl();
// List<User> userList = dao.getAllUsers();
// if (userList.isEmpty()) {
//     System.out.println("No Users Found.");
// } else {
//     System.out.println("===== All Users =====");
//     for (User user : userList) {
//         System.out.println(user);
//     }
// }
  
                     ///ADD RESTURANT DETAILS//
   
//          Resturant    Res = new  Resturant();
//          System.out.println("Enter the ResturantName");
//          String ResturantName=scan.next();
//          System.out.println("Enter the cuisine Type");
//          String cuisineType=scan.next();
//          System.out.println("Enter the deliveryTime");
//          int deliveryTime=scan.nextInt();
//          System.out.println("Enter the Address");
//          String Address=scan.next();
//          System.out.println("Enter the adminuserId");
//          int  adminuserId=scan.nextInt();
//          System.out.println("Enter the Rating");
//          Double Rating=scan.nextDouble();
//          System.out.println("Enter the IsActive");
//          Boolean IsActive=scan.nextBoolean();
//          System.out.println("Enter the ImagePath");
//          String ImagePath =scan.next();
//          Resturant res = new Resturant( ResturantName,cuisineType,deliveryTime,
   //Address,adminuserId,Rating,IsActive,ImagePath);
//          ResturantDAOimpl rdao = new ResturantDAOimpl();
//                  rdao.addResturant(res);
//                  System.out.println(res);
   
                            //GET RESTURANT DETAILS//
   
//             System.out.println("Enter the idResturant");
//             int resturantId =scan.nextInt();
//              ResturantDAOimpl   dao  = new ResturantDAOimpl();
//             Resturant resturant= dao.getResturant(resturantId);
//              if(resturant != null) {
//            	  System.out.println("Here are the Resturant Details");
//            	  System.out.println(resturant);
//              }else {
//            	 System.out.println("Sorry For Your Inconvience!Resturant Details Not Found");
//              }
   
   
                               //UPDATE RESTURANTDETAILS//
   
//        System.out.println("Enter the Restaurant ID to update:");
//        int id = scan.nextInt();
//		System.out.println("Enter the ResturantName");
//        String ResturantName=scan.next();
//        System.out.println("Enter the cuisineType");
//        String cuisineType=scan.next();
//        System.out.println("Enter the deliveryTime");
//        int deliveryTime=scan.nextInt();
//        System.out.println("Enter the address");
//        String address=scan.next();        
//        System.out.println("Enter the adminuserId");
//        int adminuserId=scan.nextInt();        
//        System.out.println("Enter the Rating");
//        Double Rating=scan.nextDouble();
//        System.out.println("Enter isActive(True/False)");
//        boolean isActive=scan.nextBoolean();
//        System.out.println("Enter the ImagePath:");
//        String Imagepath=scan.next();	
//    Resturant res = new Resturant(ResturantName,cuisineType,deliveryTime,address,adminuserId, Rating,isActive,Imagepath);
//    res.setResturantId(id);
//    ResturantDAOimpl dao =new ResturantDAOimpl();
//             dao.UpdateResturant(res);
   
                           //DELETE RESTURANT//

//        System.out.println("Enter the ResturantID to Delete");
//        int id=scan.nextInt();
//        ResturantDAOimpl dao = new ResturantDAOimpl();
//        dao.DeleteResturant(id);
   
                         //GET ALL RESTURANT DETAILS//
   
//   System.out.println(" All Resturant Details ");
//     ResturantDAOimpl dao=new ResturantDAOimpl();
//     List<Resturant> resturantList = dao.getAllResturant();
//     if(resturantList.isEmpty()) {
//    	 System.out.println("No Restaurant Details Found!"); 	 
//     }else {
//    	 for(Resturant resturant : resturantList) {
//    		 System.out.println(resturant);
//    	 }
//     }

   
   
   
                                //ADD MENU//
//       Menu  menu =new Menu();
//       System.out.println("Enter the ResturantId:");
//       int resturantId=scan.nextInt();
//       System.out.println("Enter Item Name: ");
//       String itemName=scan.next();
//       System.out.println("Enter Description:");
//       scan.nextLine();      // consume the leftover newline
//       String description = scan.nextLine();
//       System.out.println("Enter Price:");
//       double Price=scan.nextDouble();
//       System.out.println("Is Available (true/false):");
//       boolean isAvaiable=scan.nextBoolean();
//       System.out.println("Enter Rating:");
//       double  Rating=scan.nextDouble();
//       System.out.println("Image Path:");
//       String Imagepath=scan.next();
//       
//       menu.setResturantid(resturantId);
//       menu.setItemName(itemName);
//       menu.setDescription(description);
//       menu.setPrice(Price);
//       menu.setAvaiable(isAvaiable);
//       menu.setRating(Rating);
//       menu.setImagepath(Imagepath);
//
//       MenuDAOimpl dao = new MenuDAOimpl();
//       dao.addMenu(menu);
   
                                //GET MENU DETAILS//
       
//       System.out.println("Enter Menu ID:");
//       int menuId = scan.nextInt();
//       MenuDAOimpl dao = new MenuDAOimpl();
//       Menu menu = dao.getMenu(menuId);
//       if (menu != null) {
//           System.out.println(menu);
//       } else {
//           System.out.println("Menu Not Found");
//       }
   
   
                            //UPDATE MENU DETAILS//
//   
//   System.out.println("Enter Menu ID to Update:");
//   int menuId = scan.nextInt();
//   System.out.println("Enter Restaurant ID:");
//   int resturantId = scan.nextInt();
//   System.out.println("Enter Item Name:");
//   String itemName = scan.next();
//   scan.nextLine(); // consume newline
//   System.out.println("Enter Description:");
//   String description = scan.nextLine();
//   System.out.println("Enter Price:");
//   double price = scan.nextDouble();
//   System.out.println("Is Available (true/false):");
//   boolean isAvailable = scan.nextBoolean();
//   System.out.println("Enter Rating:");
//   double rating = scan.nextDouble();
//   System.out.println("Enter Image Path:");
//   String imagePath = scan.next();
//
//   Menu menu = new Menu();
//   menu.setMenuid(menuId);
//   menu.setResturantid(resturantId);
//   menu.setItemName(itemName);
//   menu.setDescription(description);
//   menu.setPrice(price);
//   menu.setAvaiable(isAvailable);
//   menu.setRating(rating);
//   menu.setImagepath(imagePath);
//
//   MenuDAOimpl dao = new MenuDAOimpl();
//   dao.updateMenu(menu);
   
   
                                //DELETE MENU//
//       
//   System.out.println("Enter Menu ID to Delete:");
//   int menuId = scan.nextInt();
//   MenuDAOimpl dao = new MenuDAOimpl();
//   dao.deleteMenu(menuId);
       
                                 //GET ALL MENU DETAILS
   
//   System.out.println("************* ALL MENU DETAILS *************");
//   MenuDAOimpl dao = new MenuDAOimpl();
//   List<Menu> menuList = dao.getAllMenu();
//   if (menuList.isEmpty()) {
//       System.out.println("No Menu Found!");
//   } else {
//       for (Menu menu : menuList) {
//           System.out.println(menu);
//       }
//   }
   
   
                           //ADD ORDER
   
//   
//   System.out.println("Enter User ID:");
//   int userId = scan.nextInt();
//   System.out.println("Enter Restaurant ID:");
//   int resturantId = scan.nextInt();
//   System.out.println("Enter Total Amount:");
//   double totalAmount = scan.nextDouble();
//   scan.nextLine();
//   System.out.println("Enter Status (PLACED/PREPARING/OUT_FOR_DELIVERY/DELIVERED/CANCELLED):");
//   String status = scan.nextLine();
//   System.out.println("Enter Payment Mode:");
//   String paymentMode = scan.nextLine();
//   Order order = new Order();
//   order.setUserId(userId);
//   order.setResturantId(resturantId);
//   order.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
//   order.setTotalAmount(totalAmount);
//   order.setStatus(status);
//   order.setPaymentMode(paymentMode);
//
//   OrderDAOImpl dao = new OrderDAOImpl();
//   dao.addOrder(order);
//   
   
   
                                //GET ORDER DETAILS//
   
//   Scanner sc = new Scanner(System.in);
//   OrderDAOImpl orderDAO = new OrderDAOImpl();
//   System.out.println("Enter Order ID:");
//   int orderId = sc.nextInt();
//   Order order = orderDAO.getOrder(orderId);
//   while(order != null) {
//       System.out.println("========== Order Details ==========");
//       System.out.println("Order ID: " + order.getOrderId());
//       System.out.println("User ID: " + order.getUserId());
//       System.out.println("Restaurant ID: " + order.getResturantId());
//       System.out.println("Order Date: " + order.getOrderDate());
//       System.out.println("Total Amount: " + order.getTotalAmount());
//       System.out.println("Status: " + order.getStatus());
//       System.out.println("Payment Mode: " + order.getPaymentMode());
//       System.out.println("===================================");
//       break;
//   }
//   if(order == null) {
//       System.out.println("Order Not Found!");
//   }
//   sc.close();
   
   
                                //UPDATE OREDR DETAILS
   
//   Scanner sc = new Scanner(System.in);
//   OrderDAOImpl orderDAO = new OrderDAOImpl();
//   System.out.println("Enter Order ID to Update:");
//   int orderId = sc.nextInt();
//   System.out.println("Enter User ID:");
//   int userId = sc.nextInt();
//   System.out.println("Enter Restaurant ID:");
//   int resturantId = sc.nextInt();
//   System.out.println("Enter Total Amount:");
//   double totalAmount = sc.nextDouble();
//   sc.nextLine(); // clear buffer
//   System.out.println("Enter Status (PLACED/PREPARING/OUT_FOR_DELIVERY/DELIVERED/CANCELLED):");
//   String status = sc.nextLine();
//   System.out.println("Enter Payment Mode (ONLINE/COD):");
//   String paymentMode = sc.nextLine();
//   Order order = new Order();
//   order.setOrderId(orderId);
//   order.setUserId(userId);
//   order.setResturantId(resturantId);
//   order.setOrderDate(new Timestamp(System.currentTimeMillis())); 
//   order.setTotalAmount(totalAmount);
//   order.setStatus(status);
//   order.setPaymentMode(paymentMode);
//   orderDAO.updateOrder(order);
   
                                   //DELETE ORDER
   
//   Scanner sc = new Scanner(System.in);
//   OrderDAOImpl orderDAO = new OrderDAOImpl();
//   System.out.println("Enter Order ID to Delete:");
//   int orderId = sc.nextInt();
//   orderDAO.deleteOrder(orderId);
//   sc.close();
   
                            //GET ALL ORDER DETAILS//
   
//   OrderDAOImpl orderDAO = new OrderDAOImpl();
//   List<Order> orderList = orderDAO.getAllOrders();
//   if(orderList.size() > 0) {
//       for(Order order : orderList) {
//           System.out.println("========== Order Details ==========");
//           System.out.println("Order ID: " + order.getOrderId());
//           System.out.println("User ID: " + order.getUserId());
//           System.out.println("Restaurant ID: " + order.getResturantId());
//           System.out.println("Order Date: " + order.getOrderDate());
//           System.out.println("Total Amount: " + order.getTotalAmount());
//           System.out.println("Status: " + order.getStatus());
//           System.out.println("Payment Mode: " + order.getPaymentMode());
//           System.out.println("-----------------------------------");
//       }
//   }else {
//       System.out.println("No Orders Found");
//   }
   
   
                            //ADD OREDER-ITEM DETAILS//
   
//   System.out.println("Enter Order ID:");
//   int orderId = scan.nextInt();
//   System.out.println("Enter Menu ID:");
//   int menuId = scan.nextInt();
//   System.out.println("Enter Quantity:");
//   int quantity = scan.nextInt();
//   System.out.println("Enter Price:");
//   double price = scan.nextDouble();
//   OrderItem orderItem = new OrderItem();
//   orderItem.setOrderId(orderId);
//   orderItem.setMenuId(menuId);
//   orderItem.setQuantity(quantity);
//   orderItem.setPrice(price);
//   OrderItemImpl dao = new OrderItemImpl();
//   dao.addOrderItem(orderItem);
   
   
                       //GET ORDER-ITEM DETAILS// 
   
//   System.out.println("Enter Order Item ID:");
//   int orderItemId = scan.nextInt();
//   OrderItemImpl dao = new OrderItemImpl();
//   OrderItem orderItem = dao.getOrderItem(orderItemId);
//   if(orderItem != null) {
//       System.out.println(orderItem);
//   }
//   else {
//       System.out.println("Order Item Not Found");
//   }
//   
   
                      
                      //UPDATE ORDER ITEM DETAILS//
   
//   System.out.println("Enter Order Item ID:");
//   int orderItemId = scan.nextInt();
//   System.out.println("Enter Order ID:");
//   int orderId = scan.nextInt();
//   System.out.println("Enter Menu ID:");
//   int menuId = scan.nextInt();
//   System.out.println("Enter Quantity:");
//   int quantity = scan.nextInt();
//   System.out.println("Enter Price:");
//   double price = scan.nextDouble();
//   OrderItem orderItem = new OrderItem();
//   orderItem.setOrderItemId(orderItemId);
//   orderItem.setOrderId(orderId);
//   orderItem.setMenuId(menuId);
//   orderItem.setQuantity(quantity);
//   orderItem.setPrice(price);
//   OrderItemImpl dao = new OrderItemImpl();
//   dao.updateOrderItem(orderItem);
  
                                   //DELETE ORDER-ITEM DETAILS//
 
//   System.out.println("Enter Order Item ID:");
//   int orderItemId = scan.nextInt();
//   OrderItemImpl dao = new OrderItemImpl();
//   dao.deleteOrderItem(orderItemId);
//   
                        //GET ALL ORDER-ITEM DETAILS//
   
//   OrderItemImpl dao = new OrderItemImpl();
//   List<OrderItem> orderItemList = dao.getAllOrderItems();
//   if(orderItemList.isEmpty()) {
//       System.out.println("No Order Items Found");
//   } else {
//       for(OrderItem orderItem : orderItemList) {
//           System.out.println(orderItem);
//       }
//   }
//   
   
                       //ADD CART DETAILS//
   
//   System.out.println("Enter User ID:");
//   int userId = scan.nextInt();
//   Cart cart = new Cart();
//   cart.setUserId(userId);
//   CartDAOimpl dao = new CartDAOimpl();
//   dao.addCart(cart);
//   
   
                           //GET CART DETAILS//
   
//   System.out.println("Enter Cart ID:");
//   int cartId = scan.nextInt();
//   CartDAOimpl dao = new CartDAOimpl();
//   Cart cart = dao.getCart(cartId);
//   if(cart != null) {
//       System.out.println(cart);
//   } else {
//       System.out.println("Cart Not Found");
//   }
   
                                //UPDATE CART DETAILS//.
   
//   System.out.println("Enter Cart ID:");
//   int cartId = scan.nextInt();
//   System.out.println("Enter New User ID:");
//   int userId = scan.nextInt();
//   Cart cart = new Cart();
//   cart.setCartId(cartId);
//   cart.setUserId(userId);
//   CartDAOimpl dao = new CartDAOimpl();
//   dao.updateCart(cart);

  
                       //DELETE CART DETAILS//
   
//   System.out.println("Enter Cart ID:");
//   int cartId = scan.nextInt();
//   CartDAOimpl dao = new CartDAOimpl();
//   dao.deleteCart(cartId);

   
                    //GET ALL CART DETAILS//
   
//   CartDAOimpl dao = new CartDAOimpl();
//   List<Cart> cartList = dao.getAllCarts();
//   if(cartList.isEmpty()) {
//       System.out.println("No Carts Found");
//   } else {
//       for(Cart cart : cartList) {
//           System.out.println(cart);
//       }
//   }
   
                     //ADD CART-ITEM DETAILS//
//    System.out.println("Enter Cart ID:");
//   int cartId = scan.nextInt();
//   System.out.println("Enter Menu ID:");
//   int menuId = scan.nextInt();
//   System.out.println("Enter Quantity:");
//   int quantity = scan.nextInt();
//   CartItem cartItem = new CartItem();
//   cartItem.setCartId(cartId);
//   cartItem.setMenuId(menuId);
//   cartItem.setQuantity(quantity);
//   CartItemDAOimpl dao = new CartItemDAOimpl();
//   dao.addCartItem(cartItem);
   
                 //UPDATE CART-ITEM DETAILS//
   
//   System.out.println("Enter Cart Item ID:");
//   int cartItemId = scan.nextInt();
//   System.out.println("Enter Cart ID:");
//   int cartId = scan.nextInt();
//   System.out.println("Enter Menu ID:");
//   int menuId = scan.nextInt();
//   System.out.println("Enter Quantity:");
//   int quantity = scan.nextInt();
//   CartItem cartItem = new CartItem();
//   cartItem.setCartItemId(cartItemId);
//   cartItem.setCartId(cartId);
//   cartItem.setMenuId(menuId);
//   cartItem.setQuantity(quantity);
//   CartItemDAOimpl dao = new CartItemDAOimpl();
//   dao.updateCartItem(cartItem);
   
                                       //DELETE CART-ITEM DETAILS//
   
//   System.out.println("Enter Cart Item ID to Delete:");
//   int cartItemId = scan.nextInt();
//   CartItemDAOimpl dao = new CartItemDAOimpl();
//   dao.deleteCartItem(cartItemId);
   
   //GET ALL CART-ITEM DETAILS//
   
   CartItemDAOimpl dao = new CartItemDAOimpl();
   List<CartItem> cartItemList = dao.getAllCartItems();
   if (cartItemList.isEmpty()) {
       System.out.println("No Cart Items Found");
   } else {
       System.out.println("========== ALL CART ITEMS ==========");

       for (CartItem cartItem : cartItemList) {
           System.out.println(cartItem);
       }
   }
   
   
	}

}

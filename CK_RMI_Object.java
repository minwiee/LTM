package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RMI.ObjectService;
import RMI.Order;

public class CK_RMI_Object {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "7ApbwKtD";
        try {
            Registry registry = LocateRegistry.getRegistry("203.162.10.109");
            ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

            // ObjectService objectService = (ObjectService) LocateRegistry.getRegistry("203.162.10.109")
            //         .lookup("RMIObjectService");
            Order order = (Order) objectService.requestObject(msv, qcode);
            System.out.println("den day chua");
            StringBuilder orderCode = new StringBuilder();
            orderCode.append((Character.toUpperCase((order.getShippingType().charAt(0)))));
            orderCode.append((Character.toUpperCase((order.getShippingType().charAt(1)))));
            orderCode.append(order.getCustomerCode().substring(order.getCustomerCode().length() - 3));
            System.out.println(order.getOrderDate());
            System.out.println(orderCode.toString());
            String[] dateParts = order.getOrderDate().split("-");
            orderCode.append(dateParts[2]);
            orderCode.append(dateParts[1]);
            order.setOrderCode(orderCode.toString());
            System.out.println("ordercode:" + orderCode.toString());
            objectService.submitObject(msv, qcode, order);

        } catch (Exception e) {
        }
    }
}

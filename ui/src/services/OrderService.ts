import axios from "axios";
import {Order} from "../model/Order";
import {StatusOrder} from "../model/StatusOrder";


class OrderService {

    async createOrder(order: Order): Promise<Order> {
        const response = await axios.post<Order>('http://localhost:8080/api/v1/orders/create', order,
            {withCredentials: true});
        return response.data;
    }

    /*  async findAllOrdersByStatus(status: string): Promise<Array<Order>> {
          const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders/status');
          return response.data;
      }*/

    async findAllOrdersByPage(): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders',
            {withCredentials: true});
        return response.data;
    }


    async findOrdersByUserId(userId: number): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders/' + userId,
            {withCredentials: true});
        return response.data;
    }

    async updateOrderStatus(status: StatusOrder) {

        const response = await axios.put('http://localhost:8080/api/v1/orders/status',
            status, {withCredentials: true});
        return response.data;
    }

    async findOrderByOrderId(orderId: number): Promise<Order> {
        const response = await axios.get<Order>('http://localhost:8080/api/v1/orders/order/' + orderId,
            {withCredentials: true});
        return response.data;
    }

}

export default new OrderService();
import axios from "axios";
import {Order} from "../model/Order";


class OrderService {

    async createOrder(order: Order): Promise<Order> {
        const response =await axios.post<Order>('http://localhost:8080/api/v1/orders/create', order);
        return response.data;
    }
  /*  async findAllOrdersByStatus(status: string): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders/status');
        return response.data;
    }*/

    async findAllOrdersByPage(): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders');
        return response.data;
    }


    async findOrdersByUserId(userId: number): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders/' + userId);
        return response.data;
    }

    async updateOrderStatus(status: string, orderId: number): Promise<void> {
        const response = await axios.put('http://localhost:8080/api/v1/orders/status');
        return response.data;
    }

    async findOrderByOrderId(orderId: number): Promise<Order> {
        const response = await axios.get<Order>('http://localhost:8080/api/v1/orders/order/' + orderId);
        return response.data;
    }

}

export default new OrderService();
import axios from "axios";
import {Order} from "../model/Order";
import {Product} from "../model/Product";

class OrderService {

    async createOrder(order: Order): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/orders', order);
    }
    async findAllOrdersByStatus(): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders/status');
        return response.data;
    }
    async findAllOrdersByPage(): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders');
        return response.data;
    }
    async getOrder(): Promise<Order> {
        const response = await axios.get<Order>('http://localhost:8080/api/v1/orders/${orderId}');
        return response.data;
    }
    async findOrdersByUserId(): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders/orders-by/${userId}');
        return response.data;
    }
    async updateOrderStatus(order: Order): Promise<void> {
        const response = await axios.put('http://localhost:8080/api/v1/orders/status/update');
        return response.data;
    }
    async findStatusOrder(): Promise<String> {
        const response = await axios.get<String>('http://localhost:8080/api/v1/orders/status/${orderId}');
        return response.data;
    }


}
export default new OrderService();
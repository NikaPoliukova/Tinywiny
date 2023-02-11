import axios from "axios";
import {Order} from "../model/Order";

class OrderService {

    async getOrders(): Promise<Array<Order>> {
        const response = await axios.get<Array<Order>>('http://localhost:8080/api/v1/orders');
        return response.data;
    }

    async saveOrder(order: Order): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/orders/create', order);
    }



}
export default new OrderService();
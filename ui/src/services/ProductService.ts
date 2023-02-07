import axios from "axios";
import {Product} from "../model/Product";

class ProductService {

    async getProducts(): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/order/products-for-order');
        return response.data;
    }



}
export default new ProductService();
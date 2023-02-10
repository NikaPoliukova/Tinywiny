import axios from "axios";
import {Product} from "../model/Product";

class ProductService {

    async getProducts(): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/products/type');
        return response.data;
    }

    async getProductsInBucket(): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/bucket');
        return response.data;
    }

}
export default new ProductService();
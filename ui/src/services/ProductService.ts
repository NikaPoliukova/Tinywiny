import axios from "axios";
import {Product} from "../model/Product";
import {ProductInBucket} from "../model/ProductInBucket";

class ProductService {
    async createProduct(product: Product): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/products', product);
    }

    async findAllProductsByTypeAndPage(type : string): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/products/type/' + type);
        return response.data;
    }
    async findAllProducts(): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/admin/products');
        return response.data;
    }

    async getProduct(productId: number): Promise<Product> {
        const response = await axios.get<Product>('http://localhost:8080/api/v1/products/' + productId);
        return response.data;
    }

    async updateProduct(productId : number, product: Product): Promise<void> {
        const response = await axios.put('http://localhost:8080/api/v1/admin/products' + productId, product);
        return response.data;
    }

    async deleteProduct(): Promise<void> {
        const response = await axios.post('http://localhost:8080/api/v1/admin/products/${productId}');
        return response.data;
    }


}

export default new ProductService();
import axios from "axios";
import {Product} from "../model/Product";
import {ProductInBucket} from "../model/ProductInBucket";

class ProductService {
    async createProduct(product: Product): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/products/create', product);
    }

    async findAllProductsByTypeAndPage(): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/products/type');
        return response.data;
    }

    async getProduct(): Promise<Product> {
        const response = await axios.get<Product>('http://localhost:8080/api/v1/products/${productId}');
        return response.data;
    }

    async addProductInBucket(productInBucket: ProductInBucket): Promise<Array<ProductInBucket>> {
        const response = await axios.post<Array<ProductInBucket>>('http://localhost:8080/api/v1/bucket', productInBucket);
        return response.data;
    }

    async updateProduct(product: Product): Promise<void> {
        const response = await axios.put('http://localhost:8080/api/v1/products');
        return response.data;
    }

    async deleteProduct(): Promise<void> {
        const response = await axios.post('http://localhost:8080/api/v1/products/${productId}');
        return response.data;
    }


}

export default new ProductService();
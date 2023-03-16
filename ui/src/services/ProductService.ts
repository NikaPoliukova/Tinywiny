import axios from "axios";
import {Product} from "../model/Product";
import { Buffer } from 'buffer';

class ProductService {

    async uploadFile({productName,price,countInStock,description,idType, file}: any): Promise<any> {
        const formData = new FormData();
        formData.append('productName', productName);
        formData.append('price', price);
        formData.append('countInStock', countInStock);
        formData.append('description', description);
        formData.append('idType', idType);
        formData.append('file', file, file.name);
        const response = await axios.post<any>('http://localhost:8080/api/v1/admin/product/create', formData, {
            withCredentials: true,
            headers: {
                "Content-Type": "multipart/form-data",
            },
        });
        return Buffer.from(response.data, 'binary').toString('base64');
    }

    async findAllProductsByTypeAndPage(type : string): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/products/type/' + type,
            {withCredentials: true});
        return response.data;
    }
    async findAllProducts(): Promise<Array<Product>> {
        const response = await axios.get<Array<Product>>('http://localhost:8080/api/v1/admin/products',
            {withCredentials: true});
        return response.data;
    }

    async getProduct(productId: number): Promise<Product> {
        const response = await axios.get<Product>('http://localhost:8080/api/v1/products/' + productId,
            {withCredentials: true});
        return response.data;
    }

    async updateProduct(productId : number, product: Product): Promise<void> {
        const response = await axios.put('http://localhost:8080/api/v1/admin/products/' + productId, product,
        {withCredentials: true});
        return response.data;
    }

    async deleteProduct(productId : number): Promise<void> {
        const response = await axios.post('http://localhost:8080/api/v1/admin/products/' +productId,
            {withCredentials: true});
        return response.data;
    }
    /*async createProduct(product: Product): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/products', product);
    }*/


}

export default new ProductService();
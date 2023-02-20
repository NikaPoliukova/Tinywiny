import axios from "axios";
import {Bucket} from "../model/Bucket";
import {ProductInBucket} from "../model/ProductInBucket";
import {Product} from "../model/Product";

class BucketService {

    async findAllProductsInBucket(bucketId: number): Promise<Array<ProductInBucket>> {
        const response = await axios.get<Array<ProductInBucket>>('http://localhost:8080/api/v1/bucket/' + bucketId);
        return response.data;
    }

    async findBucketByBucketId(bucketId: number): Promise<Bucket> {
        const response = await axios.get<Bucket>('http://localhost:8080/api/v1/bucket/' + bucketId);
        return response.data;
    }

    async addProductInBucket(productInBucket: ProductInBucket): Promise<ProductInBucket> {
        const response = await axios.post<ProductInBucket>('http://localhost:8080/api/v1/bucket', productInBucket);
        return response.data;
    }

    async deleteProductInBucket(productInBucket: ProductInBucket): Promise<void> {
        await axios.delete('http://localhost:8080/api/v1/bucket');
    }

    async updateCountProduct(productInBucket: ProductInBucket): Promise<ProductInBucket> {
        const response = await axios.put<ProductInBucket>('http://localhost:8080/api/v1/bucket', productInBucket);
        return response.data
    }
}

export default new BucketService();
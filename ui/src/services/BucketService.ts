import axios from "axios";
import {Bucket} from "../model/Bucket";
import {ProductInBucket} from "../model/ProductInBucket";

class BucketService {

    async findAllProductsInBucket(bucketId: number): Promise<Array<ProductInBucket>> {
        const response = await axios.get<Array<ProductInBucket>>('http://localhost:8080/api/v1/bucket/' + bucketId);
        return response.data;
    }

    async findBucketByUserId(userId: number): Promise<Bucket> {
        const param = {userId: userId};
        const response = await axios.get<Bucket>('http://localhost:8080/api/v1/bucket', {
            data: param
        });
        return response.data;
    }

    async findBucketByBucketId(bucketId: number): Promise<Bucket> {
        const response = await axios.get<Bucket>('http://localhost:8080/api/v1/bucket/' + bucketId);
        return response.data;
    }

    async getSumWithDiscount(sum: number) {
        const param = {sum: sum};
        const response = await axios.get<number>('http://localhost:8080/api/v1/bucket/final-sum', {
            data: param
        });
        return response.data;
    }

    async getSumProductInBucket(bucketId: number) {
        const param = {bucketId: bucketId};
        const response = await axios.get<number>('http://localhost:8080/api/v1/bucket/sum/all', {
            data: param
        });
        return response.data;
    }

    async addProductInBucket(productInBucket: ProductInBucket): Promise<ProductInBucket> {
        const response = await axios.post<ProductInBucket>('http://localhost:8080/api/v1/bucket', productInBucket);
        return response.data;
    }

    async deleteProductInBucket(id: number): Promise<void> {
        const param = {id: id};
        const response = await axios.delete('http://localhost:8080/api/v1/bucket', {
            data: param
        });
        return response.data;
    }

    async deleteAllProductsInBucket(bucketId: number): Promise<void> {
        const response = await axios.delete('http://localhost:8080/api/v1/bucket/' + bucketId);
        return response.data;
    }

    async updateCountProduct(productInBucket: ProductInBucket): Promise<ProductInBucket> {
        const response = await axios.put<ProductInBucket>('http://localhost:8080/api/v1/bucket', productInBucket);
        return response.data
    }
}

export default new BucketService();
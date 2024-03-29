import axios from "axios";
import {Bucket} from "../model/Bucket";
import {ProductInBucket} from "../model/ProductInBucket";
import {OrderSumDto} from "../model/OrderSumDto";


class BucketService {

    async findAllProductsInBucket(): Promise<Array<ProductInBucket>> {
        const response = await axios.get<Array<ProductInBucket>>('http://localhost:8080/api/v1/bucket',
            {withCredentials: true});
        return response.data;
    }

    async findBucketByUserId(userId: number): Promise<Bucket> {
        const params = {userId: userId}
        const response = await axios.get<Bucket>('http://localhost:8080/api/v1/bucket/user',
            {params, withCredentials: true});
        return response.data;
    }

    async findBucketByBucketId(bucketId: number): Promise<Bucket> {
        const response = await axios.get<Bucket>('http://localhost:8080/api/v1/bucket/' + bucketId,
            {withCredentials: true});
        return response.data;
    }

    /* async getSumWithDiscount(sum: number) {
         const params = {sum: sum};
         const response = await axios.get<number>('http://localhost:8080/api/v1/bucket/final-sum', {
             params
         });
         return response.data;
     }*/

    async getSumProductInBucket(): Promise<OrderSumDto> {
        const response = await axios.get<OrderSumDto>('http://localhost:8080/api/v1/bucket/sum/all',
            {withCredentials: true}
        );
        return response.data;
    }

    async addProductInBucket(productId: number) {
        const params = {productId: productId};
        await axios.post('http://localhost:8080/api/v1/bucket/product/' + productId,
            {params: params}, {withCredentials: true});
    }

    async deleteProductInBucket(productInBucketId: number) {
        const response = await axios.delete('http://localhost:8080/api/v1/bucket/product/' + productInBucketId,
            {withCredentials: true}
        );
        return response.data;
    }

    async deleteAllProductsInBucket(): Promise<void> {
        const response = await axios.delete('http://localhost:8080/api/v1/bucket',
            {withCredentials: true});
        return response.data;
    }

    async updateCountProduct(productInBucket: ProductInBucket): Promise<ProductInBucket> {
        const response = await axios.put<ProductInBucket>('http://localhost:8080/api/v1/bucket', productInBucket,
            {withCredentials: true});
        return response.data
    }
}

export default new BucketService();
import axios from "axios";
import {Bucket} from "../model/Bucket";
import {ProductInBucket} from "../model/ProductInBucket";
import {Product} from "../model/Product";

class BucketService {

    async findAllProductsInBucket(): Promise<Array<ProductInBucket>> {
        const response = await axios.get<Array<ProductInBucket>>('http://localhost:8080/api/v1/bucket/${bucketId}');
        return response.data;
    }
    async saveBucket(bucket: Bucket): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/bucket', bucket);
    }
    async deleteProductInBucket(productInBucket: ProductInBucket): Promise<void> {
        await axios.delete('http://localhost:8080/api/v1/bucket');
    }


}

export default new BucketService();
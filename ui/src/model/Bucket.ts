import {ProductInBucket} from "./ProductInBucket";

export interface Bucket {
    bucketId: number;
    user: object;
    productInBuckets : Array<ProductInBucket>

}
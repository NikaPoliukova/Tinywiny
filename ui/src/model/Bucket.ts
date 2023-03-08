import {ProductInBucket} from "./ProductInBucket";

export interface Bucket {
    bucketId: number;
    user: object;
    productInBucketDto : Array<ProductInBucket>

}
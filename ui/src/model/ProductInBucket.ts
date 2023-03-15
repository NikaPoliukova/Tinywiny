import {Product} from "./Product";

export interface ProductInBucket {
    id?: number;
    count?: number;
    productDto?: Product;
    bucketId?: number;

}
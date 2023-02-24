import {Product} from "./Product";


export interface ProductInBucket {
    id?: number;
    count?: number;
    //discountId?: number;
    //sum?: number;
    productDto?: Product;
    bucketId: number;
}
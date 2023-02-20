import {Image} from "./Image";

export interface Product {
    productId?: number;
    productName?: string;
    price?: number;
    description?: string;
    countInStock?: number;
    idType?: number;
    image?: Image;

}
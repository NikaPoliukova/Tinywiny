import {ProductInOrder} from "./ProductInOrder";

export interface Order {

    orderId?: number;
    commentOrder: string;
    userId: number;
    deliveryInformation: object;
    deliveryTypeId: number;
    statusOrder?: string;
    createdAt?: string;
    productsInOrder: Array<ProductInOrder>
}





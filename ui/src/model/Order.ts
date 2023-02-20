import {ProductInOrder} from "./ProductInOrder";
import {DeliveryInformation} from "./DeliveryInformation";

export interface Order {

    orderId?: number;
    commentOrder?: string;
    userId?: number;
    deliveryInformation?: DeliveryInformation;
    deliveryTypeId?: number;
    statusOrder?: string;
    createdAt?: string;
    productsInOrder?: Array<ProductInOrder>
}





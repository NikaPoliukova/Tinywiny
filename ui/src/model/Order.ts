export interface Order {

    orderId: number;
    commentOrder: string;
    userId: number;
    deliveryInformation: object;
    deliveryTypeId: number;
    statusOrder: string;
    //List<ProductInOrderDto> productsInOrder;
    //createdAt: date;
}
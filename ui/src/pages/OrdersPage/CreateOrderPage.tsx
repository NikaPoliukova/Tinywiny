import React, {useEffect, useState} from 'react';
import {Button, Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import Header from '../component/MyHeader';
import {Footer} from "../component/Footer";
import {ProductInOrder} from "../../model/ProductInOrder";
import {useNavigate} from "react-router-dom";
import {DeliveryInformation} from "../../model/DeliveryInformation";
import {Form, Table} from "semantic-ui-react";
import {Order} from "../../model/Order";
import OrderService from "../../services/OrderService";
import BucketService from "../../services/BucketService";
import {ProductInBucket} from "../../model/ProductInBucket";
import Container from "@mui/material/Container";
import { Bucket } from 'model/Bucket';


export default function CreateOrderPage() {
    const [userId, setUserId] = useState(1);//ДОСТАТЬ ИЗ СЕССИИ
    const [customerName, setFirstName] = useState('');
    const [customerLastName, setLastName] = useState('');
    const [customerSurname, setSureName] = useState('');
    const [addressDelivery, setAddress] = useState('');
    const [commentOrder, setComment] = useState('');
    const [deliveryTypeId, setDeliveryType] = useState(0);
    const [productsInOrder, setProductsInOrder] = useState<Array<ProductInOrder>>([]);
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const [sumProducts, setSumProducts] = useState(Number);
    const [finalSum, setFinalSum] = useState(Number);
    const [bucket, setBucket] = useState<Bucket>();

    useEffect(() => {
        BucketService.findAllProductsInBucket(3)
            .then(response => setProducts(response));
    }, []);
    useEffect(() => {
        BucketService.findBucketByUserId(Number(userId))
            .then(bucket => setBucket(bucket));
    }, []);


    const deliveryInformation: DeliveryInformation = {
        customerName,
        customerLastName,
        customerSurname,
        addressDelivery,
        userId
    }
//Переложить продукты в ордеркарзину
    const navigate = useNavigate();
    const createOrder = () => {

        const order: Order = {
            commentOrder,
            userId,
            deliveryInformation,
            deliveryTypeId,
            productsInOrder,
            sum: finalSum
        };

        OrderService.createOrder(order).then(response => navigate("/products/type/toys"));
        BucketService.deleteAllProductsInBucket(Number(bucket?.bucketId)).then(() => navigate("/products/type/toys") );
    }
    return (
        <React.Fragment>
            <Header/>
            <Container maxWidth="sm">
                <Typography
                    component="h1"
                    variant="h2"
                    align="center"
                    color="text.primary">
                    Create order
                </Typography>
            </Container>
            <Card style={{width: 1000}}
                  sx={{
                      marginTop: 10,
                      display: 'flex',
                      flexDirection: 'column',
                      alignItems: 'center',

                  }}>
                <h2> Product in order</h2>
                <TableContainer>
                    <Table celled padded>
                        <TableHead>
                            <Table.Row>
                                <Table.HeaderCell>Product Name</Table.HeaderCell>
                                <Table.HeaderCell>Count</Table.HeaderCell>
                                <Table.HeaderCell>Price</Table.HeaderCell>

                            </Table.Row>
                        </TableHead>
                        <TableBody>
                            {products.map((item) => (
                                <TableRow
                                    key={item.productDto?.productId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.productName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {item.count}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.price}
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                <div>Sum order consist:</div>
            </Card>
            <h2> Add information</h2>
            <Form>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='FirstName' placeholder='Enter firstname'
                                value={customerName}
                                onChange={e => setFirstName(e.target.value)}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='LastName' placeholder='Enter lastname' value={customerLastName}
                                onChange={e => setLastName(e.target.value)}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='SureName' placeholder='Enter surename'
                                value={customerSurname}
                                onChange={e => setSureName(e.target.value)}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Address' placeholder='Enter address'
                                value={addressDelivery}
                                onChange={e => setAddress(e.target.value)}/>
                </Form.Group>
                <Form.Group widths='equal'>
                </Form.Group>
                <Form.TextArea label='Order comment'
                               value={commentOrder}
                               onChange={e => setComment(e.target.value)}/>
                <div>Тип доставки</div>
                <Form.Group>
                    <select value={deliveryTypeId} onChange={event => setDeliveryType(Number(event.target.value))}>
                        <option value={1}>Белпочта</option>
                        <option value={2}>SDEK</option>
                    </select>
                </Form.Group>
                <div>Sum for products : {sumProducts}</div>
                <div>Final sum with discount : {finalSum}</div>
                <Button color="secondary"
                        variant="contained"
                        onClick={createOrder}
                >Create order</Button>
            </Form>
            <Footer/>
        </React.Fragment>
    );
}
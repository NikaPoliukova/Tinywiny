import React, {useEffect, useState} from 'react';
import {Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import Header from '../component/MyHeader';
import {Footer} from "../component/Footer";
import {useNavigate} from "react-router-dom";
import {DeliveryInformation} from "../../model/DeliveryInformation";
import {Button, Form, Table} from "semantic-ui-react";
import {Order} from "../../model/Order";
import OrderService from "../../services/OrderService";
import BucketService from "../../services/BucketService";
import {ProductInBucket} from "../../model/ProductInBucket";
import Container from "@mui/material/Container";
import {OrderSumDto} from "../../model/OrderSumDto";
import {useSessionStore} from "../../store";


export default function CreateOrderPage() {
    const user = useSessionStore(state => state.user);

    const [customerName, setFirstName] = useState('');
    const [customerLastName, setLastName] = useState('');
    const [customerSurname, setSureName] = useState('');
    const [addressDelivery, setAddress] = useState('');
    const [commentOrder, setComment] = useState('');
    const [deliveryTypeId, setDeliveryType] = useState(0);
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const [sumProducts, setSumProducts] = useState<OrderSumDto>();


    useEffect(() => {
        BucketService.findAllProductsInBucket()
            .then(products => {
                setProducts(products);
                return products;
            })
        BucketService.getSumProductInBucket()
            .then(result => setSumProducts(result));
    }, []);


    const deliveryInformationDto: DeliveryInformation = {
        customerName,
        customerLastName,
        customerSurname,
        addressDelivery,
        userId: Number(user?.userId)
    }
    const navigate = useNavigate();
    const createOrder = () => {
        const order: Order = {
            commentOrder,
            userId: Number(user?.userId),
            deliveryInformationDto,
            deliveryTypeId,
            sum: sumProducts?.sumWithDiscount
        };
        OrderService.createOrder(order)
            .then(() => {
                    BucketService.deleteAllProductsInBucket()
                        .then(() => navigate("/products/type/toys"));
                }
            );
    }
    return (
        <React.Fragment>
            <Header/>
            <Container >
                <Typography
                    style={{color: 'var(--primary-color)'}}
                    component="h2"
                    variant="h3"
                    align="center">
                    Create order
                </Typography>

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
                <div>Sum order consist: {sumProducts?.sum}</div>
                <div>Final sum with discount : {sumProducts?.sumWithDiscount}</div>
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
                        <option>select delivery type</option>
                        <option value={1}>Белпочта</option>
                        <option value={2}>SDEK</option>
                    </select>
                </Form.Group>
                <Button basic color='brown'
                        type="submit"
                        variant="outlined"
                        sx={{mt: 3, mb: 2}}
                        onClick={createOrder}
                >Create order</Button>

            </Form>
            </Container>
            <Footer/>
        </React.Fragment>
    );
}
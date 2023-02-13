import React, {useEffect, useState} from 'react';

import {Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Header from '../component/Header';
import {useParams} from "react-router-dom";
import OrderService from "../../services/OrderService";
import {Order} from "../../model/Order";
import {Footer} from "../component/Footer";


export const OrderPage = () => {
    const {orderId} = useParams();
    const [order, setOrder] = useState<Order>();

    useEffect(() => {
        OrderService.findOrderByOrderId(Number(orderId))
            .then(response => setOrder(response));
    }, []);

    return (
        <React.Fragment>
            <Header/>
            <h3>Order № {order?.orderId}</h3>
            <Card style={{width: 1000}}

                  sx={{
                      marginTop: 10,
                      display: 'flex',
                      flexDirection: 'column',
                      alignItems: 'center',

                  }}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <h3>Products for order</h3>
                            <TableRow>
                                <TableCell align="center">Product</TableCell>
                                <TableCell align="center">Count</TableCell>
                                <TableCell align="center">Price</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {order?.productsInOrder.map((productInOrder) => (
                                <TableRow
                                    key={order?.orderId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {productInOrder.productId}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {productInOrder.count}
                                    </TableCell>

                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <h3>Details order</h3>
                            <TableRow>
                                <TableCell align="center">Comment</TableCell>
                                <TableCell align="center">Date created</TableCell>
                                <TableCell align="center">delivery Type Id</TableCell>
                                <TableCell align="center">Status</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            <TableRow
                                key={order?.orderId}
                                sx={{'&:last-child td, &:last-child th': {border: 0}}}
                            >
                                <TableCell component="th" scope="row">
                                    {order?.commentOrder}
                                </TableCell>
                                <TableCell component="th" scope="row">
                                    {order?.createdAt}
                                </TableCell>
                                <TableCell component="th" scope="row">
                                    {order?.deliveryTypeId}
                                </TableCell>
                                <TableCell component="th" scope="row">
                                    {order?.statusOrder}
                                </TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>

                </TableContainer>
            </Card>

            <Footer/>
        </React.Fragment>
    );
}
import React, {useEffect, useState} from 'react';
import {
    Button,
    Card,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import Header from "../component/Header";
import {Order} from "../../model/Order";
import OrderService from "../../services/OrderService";
import {Link, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";


export default function MyOrdersPage (){
    const [orders, setOrders] = useState<Array<Order>>([]);
    const {userId} = useParams();

    useEffect(() => {
        OrderService.findOrdersByUserId(Number(userId))
            .then(response => setOrders(response));
    }, []);


    return (
        <div>
            <Header />

            <Typography component="h1" variant="h5">
                <h1>Orders</h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="center">orderId</TableCell>
                                <TableCell align="center">statusOrder</TableCell>
                                <TableCell align="center">createdAt</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {orders.map((order) => (
                                <TableRow
                                    key={order.orderId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {order.orderId}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {order.statusOrder}
                                    </TableCell>

                                    <Button
                                        component={Link}
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        sx={{mt: 1, mb: 1}}
                                        to={'product/${order.orderId}'}
                                    >
                                        Open
                                    </Button>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
            <Footer />
        </div>
    );
};

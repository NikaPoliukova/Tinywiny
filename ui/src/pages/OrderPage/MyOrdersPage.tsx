import React, {useEffect, useState} from 'react';
import {
    Button,
    Card,
    Container,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import MyHeader from "../component/MyHeader";
import {Order} from "../../model/Order";
import OrderService from "../../services/OrderService";
import {Link, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import {useSessionStore} from "../../store";


export default function MyOrdersPage() {
    const [orders, setOrders] = useState<Array<Order>>([]);
    const {userId} = useParams();
    const user = useSessionStore(state => state.user);
    useEffect(() => {
        OrderService.findOrdersByUserId(Number(userId))
            .then(response => setOrders(response));
    }, []);
    return (
        <div>
            <MyHeader/>
            <Container>
                <Typography style={{color: 'var(--primary-color)'}}
                            component="h1"
                            variant="h2"
                            align="center">
                    <h2>List orders {user?.userName}</h2>
                </Typography>
                <Card style={{width: 1000}}>
                    <TableContainer>
                        <Table sx={{minWidth: 800}} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell align="left">orderId</TableCell>
                                    <TableCell align="left">statusOrder</TableCell>
                                    <TableCell align="left">createdAt</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {orders.map((order) => (
                                    <TableRow
                                        key={order?.orderId}
                                        sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                    >
                                        <TableCell component="th" scope="row">
                                            {order.orderId}
                                        </TableCell>
                                        <TableCell component="th" scope="row">
                                            {order.statusOrder}
                                        </TableCell>
                                        <TableCell component="th" scope="row">
                                            {order.createdAt}
                                        </TableCell>

                                        <Button
                                            component={Link}
                                            type="submit"
                                            fullWidth
                                            variant="contained"
                                            sx={{mt: 1, mb: 1}}
                                            to={"/orders/order/" + order.orderId}
                                        >
                                            Open
                                        </Button>

                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Card>
            </Container>
            <Footer/>
        </div>
    );
};

import React, {useEffect, useState} from 'react';
import {
    Button,
    Card, Select,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import Header from "../component/MyHeader";
import {Order} from "../../model/Order";
import OrderService from "../../services/OrderService";
import {Link, useNavigate} from "react-router-dom";
import {Footer} from "../component/Footer";
import Grid from "@mui/material/Grid";


const OrdersPage = () => {
    const [orders, setOrders] = useState<Array<Order>>([]);
    const navigate = useNavigate();
    const statuses = ['NEW', 'PROCESS', 'FINISH'];
    const [statusOrder, setStatus] = useState<string>('');


    const options = statuses.map((status, orderId) => {
        return <option key={orderId}>{status}</option>;
    });
    const updateStatus = (orderId: number) => {
        OrderService.updateOrderStatus(statusOrder, orderId).then(() => navigate("/admin"))
    }

    useEffect(() => {
        OrderService.findAllOrdersByPage()
            .then(response => setOrders(response))
        ;
    }, []);

    return (
        <div>
            <Header/>
            <Typography component="h1" variant="h5">
                <h1>Orders</h1>

            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="center">Number order</TableCell>
                                <TableCell align="center">statusOrder</TableCell>
                                <TableCell align="center">commentOrder</TableCell>
                                <TableCell align="center">userId</TableCell>
                                <TableCell align="center">createdAt</TableCell>
                                <TableCell align="center">Payment status</TableCell>
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
                                        <Grid item xs={12} sm={5}>
                                            <select value={statusOrder}
                                                    onChange={(e) =>
                                                setStatus(e.target.value)}>
                                                {options}
                                            </select>
                                        </Grid>
                                        <Button
                                            type="submit"
                                            fullWidth
                                            onClick={() => updateStatus(Number(order.orderId))}
                                        >
                                            update status
                                        </Button>
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {order.commentOrder}
                                    </TableCell>
                                    <TableCell align="right">
                                        {order.userId}
                                    </TableCell>
                                    <TableCell align="right">
                                        {order.createdAt}
                                    </TableCell>
                                    <TableCell align="right">
                                        {order.paymentStatus}
                                    </TableCell>
                                    <Button
                                        component={Link}
                                        type="submit"
                                        fullWidth
                                        variant="contained"
                                        sx={{mt: 1, mb: 1}}
                                        to={'/orders/order/' + order.orderId}
                                    >
                                        Open
                                    </Button>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
            <Footer/>
        </div>
    );
};

export default OrdersPage;
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
import Header from "../component/MyHeader";
import {Order} from "../../model/Order";
import OrderService from "../../services/OrderService";
import {Link, useNavigate} from "react-router-dom";
import {Footer} from "../component/Footer";
import Grid from "@mui/material/Grid";


const OrdersPage = () => {
    const [orders, setOrders] = useState<Array<Order>>([]);
    const [statusOrder, setStatus] = useState<string>('');
    const navigate = useNavigate();
    const updateStatus = (statusOrder: string, orderId: number) => {
        OrderService.updateOrderStatus(statusOrder, orderId).then(response => navigate("/orders"))
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
                                <TableCell align="center">orderId</TableCell>
                                <TableCell align="center">statusOrder</TableCell>
                                <TableCell align="center">commentOrder</TableCell>
                                <TableCell align="center">userId</TableCell>
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
                                        value={order.orderId}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {order.statusOrder}
                                        <Grid item xs={12} sm={5}>
                                            <select>
                                                <option value="new">NEW</option>
                                                <option value="finish">FINISH</option>
                                                <option value="prosess">IN PROCESS</option>
                                            </select>
                                        </Grid>
                                        <Button
                                            //      component={Link}
                                            //   type="submit"
                                            //   fullWidth
                                            //   variant="contained"
                                            //   sx={{mt: 1, mb: 1}}
                                            //   onClick={updateStatus(statusOrder, orderId)}
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
            <Footer/>
        </div>
    );
};

export default OrdersPage;
import React, {useState} from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import {Box, Button, Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Header from '../../component/Header';
import {Order} from "../../../model/Order";
import {Footer} from "../../component/Footer";


export default function CreateOrderPage() {
    const [order, setOrder] = useState<Order>();
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [sureName, setSureName] = useState('')
    const [address, setAddress] = useState('')
    const [comment, setComment] = useState('')
    const [deliveryType, setDeliveryType] = useState('')

    return (
        <React.Fragment>
            <Header/>
            <h2> Create order</h2>
            <Card style={{width: 1000}}
                  sx={{
                      marginTop: 10,
                      display: 'flex',
                      flexDirection: 'column',
                      alignItems: 'center',

                  }}>
                <h2> Product in order</h2>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
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
                </TableContainer>
            </Card>
            <Typography variant="h6" gutterBottom align="center">
                Заполни поля для заказа
            </Typography>
            <Box
                sx={{
                    marginTop: 10,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >

                <Grid sx={{minWidth: 800}} aria-label="simple table">
                    <Grid item xs={12} sm={5}>
                        <TextField
                            required
                            id="firstName"
                            name="firstName"
                            label="First name"
                            fullWidth
                            autoComplete="given-name"
                            variant="standard"
                            value={firstName}
                        />
                    </Grid>
                    <Grid item xs={12} sm={5}>
                        <TextField
                            required
                            id="lastName"
                            name="lastName"
                            label="Last name"
                            fullWidth
                            autoComplete="family-name"
                            variant="standard"
                            value={lastName}
                        />
                    </Grid>
                    <Grid item xs={12} sm={5}>
                        <TextField
                            required
                            id="sureName"
                            name="sureName"
                            label="SureName"
                            fullWidth
                            autoComplete="family-name"
                            variant="standard"
                            value={sureName}
                        />
                    </Grid>
                    <Grid item xs={12} sm={5}>
                        <TextField
                            required
                            id="address"
                            name="address"
                            label="Address"
                            fullWidth
                            autoComplete="shipping address-line1"
                            variant="standard"
                            value={address}
                        />
                    </Grid>
                    <Grid item xs={100}>
                        <TextField label="order comments"/>

                    </Grid>
                    <Grid item xs={12} sm={5}>
                        <select>
                            <option value="grapefruit">Европочта</option>
                            <option value="lime">Белпочта</option>

                        </select>
                    </Grid>
                    <Button color="secondary" variant="contained">Create order</Button>
                </Grid>
            </Box>
            <Footer/>
        </React.Fragment>
    );
}
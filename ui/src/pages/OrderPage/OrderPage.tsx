import React, {useEffect, useState} from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';

import {Box, Button, Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";


import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";


export default function OrderPage() {
    const [products, setProduct] = useState<Array<Product>>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        ProductService.getProducts()
            .then(response => setProduct(response))
            .catch(error => setError(error.message));
    }, []);
    return (
        <React.Fragment>
            <Card style={{width: 1000}}>
                <TableContainer >
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">Product</TableCell>
                                <TableCell align="right">Count</TableCell>
                                <TableCell align="right">Price</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {products.map((product) => (
                                <TableRow
                                    key={product.productName}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {product.count}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.price}
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
            <Typography variant="h6" gutterBottom>
                Form for order
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
                        />
                    </Grid>
                    <Grid item xs={12} sm={5}>
                        <TextField
                            required
                            id="surName"
                            name="surName"
                            label="SureName"
                            fullWidth
                            autoComplete="family-name"
                            variant="standard"
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
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            label="order comments"
                        />
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{mt: 1, mb: 1}}
                    >
                        Create order
                    </Button>
                </Grid>
            </Box>
        </React.Fragment>
    )
        ;
}
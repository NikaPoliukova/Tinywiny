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
import BucketService from "../../services/BucketService";
import {Link, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import Header from 'pages/component/Header';
import {Bucket} from "../../model/Bucket";
import {Product} from "../../model/Product";


function BucketPage() {
    const [products, setProducts] = useState<Array<Product>>([])
    const {bucketId} = useParams();


    useEffect(() => {
        BucketService.findAllProductsInBucket(Number(bucketId))
            .then(response => setProducts(response));
    }, []);
    return (
        <div>
            <Header/>
            <Typography component="h1" variant="h5">
                <h1>Bucket </h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">productName</TableCell>
                                <TableCell align="left">price</TableCell>
                                <TableCell align="left">Type product</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {products.map((product) => (
                                <TableRow
                                    key={product.productId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {product.productName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.price}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.idType}
                                    </TableCell>
                                    <TableCell>
                                        <Button
                                            component={Link}
                                            type="submit"

                                            variant="contained"
                                            sx={{mt: 1, mb: 1}}
                                            to={'products/${product.productId}'}
                                        > add count +1 </Button>
                                    </TableCell>
                                    <TableCell>

                                        <Button
                                            component={Link}
                                            type="submit"

                                            variant="contained"
                                            sx={{mt: 1, mb: 1}}
                                            to={'products/${product.productId}'}
                                        > delete
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>

            </Card>
            <Button
                component={Link}
                type="submit"

                variant="contained"
                sx={{mt: 1, mb: 1}}
                to={'/orders/create'}
            >
                Go to order
            </Button>
            <Footer/>
        </div>
    );
};

export default BucketPage;
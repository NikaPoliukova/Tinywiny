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
import {ProductInBucket} from "../../model/ProductInBucket";


function BucketPage() {
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const {bucketId} = useParams();
    useEffect(() => {
        BucketService.findAllProductsInBucket()
            .then(response => setProducts(response));
    }, []);

    return (
        <div>
            <Typography component="h1" variant="h5">
                <h1>Bucket </h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">productId</TableCell>
                                <TableCell align="right">count</TableCell>

                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {products.map((product) => (
                                <TableRow
                                    key={product.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {product.productId}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.count}
                                    </TableCell>
                                    <TableCell>
                                        <Button
                                            component={Link}
                                            type="submit"

                                            variant="contained"
                                            sx={{mt: 1, mb: 1}}
                                            to={'products/${product.productId}'}
                                        > add </Button>
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
                to={'/products-for-order'}
            >
                Go to order
            </Button>
        </div>
    );
};

export default BucketPage;
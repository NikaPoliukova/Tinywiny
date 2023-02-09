import React, {useEffect, useState} from 'react';
import {Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";


const BucketPage = () => {
    const [products, setProduct] = useState<Array<Product>>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        ProductService.getProductsInBucket()
            .then(response => setProduct(response))
            .catch(error => setError(error.message));
    }, []);

    return (
        <div>
            {error}
            <Typography component="h1" variant="h5">
                <h1>Bucket</h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table sx={{minWidth: 800}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="right">Product</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {products.map((product) => (
                                <TableRow
                                    key={product.productName}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {product.productName}
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Card>
        </div>
    );
};

export default BucketPage;
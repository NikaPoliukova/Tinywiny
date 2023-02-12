import React, {useEffect, useState} from 'react';
import {Card, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import BucketService from "../../services/BucketService";
import {Product} from "../../model/Product";


const BucketPage = () => {
    const [products, setProducts] =useState<Array<Product>>([])
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
                                <TableCell align="right">productName</TableCell>
                                <TableCell align="right">price</TableCell>
                                <TableCell align="right">description</TableCell>
                                <TableCell align="right">count in stock</TableCell>
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
                                    <TableCell component="th" scope="row">
                                        {product.price}
                                    </TableCell>
                                    <TableCell align="right">
                                        {product.description}
                                    </TableCell>
                                    <TableCell align="right">
                                        {product.count}
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
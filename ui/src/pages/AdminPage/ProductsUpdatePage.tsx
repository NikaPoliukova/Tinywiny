import React, {useEffect, useState} from 'react';
import {Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import {Footer} from "../component/Footer";
import MyHeader from '../component/MyHeader';
import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";
import Button from "@mui/material/Button";
import {Link} from "react-router-dom";
import {Table} from "semantic-ui-react";


const ProductsUpdatePage = () => {
    const [products, setProducts] = useState<Array<Product>>([]);

    useEffect(() => {
        ProductService.findAllProducts()
            .then(response => setProducts(response));
    }, []);

    return (
        <div>
            <MyHeader/>
            <Typography component="h1" variant="h5">
                <h1>All products</h1>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table celled padded>
                        <TableHead>
                            <Table.Row>
                                <Table.HeaderCell>Id product</Table.HeaderCell>
                                <Table.HeaderCell>Name product</Table.HeaderCell>
                                <Table.HeaderCell>Price</Table.HeaderCell>
                                <Table.HeaderCell>Type product</Table.HeaderCell>
                                <Table.HeaderCell>Description</Table.HeaderCell>
                            </Table.Row>
                        </TableHead>
                        <TableBody>
                            {products.map((product) => (
                                <TableRow
                                    key={product.productId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                > <TableCell component="th" scope="row">
                                    {product.productId}
                                </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.productName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.price}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.idType}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {product.description}
                                    </TableCell>
                                    <TableCell>
                                        <Button
                                            component={Link}
                                            type="submit"
                                            fullWidth
                                            variant="contained"
                                            sx={{mt: 1, mb: 1}}
                                            to={'/admin/products/${product.productId'}
                                        >
                                            Update
                                        </Button>
                                    </TableCell>
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

export default ProductsUpdatePage;
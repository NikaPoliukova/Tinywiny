import React, {useEffect, useState} from 'react';
import {Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import {Button, Icon, Table} from 'semantic-ui-react'
import BucketService from "../../services/BucketService";
import {useNavigate, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import MyHeader from "../component/MyHeader";
import {ProductInBucket} from "../../model/ProductInBucket";


function BucketPage() {
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const {bucketId} = useParams();
    const [count, setCount] = useState(1);
    const navigate = useNavigate();
    const [sumProducts, setSumProducts] = useState(0);

    useEffect(() => {
        BucketService.findAllProductsInBucket()
            .then(response => {
                setProducts(response);
                return products;
            })
        BucketService.getSumProductInBucket()
            .then(sumProducts => setSumProducts(sumProducts.sum));
    }, []);

    const deleteProduct = (id: number) => {
        BucketService.deleteProductInBucket(id).then(() => navigate(`/bucket/${bucketId}`))
    }
    const increase = (id: number) => {
        setProducts((cart) => {
            return cart.map((product) => {
                setCount(Number(product.count) + 1);
                const productInBucket: ProductInBucket = {
                    id,
                    count: Number(product.count) + 1,
                }
                BucketService.updateCountProduct(productInBucket).then(() => navigate(`/bucket/${bucketId}`));
                if (product.id === id) {
                    return {
                        ...product,
                        count: Number(product.count) + 1,
                        priceTotal: (Number(product.count) + 1) * Number(product.productDto?.price),
                    };
                }
                return product
            })
        })
    }

    const decrease = (id: number) => {
        setProducts((cart) => {
            return cart.map((product) => {
                if (product.id === id) {
                    const newCount = Number(product.count) - 1 > 1 ? Number(product.count) - 1 : 1;
                    setCount(newCount);
                    const productInBucket: ProductInBucket = {
                        id,
                        count: newCount
                    }
                    BucketService.updateCountProduct(productInBucket).then(() => navigate(`/bucket/${bucketId}`));
                    return {
                        ...product,
                        count: newCount,
                        priceTotal: newCount * Number(product.productDto?.price),
                    };
                }
                return product
            })
        })
    }

    return (
        <div>
            <MyHeader/>
            <Typography component="h1" variant="h5">
                <React.Fragment><Icon name='bitbucket' size='big'/>Bucket</React.Fragment>
            </Typography>
            <Card style={{width: 1000}}>
                <TableContainer>
                    <Table celled padded>
                        <TableHead>
                            <Table.Row>
                                <Table.HeaderCell>Product Name</Table.HeaderCell>
                                <Table.HeaderCell>Count</Table.HeaderCell>
                                <Table.HeaderCell>Price</Table.HeaderCell>
                            </Table.Row>
                        </TableHead>
                        <TableBody>
                            {products.map((item) => (
                                <TableRow
                                    key={item.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.productName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        <Button
                                            type="submit"
                                            fullWidth
                                            onClick={() => increase(Number(item.id))}
                                        > + </Button>
                                        <Button
                                            type="submit"
                                            fullWidth
                                            onClick={() => decrease(Number(item.id))}
                                        > - </Button>
                                        {item.count}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.price}
                                    </TableCell>
                                    <TableCell>
                                        <Button sx={{mt: 4}}
                                                onClick={() => deleteProduct(Number(item.id))}
                                        ><Icon name='delete' size='big'/>
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
                Sum products in order :{sumProducts}
            </Card>
            <Button
                basic color='brown'
                content='Create order'
                size="small"
                href={`/orders/create`}
            ></Button>
            <Footer/>
        </div>
    );
};

export default BucketPage;
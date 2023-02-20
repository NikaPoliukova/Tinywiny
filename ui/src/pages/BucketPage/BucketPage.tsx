import React, {useEffect, useState} from 'react';
import {Button, Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import BucketService from "../../services/BucketService";
import {Link, useNavigate, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import {Icon, Table} from 'semantic-ui-react'
import MyHeader from "../component/MyHeader";
import {ProductInBucket} from "../../model/ProductInBucket";
import {Product} from "../../model/Product";


function BucketPage() {
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const {bucketId} = useParams();
    const [count, setCount] = useState(1);
    const [productDto, setProduct] = useState<Product>();
    const navigate = useNavigate();
    const id = Number(bucketId);

    const addCountProduct = () => {
        const productInBucket: ProductInBucket = {
            productDto,
            count,
            bucketId: id
        }
        BucketService.updateCountProduct(productInBucket).then(response => navigate("/bucket/${bucketId}"))
    }

    const deleteProduct = () => {
        const productForDelete: ProductInBucket = {
            productDto,
            bucketId: id
        }
        BucketService.deleteProductInBucket(productForDelete).then(response => navigate("/bucket/${bucketId}"))
    }
    useEffect(() => {
        BucketService.findAllProductsInBucket(Number(bucketId))
            .then(response => setProducts(response));
    }, []);

    return (
        <div>
            <MyHeader/>
            <Typography component="h1" variant="h5">
                <h1><Icon name='bitbucket' size='big'/>Bucket</h1>
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
                                    key={item.productDto?.productId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.productName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {item.count}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.price}
                                    </TableCell>
                                    <TableCell>
                                        <Button sx={{mt: 4}}
                                            //   onClick={() => setCount(count + 1)}
                                        ><Icon name='add to cart' size='big'/>
                                            add count </Button>
                                    </TableCell>
                                    <TableCell>
                                        <Button sx={{mt: 4}}
                                                onClick={deleteProduct}
                                        ><Icon name='delete' size='big'/>
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>

                </TableContainer>
            </Card>
            <Button sx={{mt: 4}}
                    component={Link}
                    type="submit"
                    variant="contained"
                    to={'/orders/create'}
            > Go to order
            </Button>
            <Footer/>
        </div>
    );
};

export default BucketPage;
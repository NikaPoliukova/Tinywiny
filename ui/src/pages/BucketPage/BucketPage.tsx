import React, {useEffect, useState} from 'react';
import {Button, Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import BucketService from "../../services/BucketService";
import {useNavigate, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import {Icon, Table} from 'semantic-ui-react'
import MyHeader from "../component/MyHeader";
import {ProductInBucket} from "../../model/ProductInBucket";
import {Product} from "../../model/Product";
import {useSessionStore} from "../../Session";
import ProductService from "../../services/ProductService";


function BucketPage() {
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const user = useSessionStore(state => state.user);
    const {bucketId} = useParams();
    const id = Number(bucketId)
    const [count, setCount] = useState(1);
    const [productDto, setProduct] = useState<Product>();
    const navigate = useNavigate();
    const [sumProducts, setSumProducts] = useState(0);

    const updateCountProduct = () => {
        const productInBucket: ProductInBucket = {
            productDto,
            count,
            bucketId: id
        }
        BucketService.updateCountProduct(productInBucket).then(() => navigate("/bucket/${bucketId}"))
    }

    const deleteProduct = (id: number) => {
        BucketService.deleteProductInBucket(id).then(() => navigate("/bucket/${bucketId}"))
    }

    useEffect(() => {
        BucketService.findAllProductsInBucket(Number(bucketId))
            .then(response => {
                setProducts(response);
                return products;
            })
        BucketService.getSumProductInBucket(Number(bucketId)).then(sum=>setSumProducts(sum));
    }, []);
    console.log(sumProducts)
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
                                    key={item.productDto?.productId}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">
                                        {item.productDto?.productName}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        <button type='button' className='count__up' onClick={() => setCount(count - 1)}>
                                            <Icon name="plus"/>
                                        </button>
                                        {item.count}
                                        <button type='button' className='count__down'
                                                onClick={() => setCount(count + 1)}>
                                            <Icon name="minus"/>
                                        </button>
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
            <Button sx={{mt: 4}}
                    type="submit"
                    variant="contained"
                    href={'/orders/create'}
            > Go to order
            </Button>
            <Footer/>
        </div>
    );
};

export default BucketPage;
import React, {useEffect, useState} from 'react';
import {Button, Card, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import BucketService from "../../services/BucketService";
import {Link, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import {Icon, Table} from 'semantic-ui-react'
import {Product} from "../../model/Product";
import MyHeader from "../component/MyHeader";


function BucketPage() {
    const [products, setProducts] = useState<Array<Product>>([])
    const {bucketId} = useParams();
    const [count, setCount] = useState(1);

    /*  const addProductInBucket = () => {
          setProduct([...product, {productName: '', price:0 ,countInStock: 0, description: '', idType: 0}])
      }*/
    /*  const deleteProductFromBucket = () => {
         setProduct([...product, {productName: '', price:0 ,countInStock: 0, description: '', idType: 0}])
     }*/


    //функция которая передает все продукты в ордер
    //функция добавления количества продукта в корзину
    //функция удаления продукта из корзины

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
                                <Table.HeaderCell>Name product</Table.HeaderCell>
                                <Table.HeaderCell>Price</Table.HeaderCell>
                                <Table.HeaderCell>Type priduct</Table.HeaderCell>
                            </Table.Row>
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
                                        <Button sx={{mt: 4}}
                                                onClick={() => setCount(count + 1)}
                                        ><Icon name='add to cart' size='big'/>
                                            add count +1 </Button>
                                    </TableCell>
                                    <TableCell>
                                        <Button sx={{mt: 4}}
                                                onClick={() => setCount(count + 1)}
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
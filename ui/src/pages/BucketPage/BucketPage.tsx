import React, {useEffect, useState} from 'react';
import {
    Card, Link,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField,
    Typography
} from "@mui/material";
import {Button} from 'semantic-ui-react'
import BucketService from "../../services/BucketService";
import {useNavigate, useParams} from "react-router-dom";
import {Footer} from "../component/Footer";
import {Icon, Table} from 'semantic-ui-react'
import MyHeader from "../component/MyHeader";
import {ProductInBucket} from "../../model/ProductInBucket";


function BucketPage() {
    const [products, setProducts] = useState<Array<ProductInBucket>>([])
    const {bucketId} = useParams();
    const [count, setCount] = useState(1);
    const navigate = useNavigate();
    const [sumProducts, setSumProducts] = useState(0);


    const updateCountProductInBucket = (id: number) => {
        const productInBucket: ProductInBucket = {
            id,
            count
        }
        BucketService.updateCountProduct(productInBucket).then(() =>navigate(`/bucket/`+bucketId));
    }

    const deleteProduct = (id: number) => {
        BucketService.deleteProductInBucket(id).then(() => navigate(`/bucket/${bucketId}`))
    }


    useEffect(() => {
        BucketService.findAllProductsInBucket(Number(bucketId))
            .then(response => {
                setProducts(response);
                return products;
            })
        BucketService.getSumProductInBucket(Number(bucketId)).then(sumProducts => setSumProducts(sumProducts.sum));
    }, []);

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
                                        <TextField label="Count"
                                                   type={'number'}
                                                   value={count}
                                                   onChange={e => {
                                                       setCount(Number(e.target.value));
                                                       updateCountProductInBucket(Number(item.id))
                                                   }}
                                        >
                                        </TextField>

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
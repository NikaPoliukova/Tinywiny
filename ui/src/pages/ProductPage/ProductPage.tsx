import * as React from 'react';
import {useEffect, useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';

import {useNavigate, useParams} from "react-router-dom";
import ProductService from "../../services/ProductService";
import {Product} from "../../model/Product";
import Button from "@mui/material/Button";
import {Footer} from "../component/Footer";
import {Col, Row} from "react-bootstrap";

import {Divider, Header, Icon, Table} from 'semantic-ui-react'
import MyHeader from 'pages/component/MyHeader';
import {ProductInBucket} from "../../model/ProductInBucket";


const theme = createTheme();

export function ProductPage() {
    const Product = () => {
        const {productId} = useParams();
        const [product, setProduct] = useState<Product>();
        const [user];
        const [bucket]

        const navigate = useNavigate();
        const addProductInBucket = () => {
            const product: ProductInBucket = {
                productId,
                bucketId
            };
            ProductService.addProductInBucket(product).then(response => navigate("/products/toys"));
        }


        useEffect(() => {
            ProductService.getProduct(Number(productId))
                .then(response => setProduct(response));
        }, []);
        return (
            <ThemeProvider theme={theme}>
                <CssBaseline/>
                <MyHeader/>
                <main>

                    <Container className="mt-3">
                        <Row>
                            <Col md={4}>
                                <Card sx={{height: '100%', display: 'flex', flexDirection: 'column'}}
                                >
                                    <CardMedia
                                        component="img"
                                        sx={{
                                            pt: '1%',
                                        }}
                                        image="https://source.unsplash.com/random"
                                    />
                                    <CardContent sx={{flexGrow: 1}}>
                                    </CardContent>
                                </Card>
                            </Col>

                            <Divider horizontal>
                                <Header as='h4'>
                                    <Icon name='tag'/>
                                    Information
                                </Header>
                            </Divider>

                            <Table definition>
                                <Table.Body>
                                    <Table.Row>
                                        <Table.Cell width={2}>Product name:</Table.Cell>
                                        <Table.Cell>{product?.productName}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell width={2}>Price, BUN:</Table.Cell>
                                        <Table.Cell>{product?.price}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>Count in stock</Table.Cell>
                                        <Table.Cell>{product?.countInStock}</Table.Cell>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell>Description</Table.Cell>
                                        <Table.Cell>{product?.description}</Table.Cell>
                                    </Table.Row>
                                </Table.Body>
                            </Table>
                            <Button
                                type="submit"
                                variant="contained"
                                sx={{mt: 1, mb: 1}}
                                onClick={addProductInBucket}
                            >
                                Add in bucket
                            </Button>
                        </Row>
                    </Container>
                </main>
                <Footer/>
            </ThemeProvider>
        );
    }
    return (
        <>
            <Product/>
        </>
    )
}

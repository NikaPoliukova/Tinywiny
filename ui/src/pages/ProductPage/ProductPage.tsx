import * as React from 'react';
import {useEffect, useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import Header from "../component/Header";
import {Link, useParams} from "react-router-dom";
import ProductService from "../../services/ProductService";
import {Product} from "../../model/Product";
import Button from "@mui/material/Button";
import {Footer} from "../component/Footer";
import {Col, Row} from "react-bootstrap";


const theme = createTheme();

export function ProductPage() {
    const Product = () => {
        const {productId} = useParams();
        const [product, setProduct] = useState<Product>();
        useEffect(() => {
            ProductService.getProduct(Number(productId))
                .then(response => setProduct(response));
        }, []);
        return (
            <ThemeProvider theme={theme}>
                <CssBaseline/>
                <Header/>
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
                            <Col>
                                <Row className="d-flex flex-column align-items-center">
                                    <h2>{product?.productName}</h2>
                                </Row>
                            </Col>
                            <Col md={1}>
                                <h3>Price: {product?.price} BUN</h3>
                                <Row className="d-flex flex-column m-3">
                                    <Row>
                                        <h4>Discription:</h4> {product?.description}
                                    </Row>
                                </Row>
                                <Col>
                                    <Row className="d-flex flex-column align-items-center">
                                        <h4> count in stock:</h4> {product?.countInStock}
                                    </Row>
                                </Col>
                                <Button
                                    component={Link}
                                    type="submit"
                                    variant="contained"
                                    sx={{mt: 1, mb: 1}}
                                    to={'product/${order.orderId}'}
                                >
                                    Add in bucket
                                </Button>
                            </Col>
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

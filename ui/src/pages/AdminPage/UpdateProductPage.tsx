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
import {Footer} from "../component/Footer";
import {Col, Row} from "react-bootstrap";
import {Divider, Form, Header, Icon, Table} from 'semantic-ui-react'
import MyHeader from 'pages/component/MyHeader';
import {IconButton} from "@mui/material";
import {AddAPhoto, AutoDelete} from "@mui/icons-material";
import ImageService from "../../services/ImageService";
import {Image} from "../../model/Image";


const theme = createTheme();

export function UpdateProductPage() {
    const Product = () => {
        const {productId} = useParams();
        const [product, setProduct] = useState<Product>();
        const [productName, setProductName] = useState('');
        const [price, setPrice] = useState(0);
        const [countInStock, setCountInStock] = useState(0);
        const [description, setDescription] = useState('');
        const [imageName, setImageName] = useState('');


        const navigate = useNavigate();
      /*  const updateImage = () => {
            const image: Image = {
                imageName,
                //productId
            };
            // ImageService.updateImage(image).then(response => navigate("/products"));
        }

        const deleteImage = () => {
            const image: Image = {
                //productId
            };
            ImageService.deleteImage(image).then(response => navigate("/products"));
        }*/


        const updateProduct = () => {
            const product: Product = {
                productName,
                price,
                countInStock,
                description

            };
            ProductService.updateProduct(Number(productId),product).then(response => navigate("/admin/products"));
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
                    <Container>
                        <Row>
                            <Col md={4}>
                                <Card sx={{height: '100%', display: 'flex', flexDirection: 'column'}}>
                                    <CardMedia
                                        component="img"
                                        image="https://source.unsplash.com/random"/>
                                    <CardContent sx={{flexGrow: 1}}>
                                    </CardContent>
                                    <IconButton color="primary"
                                                aria-label="upload picture"
                                                component="label">
                                        <input hidden accept="image/*" type="file"
                                              // onClick={updateImage}
                                        />
                                        <AddAPhoto/>
                                    </IconButton>

                                    <IconButton color="primary"
                                                aria-label="delete picture"
                                                component="label"
                                               // onClick={deleteImage}
                                    >
                                        <AutoDelete/>
                                    </IconButton>

                                </Card>
                            </Col>
                            <Divider horizontal>
                                <Header as='h4'>
                                    <Icon name='setting'/>
                                    Update information
                                </Header>
                            </Divider>

                            <Table definition>
                                <Table.Body>
                                    <Table.Row>
                                        <Table.Cell width={2}>Product name:</Table.Cell>
                                        <Table.Cell>{product?.productName}</Table.Cell>
                                        <Form.Input fluid placeholder='Write new product name'
                                                    value={productName}
                                                    onChange={e => setProductName(e.target.value)}/>
                                    </Table.Row>
                                    <Table.Row>
                                        <Table.Cell width={2}>Price, BUN:</Table.Cell>
                                        <Table.Cell>{product?.price}</Table.Cell>
                                        <Form.Input fluid placeholder='Write new price'
                                                    value={price}
                                                    onChange={e => setPrice(Number(e.target.value))}/>
                                    </Table.Row>

                                    <Table.Row>
                                        <Table.Cell>Count in stock</Table.Cell>
                                        <Table.Cell>{product?.countInStock}</Table.Cell>
                                        <Form.Input fluid placeholder='Write new count'
                                                    value={countInStock}
                                                    onChange={e => setCountInStock(Number(e.target.value))}/>
                                    </Table.Row>

                                    <Table.Row>
                                        <Table.Cell>Description</Table.Cell>
                                        <Table.Cell>{product?.description}</Table.Cell>
                                        <Form.Input fluid placeholder='Correct description'
                                                    value={description}
                                                    onChange={e => setDescription(e.target.value)}/>
                                    </Table.Row>
                                </Table.Body>
                            </Table>
                            <Form.Button
                                onClick={updateProduct}
                            >Update product</Form.Button>
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

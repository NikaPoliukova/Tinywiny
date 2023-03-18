import * as React from 'react';
import {useEffect, useRef, useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CssBaseline from '@mui/material/CssBaseline';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import {useNavigate, useParams} from "react-router-dom";
import ProductService from "../../services/ProductService";
import {Product} from "../../model/Product";
import {Footer} from "../component/Footer";
import {Col, Row} from "react-bootstrap";
import {Divider, Form, Header, Icon, Segment, Table} from 'semantic-ui-react'
import MyHeader from 'pages/component/MyHeader';
import {Button, IconButton} from "@mui/material";
import {AutoDelete} from "@mui/icons-material";
import ImageService from "../../services/ImageService";


const theme = createTheme();

export function UpdateProductPage() {
    const Product = () => {
        const {productId} = useParams();
        const [product, setProduct] = useState<Product>();
        const [price, setPrice] = useState(0);
        const [countInStock, setCountInStock] = useState(0);
        const [description, setDescription] = useState<string>();
        const [image, setImage] = useState('');
        const fileInput = useRef<HTMLInputElement | null>(null);
        const navigate = useNavigate();

        useEffect(() => {
            ProductService.getProduct(Number(productId))
                .then(response => setProduct(response))
                .then(() => ImageService.downloadImage()
                    .then(response => {
                        setImage(response)
                    }))
        }, []);

        const uploadImage = () => {
            ImageService.uploadImage({
                productId, file: fileInput?.current?.files && fileInput?.current?.files[0]
            }).then(response => {
                setImage(response);
            })
        };

        const updateProduct = () => {
            const productUpdate: Product = {
                productName: product?.productName,
                price,
                countInStock,
                description
            };
            ProductService.updateProduct(Number(productId), productUpdate)
                .then(() => navigate("/admin/products"));
        }

        const deleteProduct = () => {
            ProductService.deleteProduct(Number(productId)).then(() => navigate("/admin/products"));
        }
        const deleteImage = () => {
            ImageService.deleteImage(Number(productId)).then(() => navigate("/admin/products"));
        }

        return (
            <ThemeProvider theme={theme}>
                <CssBaseline/>
                <MyHeader/>
                <main>
                    <Container>
                        <Row>
                            <Col md={4}>
                                <Card sx={{height: '100%', display: 'flex', flexDirection: 'column'}}>
                                    <CardContent sx={{flexGrow: 1}}>
                                    </CardContent>
                                    <Segment placeholder>
                                        <Header icon>
                                            <img src={`data:image/png;base64,${image}`}/>
                                        </Header>
                                        <Button
                                            variant="contained"
                                            component="label"
                                        >
                                            Upload File
                                            <input
                                                ref={fileInput}
                                                type="file"
                                                hidden
                                            />
                                        </Button>
                                        <Button
                                            variant="contained"
                                            component="label"
                                            onClick={uploadImage}
                                        >
                                            Save
                                        </Button>
                                    </Segment>

                                    <IconButton color="primary"
                                                aria-label="delete picture"
                                                component="label"
                                                onClick={deleteImage}
                                    >
                                        <AutoDelete/>
                                    </IconButton>
                                </Card>
                            </Col>
                            <Form.Button
                                onClick={deleteProduct}
                            >Delete product
                            </Form.Button>
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

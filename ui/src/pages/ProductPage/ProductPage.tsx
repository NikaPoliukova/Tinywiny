import * as React from 'react';
import {useEffect, useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import Header from "../component/Header";
import {useParams} from "react-router-dom";
import ProductService from "../../services/ProductService";
import {Product} from "../../model/Product";
import CardActions from "@mui/material/CardActions";
import Button from "@mui/material/Button";


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
                    <Container sx={{py: 8}} maxWidth="md">
                        <Typography
                            component="h2"
                            variant="h3"
                            align="right"
                            color="text.primary">
                            имя продукта {product?.productName}
                        </Typography>
                        <Grid container spacing={4}>
                            <Grid item xs={10} sm={6} md={4}>
                                <Card
                                    sx={{height: '100%', display: 'flex', flexDirection: 'column'}}
                                >
                                    <CardMedia
                                        component="img"
                                        sx={{
                                            pt: '1%',
                                        }}
                                        image="https://source.unsplash.com/random"
                                        alt="random"
                                    />
                                    <CardContent sx={{flexGrow: 1}}>
                                        <Typography gutterBottom variant="h5" component="h2">
                                            {product?.productName}
                                            name
                                        </Typography>
                                    </CardContent>
                                </Card>
                            </Grid>
                        </Grid>
                    </Container>
                    <Container maxWidth="sm">
                        <Typography
                            component="h5"
                            variant="h5"
                            align="left"
                            color="text.primary">
                            в наличии = {product?.count}
                        </Typography>
                        <Typography
                            component="h5"
                            variant="h5"
                            align="left"
                            color="text.primary">
                            цена = {product?.price}
                        </Typography>
                        <Typography
                            component="h5"
                            variant="h5"
                            align="center"
                            color="text.primary">
                            описание {product?.description}
                        </Typography>
                        <CardActions>
                            <Button size="small">Add in bucket</Button>
                        </CardActions>
                    </Container>
                </main>
            </ThemeProvider>
        );
    }
    return (
        <>
            <Product/>
        </>
    )
}

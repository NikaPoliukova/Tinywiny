import * as React from 'react';
import {useEffect, useState} from 'react';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import MyHeader from 'pages/component/MyHeader';
import {Footer} from "../component/Footer";
import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";
import {Link, useNavigate, useParams} from "react-router-dom";
import {Sidebar} from "../component/SideBar";
import {Bucket} from "../../model/Bucket";
import BucketService from "../../services/BucketService";
import {ThemeProvider} from "react-bootstrap";
import {createTheme} from "@mui/material";
import {useSessionStore} from "../../store";


const theme = createTheme();
export default function Products() {
    const [products, setProducts] = useState<Array<Product>>([]);
    const {type} = useParams();

    useEffect(() => {
        ProductService.findAllProductsByTypeAndPage(String(type))
            .then(response => setProducts(response));
    }, []);

    return (
        <ThemeProvider theme={theme}>

            <CssBaseline/>
            <MyHeader/>
            <main>
                <Sidebar/>
                <Box
                    sx={{
                        bgcolor: 'background.paper',
                        pt: 15,
                        pb: 10,
                    }}
                >
                    <Typography style={{color: 'var(--primary-color)'}}
                                component="h1"
                                variant="h2"
                                align="center"
                    >
                        Products
                    </Typography>
                </Box>
                <Container sx={{py: 8}} maxWidth="md">
                    <Grid container spacing={4}>
                        {products.map((product) => (
                            <Grid item key={product.productId} xs={12} sm={6} md={4}>
                                <Card sx={{height: '100%', display: 'flex', flexDirection: 'column'}}>
                                    <CardMedia
                                        component="img"
                                        image="https://source.unsplash.com/random"/>
                                    <CardContent sx={{flexGrow: 1}}>
                                        <Typography>
                                            {product.productName}
                                        </Typography>
                                        <Typography>
                                            price = {product.price}
                                        </Typography>
                                    </CardContent>
                                    <CardActions>
                                        <Button
                                            component={Link}
                                            type="submit"
                                            size="small"
                                            sx={{mt: 1, mb: 1}}
                                            to={'/products/' + product.productId}
                                        >Open</Button>
                                        <Button
                                            onClick={() => BucketService.addProductInBucket(Number(product.productId))}
                                        >Add in bucket</Button>

                                    </CardActions>
                                </Card>
                            </Grid>
                        ))}
                    </Grid>
                </Container>
            </main>
            <Footer/>

        </ThemeProvider>
    );
}
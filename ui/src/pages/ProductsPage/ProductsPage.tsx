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
import {createTheme, ThemeProvider} from '@mui/material/styles';
import Header from 'pages/component/MyHeader';
import {Footer} from "../component/Footer";
import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";
import {Link, useParams} from "react-router-dom";
import {Sidebar} from "../component/SideBar";
import {ProductInBucket} from "../../model/ProductInBucket";
import {Bucket} from "../../model/Bucket";
import {useSessionStore} from "../../Session";
import BucketService from "../../services/BucketService";

const cards = [1, 2, 3, 4, 5, 6];

const theme = createTheme();
export default function Products() {
    const [products, setProducts] = useState<Array<Product>>([]);
    const {type} = useParams();
    const [productInBucket, setProductInBucket] = useState<ProductInBucket>();
    const [bucket, setBucket] = useState<Bucket>();
    const user = useSessionStore(state => state.user);
    /* useEffect(() => {
         BucketService.findBucketByUserId(Number(user.userId))
             .then(response => setBucket(response));
     }, []);*/
    useEffect(() => {
        ProductService.findAllProductsByTypeAndPage(String(type))
            .then(response => setProducts(response));
    }, []);


    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <Header/>

            <main>
                <Sidebar/>
                <Box
                    sx={{
                        bgcolor: 'background.paper',
                        pt: 15,
                        pb: 10,
                    }}
                >
                    <Container maxWidth="sm">
                        <Typography
                            component="h1"
                            variant="h2"
                            align="center"
                            color="text.primary">
                            Products
                        </Typography>
                    </Container>
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
                                            to={'/products/product/' + product.productId}
                                        >Open</Button>
                                        <Button
                                            // onClick={addInBucket(product)}
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